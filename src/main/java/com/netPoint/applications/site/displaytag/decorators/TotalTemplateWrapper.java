/**
 * 
 */
package com.netPoint.applications.site.displaytag.decorators;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.decorator.TableDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.TableModel;

/**
 * @author vincent
 *
 */
public class TotalTemplateWrapper extends TableDecorator {
	private static final Log log = LogFactory.getLog(TotalTemplateWrapper.class);
    /**
     * total amount.
     */
	public boolean totalRequired;
	private Double groupSize;

	public boolean summationRequired;
	
    private Map grandTotals = new HashMap();

    /**
     * total amount for current group.
     */
    private Map subTotals = new HashMap();

    /**
     * Previous values needed for grouping.
     */
    private Map previousValues = new HashMap();

    /**
     * Name of the property used for grouping.
     */
    private String groupPropertyName;

    /**
     * Label used for subtotals. Default: "{0} total".
     */
    private String subtotalLabel = "{0} average";

    /**
     * Label used for totals. Default: "Total".
     */
    private String totalLabel = "Average";

    public TotalTemplateWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * Setter for <code>subtotalLabel</code>.
     * @param subtotalLabel The subtotalLabel to set.
     */
    public void setSubtotalLabel(String subtotalLabel)
    {
        this.subtotalLabel = subtotalLabel;
    }

    /**
     * Setter for <code>totalLabel</code>.
     * @param totalLabel The totalLabel to set.
     */
    public void setTotalLabel(String totalLabel)
    {
        this.totalLabel = totalLabel;
    }

    /**
     * @see org.displaytag.decorator.Decorator#init(PageContext, Object, TableModel)
     */
    public void init(PageContext context, Object decorated, TableModel tableModel)
    {
        super.init(context, decorated, tableModel);
        log.debug("Init method....");
        // reset
        groupPropertyName = null;
        grandTotals.clear();
        subTotals.clear();
        previousValues.clear();

        for (Iterator it = tableModel.getHeaderCellList().iterator(); it.hasNext();)
        {
            HeaderCell cell = (HeaderCell) it.next();
            if(cell.getGroup() >0 || cell.isTotaled()){
            	summationRequired = true;
            	totalRequired = true;
            	log.debug("Total row is required");
            }
            
            if (cell.getGroup() == 1)
            {
                groupPropertyName = cell.getBeanPropertyName();
            	log.debug("Groupname:" + groupPropertyName);
            }
        }
    }

    public String startRow()
    {
    	if(!summationRequired){
    		return "";
    	}
        String subtotalRow = null;
        if (groupPropertyName != null)
        {
            Object groupedPropertyValue = evaluate(groupPropertyName);
            Object previousGroupedPropertyValue = previousValues.get(groupPropertyName);
            // subtotals
            log.debug(previousGroupedPropertyValue);
            log.debug(ObjectUtils.equals(previousGroupedPropertyValue, groupedPropertyValue));
            if (previousGroupedPropertyValue != null
                && !ObjectUtils.equals(previousGroupedPropertyValue, groupedPropertyValue))
            {
            	groupSize = new Double(getListIndex() + 1);
                subtotalRow = createTotalRow(false);
            }
            previousValues.put(groupPropertyName, groupedPropertyValue);
        }

        for (Iterator it = tableModel.getHeaderCellList().iterator(); it.hasNext();)
        {
            HeaderCell cell = (HeaderCell) it.next();
            if (cell.isTotaled())
            {
                String totalPropertyName = cell.getBeanPropertyName();
                Number amount = (Number) evaluate(totalPropertyName);

                Number previousSubTotal = (Number) subTotals.get(totalPropertyName);
                Number previousGrandTotals = (Number) grandTotals.get(totalPropertyName);

                subTotals.put(totalPropertyName, new Double((previousSubTotal != null
                    ? previousSubTotal.doubleValue()
                    : 0)
                    + (amount != null ? amount.doubleValue() : 0)));

                grandTotals.put(totalPropertyName, new Double((previousGrandTotals != null ? previousGrandTotals
                    .doubleValue() : 0)
                    + (amount != null ? amount.doubleValue() : 0)));
            }
        }
        return subtotalRow;
    }

    /**
     * After every row completes we evaluate to see if we should be drawing a new total line and summing the results
     * from the previous group.
     * @return String
     */
    public final String finishRow()
    {
    	if(!summationRequired){
    		return "";
    	}
        StringBuffer buffer = new StringBuffer(1000);

        // Grand totals...
        if (getViewIndex() == ((List) getDecoratedObject()).size() - 1)
        {
            if (groupPropertyName != null)
            {
                buffer.append(createTotalRow(false));
            }
            buffer.append(createTotalRow(true));
        }
        return buffer.toString();

    }

    public void writeToExporter(String[] rowValues){
    	
    }
    
    protected String createTotalRow(boolean grandTotal)
    {
    	boolean totalWriten = false;
    	log.debug("Create total:" + grandTotal);
        StringBuffer buffer = new StringBuffer(1000);
        buffer.append("\n<tr class=\"total\">"); //$NON-NLS-1$

        List headerCells = tableModel.getHeaderCellList();

        //export row value
        List<String>exportValue = new ArrayList<String>();
        int i = 0;
        for (Iterator it = headerCells.iterator(); it.hasNext();)
        {
        	log.debug("col " + i++);
            HeaderCell cell = (HeaderCell) it.next();
            String cssClass = ObjectUtils.toString(cell.getHtmlAttributes().get("class"));

            buffer.append("<td"); //$NON-NLS-1$
            if (StringUtils.isNotEmpty(cssClass))
            {
                buffer.append(" class=\""); //$NON-NLS-1$
                buffer.append(cssClass);
                buffer.append("\""); //$NON-NLS-1$
            }
            buffer.append(">"); //$NON-NLS-1$

            if (cell.isTotaled())
            {
            	log.debug("NO total");
                String totalPropertyName = cell.getBeanPropertyName();
                Object total = grandTotal ? grandTotals.get(totalPropertyName) : subTotals.get(totalPropertyName);
                log.debug("Total:" + total + " grand size:" + grandTotals.size() + " subtotal size:" + subTotals.size());
                log.debug("List Size Index:" + getListIndex() +1);
                log.debug(grandTotal? "GroupSize: " + groupSize : "total size: " + new Double(getListIndex()+1));
                Number averageVal = grandTotal? ((Number)total).doubleValue()/new Double(getListIndex()+1) : ((Number)total).doubleValue()/groupSize;
                total = averageVal;
                log.debug("Avg : " + averageVal);
                DisplaytagColumnDecorator[] decorators = cell.getColumnDecorators();
                for (int j = 0; j < decorators.length; j++)
                {
                    try
                    {
                        total = decorators[j].decorate(total, this.getPageContext(), tableModel.getMedia());
                    }
                    catch (DecoratorException e)
                    {
//                        log.warn(e.getMessage(), e);
                        // ignore, use undecorated value for totals
                    }
                }
                buffer.append(total);
                exportValue.add(total.toString());
            }
            else if (groupPropertyName != null && groupPropertyName.equals(cell.getBeanPropertyName()) && !grandTotal)
            {
            	log.debug("Grouped");
            	String subTotalLabel = MessageFormat.format(subtotalLabel, new Object[]{previousValues.get(groupPropertyName)});
            	if(grandTotal && !totalWriten){
            		buffer.append(totalLabel);
            		totalWriten = true;
            		exportValue.add(totalLabel);
            	}else{
            		buffer.append(subTotalLabel);
            		exportValue.add(subTotalLabel);
            	}
            }else if(grandTotal && !totalWriten){
            	log.debug("Writing total label..");
            	totalWriten = true;
        		buffer.append(totalLabel);
        		exportValue.add(totalLabel);
            }else{
            	exportValue.add("");
            }
            
            buffer.append("</td>"); //$NON-NLS-1$

        }
        writeToExporter(exportValue.toArray(new String[exportValue.size()]));
        buffer.append("</tr>"); //$NON-NLS-1$
        // reset subtotal
        this.subTotals.clear();

        return buffer.toString();
    }

}

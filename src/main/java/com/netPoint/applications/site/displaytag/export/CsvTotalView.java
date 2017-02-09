/**
 * 
 */
package com.netPoint.applications.site.displaytag.export;

import org.apache.commons.lang.StringUtils;

/**
 * @author vincent
 *
 */
public class CsvTotalView extends TextBasedView {

    protected String getRowEnd()
    {
        return "\n";
    }

    protected String getCellEnd()
    {
        return ","; 
    }

    protected boolean getAlwaysAppendCellEnd()
    {
        return false;
    }

    protected boolean getAlwaysAppendRowEnd()
    {
        return true;
    }

    public String getMimeType()
    {
        return "text/csv"; //$NON-NLS-1$
    }

    protected String escapeColumnValue(Object value)
    {
        String stringValue = StringUtils.trim(value.toString());
        if (!StringUtils.containsNone(stringValue, new char[]{'\n', ','}))
        {
            return "\"" + //$NON-NLS-1$
                StringUtils.replace(stringValue, "\"", "\\\"") + "\"";
        }

        return stringValue;
    }

	
}

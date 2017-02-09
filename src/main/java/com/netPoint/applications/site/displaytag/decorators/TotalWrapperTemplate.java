/**
 * 
 */
package com.netPoint.applications.site.displaytag.decorators;

import org.displaytag.decorator.TableDecorator;
import java.util.List;
import org.apache.commons.lang.ObjectUtils;

/**
 * @author Faliherizo
 *
 */
public abstract class TotalWrapperTemplate extends TableDecorator{
	private double grandTotal;
	 private double cityTotal;
	 private StringBuffer buffer;
	 public final String  finishRow()
	      {
	          int listindex = ((List) getDecoratedObject()).indexOf(this.getCurrentRowObject());
	          ReportableListObject reportableObject = (ReportableListObject) this.getCurrentRowObject();
	          String nextCity = null;
	          this.cityTotal += reportableObject.getAmount();
	          this.grandTotal += reportableObject.getAmount();
	          if (listindex != ((List) getDecoratedObject()).size() - 1)
	          {
	              nextCity = ((ReportableListObject) ((List) getDecoratedObject()).get(listindex + 1)).getCity();
	          }
	          this.buffer = new StringBuffer(1000);
	          // City subtotals...
	          if (!ObjectUtils.equals(nextCity, reportableObject.getCity()))
	          {
	              writeCityTotal(reportableObject.getCity(), this.cityTotal);
	              this.cityTotal = 0;
	          }
	          // Grand totals...
	          if (getViewIndex() == ((List) getDecoratedObject()).size() - 1)
	          {
	              writeGrandTotal(this.grandTotal);
	          }

	          return buffer.toString();

	      }
	 abstract protected void  writeCityTotal(String city, double total);
	 abstract protected void  writeGrandTotal(double total);

}

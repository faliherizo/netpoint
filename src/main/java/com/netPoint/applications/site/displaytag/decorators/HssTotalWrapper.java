/**
 * 
 */
package com.netPoint.applications.site.displaytag.decorators;

import org.displaytag.decorator.hssf.DecoratesHssf;
import  org.apache.poi.hssf.usermodel.HSSFCell;


import  org.apache.poi.hssf.usermodel.HSSFRichTextString;


import  org.apache.poi.hssf.usermodel.HSSFRow;


import  org.apache.poi.hssf.usermodel.HSSFSheet;
/**
 * @author Faliherizo
 *
 */
public class HssTotalWrapper extends TotalWrapperTemplate implements DecoratesHssf{

	@Override
	public void setSheet(HSSFSheet arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeCityTotal(String city, double total) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeGrandTotal(double total) {
		// TODO Auto-generated method stub
		
	}
/*
	 private HSSFSheet sheet;
	     private HSSFCell currentCell;
	     private HSSFRow currentRow;
	     private int colNum;
	     @Override
	     protected void writeCityTotal(String city, double total)
	     {
	         this.writeTotal(city, total);
	     }
	     private void writeTotal(String value, double total)
	     {
	         if (this.assertRequiredState())
	         {
	             int rowNum = this.sheet.getLastRowNum();
	             this.currentRow = this.sheet.createRow(++rowNum);
	             this.colNum = 0;
	             prepareCell();
	             prepareCell();
	             prepareCell();
	             this.currentCell.setCellValue(new HSSFRichTextString("------------"));
	             this.currentRow = this.sheet.createRow(++rowNum);
	             this.colNum = 0;
	             prepareCell();
	             prepareCell();
	             this.currentCell.setCellValue(new HSSFRichTextString(value + " Total:"));
	             prepareCell();
	             this.currentCell.setCellValue(total);
	         }
	     }
	     private void prepareCell()
	     {
	         this.currentCell = this.currentRow.createCell(this.colNum++);
	     }
	     @Override
	     protected void writeGrandTotal(double total)
	     {
	         this.writeTotal("Grand", total);
	     }
	     @Override
	     public void setSheet(HSSFSheet sheet)
	     {
	         this.sheet = sheet;
	     }
	     private boolean assertRequiredState()
	         {
	             return this.sheet != null;
	         }*/
}

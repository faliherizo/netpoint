/**
 * 
 */
package com.netPoint.applications.site.displaytag.decorators;

import org.displaytag.decorator.hssf.DecoratesHssf;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * @author Faliherizo
 *
 */
public class ExcelTotalWrapper extends AbstractExportWrapper implements DecoratesHssf{
	private HSSFSheet sheet;
	private HSSFRow currentRow;
	
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	@Override
	public void doWriteToExporter(String[] colValues) {
	/*	int currRowNum = this.sheet.getLastRowNum();
		currentRow = this.sheet.createRow(++currRowNum);
		int colNo = 0;
		for(String colValue : colValues){
			HSSFCell currCell = currentRow.createCell(colNo++);
			currCell.setCellValue(new HSSFRichTextString(colValue));			
		}		*/
	}
}

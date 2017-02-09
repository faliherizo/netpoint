/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.netPoint.applications.site.model.Commande;

/**
 * @author Faliherizo
 *
 */
public class ReportsController extends AbstractController{

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	private String createExcelReport (List<Commande> list, HashMap reportsContext) {
		String outputFilePath = "";
	/*	
		// Invoke Excel Util
		ExcelUtil excelUtil = new ExcelUtil(reportsContext);
		HSSFWorkbook outWb = excelUtil.getNewExcelReport();
		HSSFSheet currSheet = outWb.getSheetAt(0);
		Iterator itr = list.iterator();
		
		while (itr.hasNext()){
			Commande currVendor = (Commande)itr.next();
			logger.debug ("currVendor Desc ["+currVendor.getVendorName()+"]");
			// Create row
			HSSFRow currRow = currSheet.createRow(currSheet.getLastRowNum()+1);
			
			//HashMap hmCurrReportInfo = (HashMap)hmReportInfo.get(templateId);
			//Integer titleRowIndex = (Integer)hmCurrReportInfo.get(VmpConstants.RPT001_TITLE_ROW_NUM);
			HSSFRow titleRow = currSheet.getRow(VmpConstants.RPT002_TITLE_ROW_NUM.intValue());
			int maxColCount = titleRow.getLastCellNum();
			
			
			for ( int i=0; i< titleRow.getLastCellNum(); i++) {
				//Create Cell
				HSSFCell currCell = currRow.createCell(i);
				// Set Value
				String cellValue = "";
				double cellValueDouble = 0.0;
				int cellValueInt = 0;
				
				// Indicators
				boolean isString = false;
				boolean isDouble = false;
				boolean isInt = false;
				
				if (i==VmpConstants.RPT002_RANKING_COL_NUM) {
					cellValueInt = currVendor.getRanking();
					isInt = true;
				}else if (i==VmpConstants.RPT002_VENDORNAME_COL_NUM){
					cellValue = currVendor.getVendorName();
					isString = true;
				}else if (i==VmpConstants.RPT002_EURO_TOTAL_COL_NUM){
					cellValueDouble = currVendor.getEuroTotal();
					isDouble = true;
				}else if (i==VmpConstants.RPT002_SHARE_COL_NUM) {
					cellValueDouble = currVendor.getShareOfEuroTotal();
					isDouble = true;
				}
				
				//*************
				if (isString) {
					currCell.setCellValue(cellValue);
				}else if (isDouble){
					currCell.setCellValue(cellValueDouble);
				}else if (isInt){
					currCell.setCellValue(cellValueInt);
				}

			}// end iteration through column
			
		}// end iterating through rows		
		
		// Write report gen date
		HSSFRow genDateRow = currSheet.getRow(VmpConstants.RPT002_GEN_DATE_ROW_NUM);
		HSSFCell genDateCell = genDateRow.getCell(1);
		if (genDateCell == null) {
			genDateCell = genDateRow.createCell(1);
		}
		genDateCell.setCellValue(new DateUtil().formatDate(Calendar.getInstance().getTime()));
		
		
		outputFilePath = excelUtil.writeExcelFile(outWb);
		*/
		return outputFilePath;
	}

}

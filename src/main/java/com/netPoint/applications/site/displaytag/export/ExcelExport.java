/**
 * Licensed under the Artistic License; you may not use this file
 * except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://displaytag.sourceforge.net/license.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package com.netPoint.applications.site.displaytag.export;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.displaytag.Messages;
import org.displaytag.exception.BaseNestableJspTagException;
import org.displaytag.exception.SeverityEnum;
import org.displaytag.export.BaseExportView;
import org.displaytag.export.excel.DefaultHssfExportView;
import org.displaytag.model.Column;
import org.displaytag.model.ColumnIterator;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.Row;
import org.displaytag.model.RowIterator;
import org.displaytag.model.TableModel;
import org.displaytag.render.HssfTableWriter;

/**
 * Export view for excel exporting.
 * 
 * @author Fabrizio Giustina
 * @version $Revision: 1081 $ ($Author: fgiust $)
 */
public class ExcelExport extends DefaultHssfExportView {

	/**
	 * TableModel to render.
	 */
	private TableModel model;

	/**
	 * @see org.displaytag.export.ExportView#setParameters(org.displaytag.model.TableModel,
	 *      boolean, boolean, boolean)
	 */
	@Override
	public void setParameters(TableModel tableModel, boolean exportFullList,
			boolean includeHeader, boolean decorateValues) {
		super.setParameters(tableModel, exportFullList, includeHeader,
				decorateValues);
	}

	/**
	 * @see org.displaytag.export.TextExportView#doExport(java.io.Writer)
	 */

	public void doExport(OutputStream out) throws IOException, JspException {
		HSSFCell outf =  null;
		HSSFRichTextString text= null;
		outf.setCellValue(text);
		//super.doExport(out);
	}

	/**
	 * @see org.displaytag.export.ExportView#getMimeType()
	 * @return "application/vnd.ms-excel"
	 */
	public String getMimeType() {

		return "application/vnd.ms-excel;charset=UTF-8"; //$NON-NLS-1$
	}

	/**
	 * @see org.displaytag.export.BaseExportView#getRowEnd()
	 */
	protected String getRowEnd() {
		return "\n"; //$NON-NLS-1$
	}

	/**
	 * @see org.displaytag.export.BaseExportView#getCellEnd()
	 */
	protected String getCellEnd() {
		return "\t"; //$NON-NLS-1$
	}

	/**
	 * @see org.displaytag.export.BaseExportView#getAlwaysAppendCellEnd()
	 * @return false
	 */
	protected boolean getAlwaysAppendCellEnd() {
		return false;
	}

	/**
	 * @see org.displaytag.export.BaseExportView#getAlwaysAppendRowEnd()
	 * @return false
	 */
	protected boolean getAlwaysAppendRowEnd() {
		return false;
	}

	/**
	 * Escaping for excel format.
	 * <ul>
	 * <li>Quotes inside quoted strings are escaped with a double quote</li>
	 * <li>Fields are surrounded by
	 * " (should be optional, but sometimes you get a "Sylk error" without
	 * those)</li>
	 * </ul>
	 * 
	 * @see org.displaytag.export.BaseExportView#escapeColumnValue(java.lang.Object)
	 */
	protected String escapeColumnValue(Object value) {
		if (value != null) {
			// quotes around fields are needed to avoid occasional
			// "Sylk format invalid" messages from excel
			return "\"" //$NON-NLS-1$
					+ StringUtils.replace(StringUtils.trim(value.toString()),
							"\"", "\"\"") //$NON-NLS-1$ //$NON-NLS-2$ 
					+ "\""; //$NON-NLS-1$ 
		}

		return null;
	}

	/**
	 * Wraps POI-generated exceptions.
	 * 
	 * @author Fabrizio Giustina
	 * @version $Revision$ ($Author$)
	 */
	static class HssfGenerationException extends BaseNestableJspTagException {
		/**
		 * D1597A17A6.
		 */
		private static final long serialVersionUID = 899149338534L;

		/**
		 * Instantiate a new PdfGenerationException with a fixed message and the
		 * given cause.
		 * 
		 * @param cause
		 *            Previous exception
		 */
		public HssfGenerationException(Throwable cause) {
			super(DefaultHssfExportView.class, Messages
					.getString("DefaultHssfExportView.errorexporting"), cause); //$NON-NLS-1$
		}

		/**
		 * @see org.displaytag.exception.BaseNestableJspTagException#getSeverity()
		 */
		public SeverityEnum getSeverity() {
			return SeverityEnum.ERROR;
		}
	}
}

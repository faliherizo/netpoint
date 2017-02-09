package com.netPoint.applications.site.displaytag.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import javax.servlet.jsp.JspException;

import org.displaytag.export.BaseExportView;
import org.displaytag.model.Column;
import org.displaytag.model.ColumnIterator;
import org.displaytag.model.Row;
import org.displaytag.model.RowIterator;
import org.displaytag.model.TableModel;

public abstract class TextBasedView extends BaseExportView {

    private TableModel model;
    private boolean exportFull;
    private boolean header;
    private boolean decorated;
	
	@Override
	protected abstract String escapeColumnValue(Object arg0);

	@Override
	protected abstract boolean getAlwaysAppendCellEnd();

	@Override
	protected abstract boolean getAlwaysAppendRowEnd();

	@Override
	protected abstract String getCellEnd();

	public abstract String getMimeType();

	@Override
	public void setParameters(TableModel tableModel, boolean exportFullList,
			boolean includeHeader, boolean decorateValues) {
        this.model = tableModel;
        this.exportFull = exportFullList;
        this.header = includeHeader;
        this.decorated = decorateValues;
	}

	public void doExport(Writer out) throws IOException, JspException {
		out= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test.xls"), "UTF8"));
        final String DOCUMENT_START = getDocumentStart();
        final String DOCUMENT_END = getDocumentEnd();
        final String ROW_START = getRowStart();
        final String ROW_END = getRowEnd();
        final String CELL_START = getCellStart();
        final String CELL_END = getCellEnd();
        final boolean ALWAYS_APPEND_CELL_END = getAlwaysAppendCellEnd();
        final boolean ALWAYS_APPEND_ROW_END = getAlwaysAppendRowEnd();

        // document start
        write(out, DOCUMENT_START);

        if (this.header)
        {
            write(out, doHeaders());
        }

        // get the correct iterator (full or partial list according to the exportFull field)
        RowIterator rowIterator = this.model.getRowIterator(this.exportFull);

        // iterator on rows
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();

            if (this.model.getTableDecorator() != null)
            {

                String stringStartRow = this.model.getTableDecorator().startRow();
                write(out, stringStartRow);
            }

            // iterator on columns
            ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());

            write(out, ROW_START);

            while (columnIterator.hasNext())
            {
                Column column = columnIterator.nextColumn();

                // Get the value to be displayed for the column
                String value = escapeColumnValue(column.getValue(this.decorated));

                write(out, CELL_START);

                write(out, value);

                if (ALWAYS_APPEND_CELL_END || columnIterator.hasNext())
                {
                    write(out, CELL_END);
                }
                
            }
            if (ALWAYS_APPEND_ROW_END || rowIterator.hasNext())
            {
                write(out, ROW_END);
            }
            if (this.model.getTableDecorator() != null)
            {
                String endRowStr = this.model.getTableDecorator().finishRow();
                write(out, endRowStr);
            }
        }

        // document end
        write(out, DOCUMENT_END);
	}

	private void write(Writer out, String string) throws IOException
    {
        if (string != null)
        {
            out.write(string);
        }
    }
}

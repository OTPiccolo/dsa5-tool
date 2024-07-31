package de.otpiccolo.dsa5.pdf.data.table;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import de.otpiccolo.dsa5.pdf.data.IDataWriter;

/**
 * Writer of a table, that gets other data writers and lays them out in a grid
 * format.
 */
public class TableWriter implements IDataWriter {

	private static final float CELL_INDENT = 5;

	private final TableData tableData;

	/**
	 * Constructor.
	 *
	 * @param tableData
	 *            The data of the table to write.
	 */
	public TableWriter(final TableData tableData) {
		this.tableData = tableData;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		final float[] columnSizeRatio = tableData.getColumnRatio();
		final float[] columnSize = new float[columnSizeRatio.length];
		for (int i = 0; i < columnSize.length; i++) {
			columnSize[i] = columnSizeRatio[i] * availableSpace.getWidth();
		}

		PDRectangle remainingSpace = new PDRectangle(availableSpace.getLowerLeftX(), availableSpace.getLowerLeftY(), availableSpace.getWidth(), availableSpace.getHeight());
		for (final List<IDataWriter> row : tableData.cellData()) {
			final float usedHeight = writeRow(doc, page, columnSize, remainingSpace, row);
			remainingSpace = new PDRectangle(remainingSpace.getLowerLeftX(), remainingSpace.getLowerLeftY(), remainingSpace.getWidth(), remainingSpace.getHeight() - usedHeight);
		}
		return remainingSpace;
	}

	private float writeRow(final PDDocument doc, final PDPage page, final float[] columnSize, final PDRectangle remainingSpace, final List<IDataWriter> rowData) throws IOException {
		final PDRectangle availableRowSpace = new PDRectangle(remainingSpace.getLowerLeftX(), remainingSpace.getLowerLeftY(), remainingSpace.getWidth(), remainingSpace.getHeight());
		float maxUsedHeight = 0;
		float lowerLeftX = availableRowSpace.getLowerLeftX();
		int index = 0;
		for (final IDataWriter writer : rowData) {
			final PDRectangle cellSpace = new PDRectangle(lowerLeftX, availableRowSpace.getLowerLeftY(), columnSize[index], availableRowSpace.getHeight());
			final PDRectangle indentedSpace = new PDRectangle(cellSpace.getLowerLeftX() + CELL_INDENT, cellSpace.getLowerLeftY() - CELL_INDENT, cellSpace.getWidth() - 2 * CELL_INDENT, cellSpace.getHeight() - 2 * CELL_INDENT);
			final PDRectangle spaceAfterwards = writer.writeData(doc, page, indentedSpace);
			maxUsedHeight = Math.max(maxUsedHeight, cellSpace.getHeight() - spaceAfterwards.getHeight());
			lowerLeftX += columnSize[index];
			index++;
		}
		return maxUsedHeight;
	}

}

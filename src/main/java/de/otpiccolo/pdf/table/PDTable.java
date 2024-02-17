package de.otpiccolo.pdf.table;

import java.io.IOException;
import java.util.List;

import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 * Implementation of a table.
 */
public class PDTable {

	private final PDDocument document;

	private PDPage page;

	private List<List<PDTableCell>> cellData;

	private BoundingBox boundingBox;

	private float pageMargin = 5;

	/**
	 * Constructor.
	 *
	 * @param document
	 *            The document the table will be in.
	 * @param page
	 *            The page the table will be in.
	 */
	public PDTable(final PDDocument document, final PDPage page) {
		this.document = document;
		this.page = page;
	}

	/**
	 * Writes out the table.
	 *
	 * @param horizontalOffset
	 *            Horizontal offset on the page.
	 * @param verticalOffset
	 *            Vertical offset on the page.
	 * @throws IOException
	 *             If the data cannot be written.
	 */
	public void writeTable(final float horizontalOffset, final float verticalOffset) throws IOException {
		// Compute the width and height of the columns and rows.
		boundingBox = new BoundingBox(horizontalOffset, verticalOffset, horizontalOffset, verticalOffset);
		final float[] rowsHeight = new float[cellData.size()];
		final float[] columnsWidth = new float[cellData.get(0).size()];
		for (int y = 0; y < cellData.size(); y++) {
			final List<PDTableCell> row = cellData.get(y);
			for (int x = 0; x < row.size(); x++) {
				final PDTableCell cell = row.get(x);
				final BoundingBox calculateSize = cell.calculateSize();
				columnsWidth[x] = Math.max(columnsWidth[x], calculateSize.getWidth());
				rowsHeight[y] = Math.max(rowsHeight[y], calculateSize.getHeight());
			}
		}

		// Compute the whole width and height of the table.
		float wholeWidth = 0;
		for (final float width : columnsWidth) {
			wholeWidth += width;
		}
		float wholeHeight = 0;
		for (final float height : rowsHeight) {
			wholeHeight += height;
		}

		// Compute the position of the table on the page.
		PDRectangle mediaBox = page.getMediaBox();
		boundingBox.setUpperRightX(horizontalOffset + wholeWidth);
		boundingBox.setLowerLeftY(Math.max(mediaBox.getLowerLeftY() + pageMargin, verticalOffset - wholeHeight));
		float currentYPos = verticalOffset;
		float remainingHeight = wholeHeight;

		PDPageContentStream content = null;
		try {
			content = new PDPageContentStream(document, page, AppendMode.APPEND, true, true);

			// Draw horizontal line, at the start of the table.
			content.moveTo(boundingBox.getLowerLeftX(), boundingBox.getUpperRightY());
			content.lineTo(boundingBox.getUpperRightX(), boundingBox.getUpperRightY());
			content.stroke();

			float rowHeight = 0;
			for (int i = 0; i < rowsHeight.length; i++) {
				final List<PDTableCell> row = cellData.get(i);
				rowHeight = rowsHeight[i];
				currentYPos -= rowHeight;
				remainingHeight -= rowHeight;

				// Current page ran out of space, so start a new one.
				// Don't check for zero, as we are calculating with imprecise
				// floats.
				if (currentYPos - boundingBox.getLowerLeftY() < -0.1f) {
					// Draw the vertical lines for the last page, as now it is
					// known how far they go.
					boundingBox.setLowerLeftY(currentYPos + rowHeight);
					drawVerticalLines(content, boundingBox, columnsWidth);

					// Some part of the page can still be blank, as a whole line
					// wouldn't fit, so add that part back to the remaining
					// height.
					remainingHeight += boundingBox.getLowerLeftY() - currentYPos;

					// Create the new page.
					content.close();
					page = new PDPage(page.getMediaBox());
					document.addPage(page);

					// Compute the size of the table on the page.
					mediaBox = page.getMediaBox();
					boundingBox.setUpperRightY(mediaBox.getUpperRightY() - pageMargin);
					boundingBox.setLowerLeftY(Math.max(mediaBox.getLowerLeftY() + pageMargin, boundingBox.getUpperRightY() - remainingHeight));

					content = new PDPageContentStream(document, page, AppendMode.APPEND, true, true);

					// Draw horizontal line, start of the new page.
					content.moveTo(boundingBox.getLowerLeftX(), boundingBox.getUpperRightY());
					content.lineTo(boundingBox.getUpperRightX(), boundingBox.getUpperRightY());
					content.stroke();

					currentYPos = boundingBox.getUpperRightY() - rowHeight;
				}

				// Writes the content of a row.
				writeRow(content, row, boundingBox.getLowerLeftX(), currentYPos, columnsWidth, rowHeight);

				// Draw the horizontal line that ends the row.
				content.moveTo(boundingBox.getLowerLeftX(), currentYPos);
				content.lineTo(boundingBox.getUpperRightX(), currentYPos);
				content.stroke();
			}

			// All content has been written, so draw the remaining vertical
			// lines.
			boundingBox.setLowerLeftY(currentYPos);
			drawVerticalLines(content, boundingBox, columnsWidth);

		} finally {
			if (content != null) {
				content.close();
			}
		}
	}

	private void drawVerticalLines(final PDPageContentStream content, final BoundingBox boundingBox, final float[] columnsWidth) throws IOException {
		// The most left line.
		float currentXPos = boundingBox.getLowerLeftX();
		content.moveTo(currentXPos, boundingBox.getLowerLeftY());
		content.lineTo(currentXPos, boundingBox.getUpperRightY());

		// The lines closing a column.
		for (final float columnWidth : columnsWidth) {
			currentXPos += columnWidth;
			content.moveTo(currentXPos, boundingBox.getLowerLeftY());
			content.lineTo(currentXPos, boundingBox.getUpperRightY());
		}

		content.stroke();
	}

	private void writeRow(final PDPageContentStream content, final List<PDTableCell> row, final float xPos, final float yPos, final float[] columnsWidth, final float rowHeight) throws IOException {
		float currentXPos = xPos;
		for (int i = 0; i < columnsWidth.length; i++) {
			final PDTableCell cell = row.get(i);
			final float width = columnsWidth[i];

			final BoundingBox cellBounds = new BoundingBox(currentXPos, yPos, currentXPos + width, yPos + rowHeight);
			cell.drawCell(document, content, cellBounds);
			currentXPos += width;
		}
	}

	/**
	 * The document the table will be in.
	 *
	 * @return The document.
	 */
	public PDDocument getDocument() {
		return document;
	}

	/**
	 * The page the table will be in.
	 *
	 * @return The page.
	 */
	public PDPage getPage() {
		return page;
	}

	/**
	 * The bounding box the table will be written in.
	 *
	 * @return The bounding box.
	 */
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	/**
	 * Gets the cell data that represents this table.
	 *
	 * @return The inner list is one row of the table (first index left column,
	 *         last index right column). The outer list represents each row.
	 */
	public List<List<PDTableCell>> getCellData() {
		return cellData;
	}

	/**
	 * Sets the cell data that represents this table.
	 *
	 * @param cellData
	 *            The inner list is one row of the table (first index left
	 *            column, last index right column). The outer list represents
	 *            each row.
	 */
	public void setCellData(final List<List<PDTableCell>> cellData) {
		this.cellData = cellData;
	}

	/**
	 * Gets the margin of the page.
	 *
	 * @return The margin.
	 */
	public float getPageMargin() {
		return pageMargin;
	}

	/**
	 * Sets the margin of the page.
	 *
	 * @param pageMargin
	 *            The margin.
	 */
	public void setPageMargin(final float pageMargin) {
		this.pageMargin = pageMargin;
	}

}

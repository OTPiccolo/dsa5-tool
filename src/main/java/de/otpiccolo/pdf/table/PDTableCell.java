package de.otpiccolo.pdf.table;

import java.io.IOException;

import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 * The table cell contains the data to draw a single cell of a table.
 *
 * @author OT Piccolo
 */
public abstract class PDTableCell {

	/**
	 * Horizontal alignment of the text. One of {@link SWT#BEGINNING},
	 * {@link SWT#CENTER} or {@link SWT#END}.
	 */
	public EAlignment horizontalAlignment = EAlignment.BEGINNING;

	/**
	 * Vertical alignment of the text. One of {@link EAlignment#BEGINNING},
	 * {@link EAlignment#CENTER} or {EAlignment SWT#END}.
	 */
	public EAlignment verticalAlignment = EAlignment.CENTER;

	/**
	 * Horizontal indent of the text.
	 */
	public int horizontalIndent = 5;

	/**
	 * Vertical indent of the text.
	 */
	public int verticalIndent = 5;

	/**
	 * Constructor.
	 */
	public PDTableCell() {
		// Do nothing.
	}

	/**
	 * Copy constructor.
	 *
	 * @param cell
	 *            The cell to copy all information from.
	 */
	public PDTableCell(final PDTableCell cell) {
		horizontalAlignment = cell.horizontalAlignment;
		horizontalIndent = cell.horizontalIndent;
		verticalAlignment = cell.verticalAlignment;
		verticalIndent = cell.verticalIndent;
	}

	/**
	 * Draws the content of the cell.
	 *
	 * @param doc
	 *            The PDF document.
	 * @param content
	 *            The content to draw in.
	 * @param cellBounds
	 *            The bounds of the cell to draw in.
	 * @throws IOException
	 *             If an exception during drawing happens.
	 */
	public abstract void drawCell(PDDocument doc, PDPageContentStream content, BoundingBox cellBounds) throws IOException;

	/**
	 * Calculates the size of the cell needed to be displayed.
	 *
	 * @return The calculated size depending on the content. The lower
	 *         coordinates are always zero.
	 */
	public abstract BoundingBox calculateSize();

}

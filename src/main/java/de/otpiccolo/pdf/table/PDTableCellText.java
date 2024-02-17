package de.otpiccolo.pdf.table;

import java.io.IOException;

import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * The table cell contains the data to draw a single cell of a table, containing
 * text.
 *
 * @author OT Piccolo
 */
public class PDTableCellText extends PDTableCell {

	/**
	 * The text to display in the table cell.
	 */
	public String text;

	/**
	 * The font used for the text.
	 */
	public PDFont font;

	/**
	 * The size of the font.
	 */
	public int fontSize;

	/**
	 * Constructor. <br>
	 * <br>
	 * {@link #text} and {@link #font} need to be set afterwards.
	 */
	public PDTableCellText() {
		// Do nothing.
	}

	/**
	 * Constructor.
	 *
	 * @param text
	 *            The text to display in the table cell.
	 * @param font
	 *            The font used for the text.
	 * @param fontSize
	 *            The size of the font.
	 */
	public PDTableCellText(final String text, final PDFont font, final int fontSize) {
		this.text = text;
		this.fontSize = fontSize;
		this.font = font;
	}

	/**
	 * Copy constructor.
	 *
	 * @param cell
	 *            The cell to copy all information from.
	 */
	public PDTableCellText(final PDTableCellText cell) {
		super(cell);
		text = cell.text;
		font = cell.font;
		fontSize = cell.fontSize;
	}

	@Override
	public void drawCell(final PDDocument doc, final PDPageContentStream content, final BoundingBox cellBounds) throws IOException {
		// Skip the cell if there is nothing to write.
		if (text == null || text.isEmpty()) {
			return;
		}

		final String[] lines = text.split("\n");
		final float yPos = calculateVerticalPositioning(lines.length, fontSize, cellBounds.getHeight());
		float xLineOffset = 0f;
		final float leading = 1.5f * fontSize;

		content.beginText();
		content.setFont(font, fontSize);
		content.newLineAtOffset(cellBounds.getLowerLeftX(), cellBounds.getUpperRightY() + yPos + leading / 2f);
		for (final String line : lines) {
			final float xPos = calculateHorizontalPositioning(line, cellBounds.getWidth());
			content.newLineAtOffset(xPos - xLineOffset, -leading);
			content.showText(line);
			xLineOffset = xPos;
		}
		content.endText();
	}

	@Override
	public BoundingBox calculateSize() {
		if (text == null || text.isBlank()) {
			return new BoundingBox(0f, 0f, 2f * horizontalIndent, 2f * verticalIndent);
		}

		final String lines[] = text.split("\n");
		final float height = fontSize * (lines.length * 1.5f - 0.5f);
		float maxWidth = 0f;
		try {
			for (final String line : lines) {
				maxWidth = Math.max(maxWidth, font.getStringWidth(line));
			}
		} catch (final IOException e) {
			e.printStackTrace();
			return new BoundingBox(0f, 0f, 2f * horizontalIndent, 2f * verticalIndent);
		}
		maxWidth = maxWidth / 1000f * fontSize;

		return new BoundingBox(0f, 0f, maxWidth + 2f * horizontalIndent, height + 2f * verticalIndent);
	}

	private float calculateHorizontalPositioning(final String text, final float cellSize) throws IOException {
		return switch (horizontalAlignment) {
		case BEGINNING -> horizontalIndent;
		case CENTER -> (cellSize - (font.getStringWidth(text) / 1000f * fontSize)) / 2f;
		case END -> cellSize - horizontalIndent - (font.getStringWidth(text) / 1000f * fontSize);
		};
	}

	private float calculateVerticalPositioning(final int lineCount, final float fontSize, final float cellSize) {
		return switch (verticalAlignment) {
		case BEGINNING -> -verticalIndent;
		case CENTER -> -(cellSize - (fontSize * (lineCount * 1.5f - 0.5f))) / 2f;
		case END -> verticalIndent - cellSize + fontSize * (lineCount * 1.5f - 0.5f);
		};
	}

}

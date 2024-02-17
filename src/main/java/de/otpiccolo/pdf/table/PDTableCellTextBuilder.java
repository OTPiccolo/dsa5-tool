package de.otpiccolo.pdf.table;

import org.apache.pdfbox.pdmodel.font.PDFont;

import de.otpiccolo.pdf.PDUtil;

/**
 * Builder for a {@link PDTableCell}.
 */
public class PDTableCellTextBuilder {

	/**
	 * Empty builder.
	 *
	 * @return The builder.
	 */
	public static final PDTableCellTextBuilder createEmpty() {
		return new PDTableCellTextBuilder();
	}

	/**
	 * Builder with default values set.
	 *
	 * @return The builder
	 */
	public static final PDTableCellTextBuilder createDefault() {
		return createEmpty().font(PDUtil.FONT, PDUtil.FONT_SIZE).hAlign(EAlignment.BEGINNING).vAlign(EAlignment.BEGINNING);
	}

	private final PDTableCellText cell = new PDTableCellText();

	/**
	 * Build the cell.
	 *
	 * @return The created cell.
	 */
	public final PDTableCellText build() {
		return new PDTableCellText(cell);
	}

	/**
	 * Sets the text to display.
	 *
	 * @param text
	 *            The text to display.
	 * @return This builder for chaining.
	 * @see PDTableCellText#text
	 */
	public final PDTableCellTextBuilder text(final String text) {
		cell.text = text;
		return this;
	}

	/**
	 * Sets the font for text to display.
	 *
	 * @param font
	 *            The font.
	 * @param fontSize
	 *            The font size.
	 * @return This builder for chaining.
	 * @see PDTableCellText#font
	 * @see PDTableCellText#fontSize
	 */
	public final PDTableCellTextBuilder font(final PDFont font, final int fontSize) {
		cell.font = font;
		cell.fontSize = fontSize;
		return this;
	}

	/**
	 * Horizontal alignment of the cell.
	 *
	 * @param hAlign
	 *            Horizontal alignment of the cell.
	 * @return This builder for chaining.
	 * @see PDTableCell#horizontalAlignment
	 */
	public final PDTableCellTextBuilder hAlign(final EAlignment hAlign) {
		cell.horizontalAlignment = hAlign;
		return this;
	}

	/**
	 * Vertical alignment of the cell.
	 *
	 * @param vAlign
	 *            Vertical alignment of the cell.
	 * @return This builder for chaining.
	 * @see PDTableCell#verticalAlignment
	 */
	public final PDTableCellTextBuilder vAlign(final EAlignment vAlign) {
		cell.verticalAlignment = vAlign;
		return this;
	}

	/**
	 * Horizontal indent of the cell.
	 *
	 * @param hIndent
	 *            Horizontal indent of the cell.
	 * @return This builder for chaining.
	 * @see PDTableCell#horizontalIndent
	 */
	public final PDTableCellTextBuilder hIndent(final int hIndent) {
		cell.horizontalIndent = hIndent;
		return this;
	}

	/**
	 * Vertical indent of the cell.
	 *
	 * @param vIndent
	 *            Vertical indent of the cell.
	 * @return This builder for chaining.
	 * @see PDTableCell#verticalIndent
	 */
	public final PDTableCellTextBuilder vIndet(final int vIndent) {
		cell.verticalIndent = vIndent;
		return this;
	}

}

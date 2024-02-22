package de.otpiccolo.dsa5.pdf.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import de.otpiccolo.pdf.PDUtil;

/**
 * Abstract data writer with some helper methods.
 */
public abstract class ADataWriter implements IDataWriter {

	/**
	 * Convenience method to create a data writer filled with information from
	 * its corresponding reader.
	 *
	 * @param <T>
	 *            The type of data writer used.
	 * @param <U>
	 *            The type of data reader used.
	 * @param <V>
	 *            The type of read data.
	 * @param writer
	 *            A function to create the writer with the read data.
	 * @param reader
	 *            A supplier for the reader.
	 * @param data
	 *            The data that needs to be read by the reader.
	 * @return The data writer, already filled with data.
	 */
	public static final <T extends IDataWriter, U extends IDataReader<String, V>, V> T fillWriter(final Function<Collection<V>, T> writer, final Supplier<U> reader, final String... data) {
		final List<V> readData = new ArrayList<>();
		final U suppliedReader = reader.get();
		for (final String date : data) {
			readData.add(suppliedReader.readData(date));
		}
		return writer.apply(readData);
	}

	private PDFont titleFont = PDUtil.FONT_ITALIC;
	private PDFont contentFont = PDUtil.FONT;
	private int fontSize = PDUtil.FONT_SIZE;

	/**
	 * Writes a title.
	 *
	 * @param content
	 *            The content to write on.
	 * @param vOffset
	 *            The vertical offset on the content.
	 * @param title
	 *            The title to write.
	 * @return The used height of the title.
	 * @throws IOException
	 *             If an error happened writing to the content stream.
	 */
	protected float writeTitle(final PDPageContentStream content, final float vOffset, final String title) throws IOException {
		final PDFont font = getTitleFont();
		final int fontSize = getFontSize();
		final float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

		content.beginText();
		content.setFont(font, fontSize);
		content.newLineAtOffset(PDUtil.PAGE_MARGIN, vOffset - titleHeight);
		content.showText(title);
		content.endText();
		return titleHeight;
	}

	/**
	 * Writes a paragraph.
	 *
	 * @param page
	 *            The page to write on.
	 * @param content
	 *            The content to write on.
	 * @param vOffset
	 *            The vertical offset on the content.
	 * @param paragraph
	 *            The paragraph to write. It will be automatically wrapped on
	 *            the page boundary.
	 * @return The used height of the paragraph.
	 * @throws IOException
	 *             If an error happened writing to the content stream.
	 */
	protected float writeParagraph(final PDPage page, final PDPageContentStream content, final float vOffset, final String paragraph) throws IOException {
		final PDFont font = getContentFont();
		final int fontSize = getFontSize();

		content.beginText();
		content.setFont(font, fontSize);
		content.newLineAtOffset(PDUtil.PAGE_MARGIN, vOffset);
		final float height = PDUtil.wrapText(paragraph, content, font, fontSize, page.getMediaBox().getWidth() - 2f * PDUtil.PAGE_MARGIN);
		content.endText();
		return height;
	}

	/**
	 * Gets the font for the title. Default is {@link PDUtil#FONT_ITALIC}.
	 *
	 * @return The font for the title.
	 */
	public PDFont getTitleFont() {
		return titleFont;
	}

	/**
	 * Sets the font for the title. Default is {@link PDUtil#FONT_ITALIC}.
	 *
	 * @param titleFont
	 *            The font for the title.
	 */
	public void setTitleFont(final PDFont titleFont) {
		this.titleFont = titleFont;
	}

	/**
	 * Gets the font for the content. Default is {@link PDUtil#FONT}.
	 *
	 * @return The font for the content.
	 */
	public PDFont getContentFont() {
		return contentFont;
	}

	/**
	 * Gets the font for the content. Default is {@link PDUtil#FONT}.
	 *
	 * @param contentFont
	 *            The font for the content.
	 */
	public void setContentFont(final PDFont contentFont) {
		this.contentFont = contentFont;
	}

	/**
	 * Gets the size of the font. Default is {@link PDUtil#FONT_SIZE}.
	 *
	 * @return The size of the font.
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * Sets the size of the font. Default is {@link PDUtil#FONT_SIZE}.
	 *
	 * @param fontSize
	 *            The size of the font.
	 */
	public void setFontSize(final int fontSize) {
		this.fontSize = fontSize;
	}

}

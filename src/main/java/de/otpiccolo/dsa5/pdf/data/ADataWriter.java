package de.otpiccolo.dsa5.pdf.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
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
	 * @param title
	 *            The title to write.
	 * @param content
	 *            The content to write on.
	 * @param availableSpace
	 *            The available space where data can be written to.
	 * @param spacing
	 *            Spacing to add for the next paragraph.
	 * @return The new available space. It is the available space given to this
	 *         function, without the used up space after data has been written.
	 * @throws IOException
	 *             If an error happened writing to the content stream.
	 */
	protected PDRectangle writeTitle(final String title, final PDPageContentStream content, final PDRectangle availableSpace, final float spacing) throws IOException {
		final PDFont font = getTitleFont();
		final int fontSize = getFontSize();
		final float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

		content.beginText();
		content.setFont(font, fontSize);
		content.newLineAtOffset(availableSpace.getLowerLeftX(), availableSpace.getUpperRightY() - titleHeight);
		content.showText(title);
		content.endText();
		final float height = titleHeight + spacing;

		if (isDrawRectangle()) {
			final PDRectangle rectangle = new PDRectangle(availableSpace.getLowerLeftX(), availableSpace.getUpperRightY() - height, availableSpace.getWidth(), height);
			PDUtil.drawRectangle(rectangle, content);
		}

		return new PDRectangle(availableSpace.getLowerLeftX(), availableSpace.getLowerLeftY(), availableSpace.getWidth(), availableSpace.getHeight() - height);
	}

	/**
	 * Writes a paragraph.
	 *
	 * @param paragraph
	 *            The paragraph to write. It will be automatically wrapped on
	 *            the page boundary.
	 * @param content
	 *            The content to write on.
	 * @param availableSpace
	 *            The available space where data can be written to.
	 * @param spacing
	 *            Spacing to add for the next paragraph.
	 * @return The new available space. It is the available space given to this
	 *         function, without the used up space after data has been written.
	 * @throws IOException
	 *             If an error happened writing to the content stream.
	 */
	protected PDRectangle writeParagraph(final String paragraph, final PDPageContentStream content, final PDRectangle availableSpace, final float spacing) throws IOException {
		final PDFont font = getContentFont();
		final int fontSize = getFontSize();
		final float fontHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

		content.beginText();
		content.setFont(font, fontSize);
		content.newLineAtOffset(availableSpace.getLowerLeftX(), availableSpace.getUpperRightY() - fontHeight);
		final float height = PDUtil.wrapText(paragraph, content, font, fontSize, availableSpace.getWidth()) + spacing;
		content.endText();

		if (isDrawRectangle()) {
			final PDRectangle rectangle = new PDRectangle(availableSpace.getLowerLeftX(), availableSpace.getUpperRightY() - height, availableSpace.getWidth(), height);
			PDUtil.drawRectangle(rectangle, content);
		}

		return new PDRectangle(availableSpace.getLowerLeftX(), availableSpace.getLowerLeftY(), availableSpace.getWidth(), availableSpace.getHeight() - height);
	}

	/**
	 * Draws a rectangle, if configured via {@link #isDrawRectangle()}.
	 *
	 * @param content
	 *            The content to draw on.
	 * @param newAvailableSpace
	 *            The available space after having written all data.
	 * @param oldAvailableSpace
	 *            The available space before having written all data.
	 * @throws IOException
	 *             If the content stream could not be written.
	 */
	protected void drawRectangle(final PDPageContentStream content, final PDRectangle newAvailableSpace, final PDRectangle oldAvailableSpace) throws IOException {
		if (isDrawRectangle()) {
			final float heightDif = oldAvailableSpace.getHeight() - newAvailableSpace.getHeight();
			final PDRectangle rectangle = new PDRectangle(newAvailableSpace.getLowerLeftX(), oldAvailableSpace.getUpperRightY() - heightDif, oldAvailableSpace.getWidth(), heightDif);
			PDUtil.drawRectangle(rectangle, content);
		}
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

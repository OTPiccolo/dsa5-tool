package de.otpiccolo.pdf;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Util class for PDFs.
 *
 * @author OT Piccolo
 */
public class PDUtil {

	/** Page margin to not write on */
	public static final float PAGE_MARGIN = 10f;
	/** Standard font to print in PDF */
	public static final PDFont FONT = PDType1Font.HELVETICA;
	/** Standard bold font to print in PDF */
	public static final PDFont FONT_BOLD = PDType1Font.HELVETICA_BOLD;
	/** Standard italic font to print in PDF */
	public static final PDFont FONT_ITALIC = PDType1Font.HELVETICA_BOLD_OBLIQUE;
	/** Standard font size */
	public static final int FONT_SIZE = 12;

	private PDUtil() {
		// Prevent instantiation.
	}

	/**
	 * Display a text, automatically wrapping it to a specific length on a page.
	 *
	 * @param text
	 *            The text to display.
	 * @param content
	 *            The page content to display it on. It needs to be aligned at
	 *            the starting position, horizontally and vertically of the
	 *            first character to display.
	 * @param font
	 *            The font to use.
	 * @param fontSize
	 *            The size of the font.
	 * @param paragraphWidth
	 *            The maximum width in pixels, after which the text will be
	 *            wrapped. Usually the media box of the page.
	 * @return The used height of the text after wrapping, in pixels.
	 * @throws IOException
	 *             If an error happened writing to the content stream.
	 */
	public static final float wrapText(final String text, final PDPageContentStream content, final PDFont font, final int fontSize, final float paragraphWidth) throws IOException {
		// Make sure that the first separation between lines isn't calculated
		// into the starting position.
		final float lineSpacing = 2f;
		float height = -lineSpacing;

		int startChar = 0;
		int endChar = 0;
		final float shiftHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize + lineSpacing;
		for (final int i : possibleWrapPoints(text)) {
			final float width = font.getStringWidth(text.substring(startChar, i)) / 1000 * fontSize;
			if (startChar < endChar && width > paragraphWidth) {
				// Draw partial text and increase height
				height += shiftHeight;
				content.newLineAtOffset(0f, -shiftHeight);
				content.showText(text.substring(startChar, endChar));
				startChar = endChar;
			}
			endChar = i;
		}

		// Last piece of text
		height += shiftHeight;
		content.newLineAtOffset(0f, -shiftHeight);
		content.showText(text.substring(startChar));

		// Return height of paragraph that is used.
		return height;
	}

	// Calculate possible points to split a sentence between words.
	private static final int[] possibleWrapPoints(final String text) {
		final String[] split = text.split("(?<=[^\\p{L}\\p{N}_])");
		final int[] ret = new int[split.length];
		ret[0] = split[0].length();
		for (int i = 1; i < split.length; i++) {
			ret[i] = ret[i - 1] + split[i].length();
		}
		return ret;
	}

}

package de.otpiccolo.pdf;

import java.io.IOException;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/**
 * Util class for PDFs.
 *
 * @author OT Piccolo
 */
public class PDUtil {

	/** Page margin to not write on */
	public static final float PAGE_MARGIN = 10f;
	/** Standard font to print in PDF */
	public static final PDFont FONT = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
	/** Standard bold font to print in PDF */
	public static final PDFont FONT_BOLD = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
	/** Standard italic font to print in PDF */
	public static final PDFont FONT_ITALIC = new PDType1Font(Standard14Fonts.FontName.HELVETICA_OBLIQUE);
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
		final float lineSpacing = 2f;
		float height = 0f;

		int startChar = 0;
		int endChar = 0;
		final float shiftHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize + lineSpacing;
		for (final int i : possibleWrapPoints(text)) {
			final float width = font.getStringWidth(text.substring(startChar, i)) / 1000 * fontSize;
			if (startChar < endChar && width > paragraphWidth) {
				// Draw partial text and increase height
				height += shiftHeight;
				content.showText(text.substring(startChar, endChar));
				content.newLineAtOffset(0f, -shiftHeight);
				startChar = endChar;
			}
			endChar = i;
		}

		// Last piece of text
		height += shiftHeight;
		content.showText(text.substring(startChar));

		// Return height of paragraph that is used (one line spacing too much).
		return height - lineSpacing;
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

	/**
	 * Copies a page, so the origin page will not be removed from its original
	 * document, when inserted into a new document.
	 *
	 * @param origin
	 *            The origin page.
	 * @return The copied page.
	 */
	public static final PDPage copyPage(final PDPage origin) {
		final COSDictionary pageDict = origin.getCOSObject();
		final COSDictionary newPageDict = new COSDictionary(pageDict);

		// Remove annotations, as they retain information to the old document.
		newPageDict.removeItem(COSName.ANNOTS);

		return new PDPage(newPageDict);
	}

	/**
	 * Returns a copy of the given rectangle.
	 *
	 * @param rectangle
	 *            The rectangle to copy.
	 * @return The copied rectangle.
	 */
	public static final PDRectangle copyRectangle(final PDRectangle rectangle) {
		return new PDRectangle(rectangle.getLowerLeftX(), rectangle.getLowerLeftY(), rectangle.getWidth(), rectangle.getHeight());
	}

	/**
	 * Draws a rectangle on the given content.
	 *
	 * @param rectangle
	 *            The rectangle to draw. Will be drawn at the position of the
	 *            four corners.
	 * @param content
	 *            The content to draw on.
	 * @throws IOException
	 *             If the content stream could not be written to.
	 */
	public static final void drawRectangle(final PDRectangle rectangle, final PDPageContentStream content) throws IOException {
		final float leftX = rectangle.getLowerLeftX();
		final float rightX = rectangle.getUpperRightX();
		final float lowerY = rectangle.getLowerLeftY();
		final float upperY = rectangle.getUpperRightY();
		content.moveTo(leftX, upperY);
		content.lineTo(rightX, upperY);
		content.lineTo(rightX, lowerY);
		content.lineTo(leftX, lowerY);
		content.lineTo(leftX, upperY);
		content.stroke();
	}

}

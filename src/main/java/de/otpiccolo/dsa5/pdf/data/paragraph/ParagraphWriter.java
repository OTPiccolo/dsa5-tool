package de.otpiccolo.dsa5.pdf.data.paragraph;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;
import de.otpiccolo.pdf.PDUtil;

/**
 * Class to write Paragraphen to a document.
 */
public class ParagraphWriter extends ADataWriter {

	private final String title;
	private final Collection<ParagraphData> paragraphs;

	/**
	 * A collection containing Paragraphen.
	 *
	 * @param paragraphs
	 *            The Paragraphen.
	 */
	public ParagraphWriter(final Collection<ParagraphData> paragraphs) {
		this(null, paragraphs);
	}

	/**
	 * A collection containing Paragraphen.
	 *
	 * @param title
	 *            An optional title for the Paragraphen.
	 * @param paragraphs
	 *            The Paragraphen.
	 */
	public ParagraphWriter(final String title, final Collection<ParagraphData> paragraphs) {
		this.title = title;
		this.paragraphs = paragraphs;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		// public float writeData(final PDDocument doc, final PDPage page, final
		// float verticalOffset) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			if (title != null) {
				space = writeTitle(title, content, space, 5f);
			}
			for (final ParagraphData data : paragraphs) {
				space = writeParagraph(data.paragraph(), content, space, 5f);
			}
			// Remove last Paragraph spacing.
			space.setUpperRightY(space.getUpperRightY() + 5f);
			drawRectangle(content, space, availableSpace);
		}

		return space;
	}

}

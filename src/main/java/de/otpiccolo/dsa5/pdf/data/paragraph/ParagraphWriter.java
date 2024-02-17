package de.otpiccolo.dsa5.pdf.data.paragraph;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;

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
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			if (title != null) {
				offset -= writeTitle(content, offset, title) + 5f;
			}
			for (final ParagraphData data : paragraphs) {
				offset -= writeParagraph(page, content, offset, data.paragraph()) + 5f;
			}
		}
		// Return height. Remove last Paragraph spacing.
		return verticalOffset - offset - 5f;
	}

}

package de.otpiccolo.dsa5.data.segen;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;

/**
 * Class to write Segen to a document.
 */
public class SegenWriter extends ADataWriter {

	private final Collection<SegenData> data;

	/**
	 * A collection containing Segen.
	 *
	 * @param data
	 *            The Predigten.
	 */
	public SegenWriter(final Collection<SegenData> data) {
		this.data = data;
	}

	@Override
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final SegenData segen : data) {
				offset -= writeTitle(content, offset, segen.name()) + 5f;
				offset -= writeParagraph(page, content, offset, segen.rule()) + 15f;
			}
		}
		// Return height. Remove last Segen spacing.
		return verticalOffset - offset - 15f;
	}

}

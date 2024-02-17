package de.otpiccolo.dsa5.data.predigt;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;

/**
 * Class to write Predigten to a document.
 */
public class PredigtWriter extends ADataWriter {

	private final Collection<PredigtData> data;

	/**
	 * A collection containing Predigten.
	 *
	 * @param data
	 *            The Predigten.
	 */
	public PredigtWriter(final Collection<PredigtData> data) {
		this.data = data;
	}

	@Override
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final PredigtData nachteil : data) {
				offset -= writeTitle(content, offset, nachteil.name()) + 5f;
				offset -= writeParagraph(page, content, offset, nachteil.rule()) + 15f;
			}
		}
		// Return height. Remove last Predigt spacing.
		return verticalOffset - offset - 15f;
	}

}

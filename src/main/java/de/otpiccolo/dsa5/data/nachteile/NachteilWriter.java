package de.otpiccolo.dsa5.data.nachteile;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;

/**
 * Class to write Nachteile to a document.
 */
public class NachteilWriter extends ADataWriter {

	private final Collection<NachteilData> data;

	/**
	 * A collection containing Nachteile.
	 *
	 * @param data
	 *            The Nachteile.
	 */
	public NachteilWriter(final Collection<NachteilData> data) {
		this.data = data;
	}

	@Override
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final NachteilData nachteil : data) {
				offset -= writeTitle(content, offset, nachteil.name()) + 5f;
				offset -= writeParagraph(page, content, offset, nachteil.rule()) + 15f;
			}
		}
		// Return height. Remove last Nachteil spacing.
		return verticalOffset - offset - 15f;
	}

}

package de.otpiccolo.dsa5.data.vorteile;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;

/**
 * Class to write Vorteile to a document.
 */
public class VorteilWriter extends ADataWriter {

	private final Collection<VorteilData> data;

	/**
	 * A collection containing Vorteile.
	 *
	 * @param data
	 *            The Vorteile.
	 */
	public VorteilWriter(final Collection<VorteilData> data) {
		this.data = data;
	}

	@Override
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final VorteilData vorteil : data) {
				offset -= writeTitle(content, offset, vorteil.name()) + 5f;
				offset -= writeParagraph(page, content, offset, vorteil.rule()) + 15f;
			}
		}
		// Return height. Remove last Vorteil spacing.
		return verticalOffset - offset - 15f;
	}

}

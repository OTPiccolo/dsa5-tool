package de.otpiccolo.dsa5.data.liturgien;

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
public class LiturgieWriter extends ADataWriter {

	private final Collection<LiturgieData> data;

	/**
	 * A collection containing Liturgie.
	 *
	 * @param data
	 *            The Liturgie.
	 */
	public LiturgieWriter(final Collection<LiturgieData> data) {
		this.data = data;
	}

	@Override
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final LiturgieData liturgie : data) {
				offset -= writeTitle(content, offset, liturgie.name()) + 5f;
				offset -= writeParagraph(page, content, offset, liturgie.effect()) + 5f;
				final String data = "Kosten: " + liturgie.cost() + " / Zauberzeit: " + liturgie.castTime();
				offset -= writeParagraph(page, content, offset, data) + 15f;
			}
		}
		// Return height. Remove last Liturgie spacing.
		return verticalOffset - offset - 15f;
	}

}

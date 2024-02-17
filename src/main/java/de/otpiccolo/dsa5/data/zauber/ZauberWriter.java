package de.otpiccolo.dsa5.data.zauber;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;

/**
 * Class to write Zauber to a document.
 */
public class ZauberWriter extends ADataWriter {

	private final Collection<ZauberData> data;

	/**
	 * A collection containing Zauber.
	 *
	 * @param data
	 *            The Zauber.
	 */
	public ZauberWriter(final Collection<ZauberData> data) {
		this.data = data;
	}

	@Override
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ZauberData zauber : data) {
				offset -= writeTitle(content, offset, zauber.name()) + 5f;
				offset -= writeParagraph(page, content, offset, zauber.effect()) + 5f;
				final String data = "Kosten: " + zauber.cost() + " / Zauberzeit: " + zauber.castTime();
				offset -= writeParagraph(page, content, offset, data) + 15f;
			}
		}
		// Return height. Remove last Zauber spacing.
		return verticalOffset - offset - 15f;
	}

}

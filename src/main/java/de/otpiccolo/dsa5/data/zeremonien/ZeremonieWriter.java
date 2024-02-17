package de.otpiccolo.dsa5.data.zeremonien;

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
public class ZeremonieWriter extends ADataWriter {

	private final Collection<ZeremonieData> data;

	/**
	 * A collection containing Zeremonie.
	 *
	 * @param data
	 *            The Zeremonie.
	 */
	public ZeremonieWriter(final Collection<ZeremonieData> data) {
		this.data = data;
	}

	@Override
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ZeremonieData zeremonie : data) {
				offset -= writeTitle(content, offset, zeremonie.name()) + 5f;
				offset -= writeParagraph(page, content, offset, zeremonie.effect()) + 5f;
				final String data = "Kosten: " + zeremonie.cost() + " / Zauberzeit: " + zeremonie.castTime();
				offset -= writeParagraph(page, content, offset, data) + 15f;
			}
		}
		// Return height. Remove last Zeremonie spacing.
		return verticalOffset - offset - 15f;
	}

}

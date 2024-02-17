package de.otpiccolo.dsa5.data.hexenfluch;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;

/**
 * Class to write Hexenflüche to a document.
 */
public class HexenfluchWriter extends ADataWriter {

	private final Collection<HexenfluchData> data;

	/**
	 * A collection containing Hexenflüche.
	 *
	 * @param data
	 *            The Hexenflüche.
	 */
	public HexenfluchWriter(final Collection<HexenfluchData> data) {
		this.data = data;
	}

	@Override
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final HexenfluchData hexenfluch : data) {
				offset -= writeTitle(content, offset, hexenfluch.name()) + 5f;
				offset -= writeParagraph(page, content, offset, hexenfluch.effect()) + 5f;
				final String data = "Kosten: " + hexenfluch.cost();
				offset -= writeParagraph(page, content, offset, data) + 15f;
			}
		}
		// Return height. Remove last Hexenfluch spacing.
		return verticalOffset - offset - 15f;
	}

}

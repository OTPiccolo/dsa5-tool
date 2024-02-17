package de.otpiccolo.dsa5.data.kampfsonderfertigkeiten;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;

/**
 * Class to write Kampfsonderfertigkeiten to a document.
 */
public class KampfsonderfertigkeitWriter extends ADataWriter {

	private final Collection<KampfsonderfertigkeitData> data;

	/**
	 * A collection containing Kampfsonderfertigkeiten.
	 *
	 * @param data
	 *            The Kampfsonderfertigkeiten.
	 */
	public KampfsonderfertigkeitWriter(final Collection<KampfsonderfertigkeitData> data) {
		this.data = data;
	}

	@Override
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final KampfsonderfertigkeitData kampfsonderfertigkeit : data) {
				offset -= writeTitle(content, offset, kampfsonderfertigkeit.name()) + 5f;
				offset -= writeParagraph(page, content, offset, kampfsonderfertigkeit.rule()) + 15f;
			}
		}
		// Return height. Remove last Kampfsonderfertigkeit spacing.
		return verticalOffset - offset - 15f;
	}

}

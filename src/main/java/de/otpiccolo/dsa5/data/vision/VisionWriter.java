package de.otpiccolo.dsa5.data.vision;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;

/**
 * Class to write Visionen to a document.
 */
public class VisionWriter extends ADataWriter {

	private final Collection<VisionData> data;

	/**
	 * A collection containing Visionen.
	 *
	 * @param data
	 *            The Visionen.
	 */
	public VisionWriter(final Collection<VisionData> data) {
		this.data = data;
	}

	@Override
	public float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException {
		float offset = verticalOffset;
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final VisionData vision : data) {
				offset -= writeTitle(content, offset, vision.name()) + 5f;
				offset -= writeParagraph(page, content, offset, vision.rule()) + 15f;
			}
		}
		// Return height. Remove last Vision spacing.
		return verticalOffset - offset - 15f;
	}

}

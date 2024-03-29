package de.otpiccolo.dsa5.data.vision;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;
import de.otpiccolo.pdf.PDUtil;

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
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final VisionData vision : data) {
				space = writeTitle(vision.name(), content, space, 5f);
				space = writeParagraph(vision.rule(), content, space, 15f);
			}
			// Remove last Vision spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

package de.otpiccolo.dsa5.data.weapon.schilde;

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
 * Class to write Schilde to a document.
 */
public class SchildWriter extends ADataWriter {

	private final Collection<SchildData> data;

	/**
	 * A collection containing Schilde.
	 *
	 * @param data
	 *            The Schilde.
	 */
	public SchildWriter(final Collection<SchildData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final SchildData schwert : data) {
				space = writeTitle(schwert.name(), content, space, 5f);
				space = writeParagraph("Vorteil: " + schwert.upside(), content, space, 5f);
				space = writeParagraph("Nachteil: " + schwert.downside(), content, space, 15f);
			}
			// Remove last Schild spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

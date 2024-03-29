package de.otpiccolo.dsa5.data.vorteile;

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
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final VorteilData vorteil : data) {
				space = writeTitle(vorteil.name(), content, space, 5f);
				space = writeParagraph(vorteil.rule(), content, space, 15f);
			}
			// Remove last Vorteil spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

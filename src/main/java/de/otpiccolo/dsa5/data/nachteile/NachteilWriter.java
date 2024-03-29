package de.otpiccolo.dsa5.data.nachteile;

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
 * Class to write Nachteile to a document.
 */
public class NachteilWriter extends ADataWriter {

	private final Collection<NachteilData> data;

	/**
	 * A collection containing Nachteile.
	 *
	 * @param data
	 *            The Nachteile.
	 */
	public NachteilWriter(final Collection<NachteilData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final NachteilData nachteil : data) {
				space = writeTitle(nachteil.name(), content, space, 5f);
				space = writeParagraph(nachteil.rule(), content, space, 15f);
			}
			// Remove last Nachteil spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

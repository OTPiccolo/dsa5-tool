package de.otpiccolo.dsa5.data.BannSchutzkreis;

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
 * Class to write Bann-/Schutzkreis to a document.
 */
public class BannSchutzkreisWriter extends ADataWriter {

	private final Collection<BannSchutzkreisData> data;

	/**
	 * A collection containing Bann-/Schutzkreise.
	 *
	 * @param data
	 *            The Bann-/Schutzkreise.
	 */
	public BannSchutzkreisWriter(final Collection<BannSchutzkreisData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final BannSchutzkreisData kreis : data) {
				space = writeTitle(kreis.name(), content, space, 5f);
				space = writeParagraph("Bannkreis: " + kreis.bannEffect(), content, space, 5f);
				space = writeParagraph("Schutzkreis: " + kreis.schutzEffect(), content, space, 5f);
				space = writeParagraph("Kosten: " + kreis.cost(), content, space, 15f);
			}
			// Remove last Bann-/Schutzkreis spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

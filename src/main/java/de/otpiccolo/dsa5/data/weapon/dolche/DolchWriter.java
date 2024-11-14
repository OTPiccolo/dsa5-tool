package de.otpiccolo.dsa5.data.weapon.dolche;

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
 * Class to write Dolche to a document.
 */
public class DolchWriter extends ADataWriter {

	private final Collection<DolchData> data;

	/**
	 * A collection containing Dolche.
	 *
	 * @param data
	 *            The Dolche.
	 */
	public DolchWriter(final Collection<DolchData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final DolchData dolch : data) {
				space = writeTitle(dolch.name(), content, space, 5f);
				space = writeParagraph("Vorteil: " + dolch.upside(), content, space, 5f);
				space = writeParagraph("Nachteil: " + dolch.downside(), content, space, 15f);
			}
			// Remove last Dolch spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

package de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten;

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
 * Class to write Allgemeine Sonderfertigkeiten to a document.
 */
public class AllgemeinesonderfertigkeitWriter extends ADataWriter {

	private final Collection<AllgemeinesonderfertigkeitData> data;

	/**
	 * A collection containing Allgemeine Sonderfertigkeiten.
	 *
	 * @param data
	 *            The Allgemeine Sonderfertigkeiten.
	 */
	public AllgemeinesonderfertigkeitWriter(final Collection<AllgemeinesonderfertigkeitData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final AllgemeinesonderfertigkeitData sonderfertigkeit : data) {
				space = writeTitle(sonderfertigkeit.name(), content, space, 5f);
				space = writeParagraph(sonderfertigkeit.rule(), content, space, 15f);
			}
			// Remove last Allgemeine Sonderfertigkeit spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

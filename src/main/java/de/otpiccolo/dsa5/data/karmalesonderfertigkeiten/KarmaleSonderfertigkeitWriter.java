package de.otpiccolo.dsa5.data.karmalesonderfertigkeiten;

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
 * Class to write Karmale Sonderfertigkeiten to a document.
 */
public class KarmaleSonderfertigkeitWriter extends ADataWriter {

	private final Collection<KarmaleSonderfertigkeitData> data;

	/**
	 * A collection containing Karmale Sonderfertigkeiten.
	 *
	 * @param data
	 *            The Karmale Sonderfertigkeiten.
	 */
	public KarmaleSonderfertigkeitWriter(final Collection<KarmaleSonderfertigkeitData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final KarmaleSonderfertigkeitData sonderfertigkeit : data) {
				space = writeTitle(sonderfertigkeit.name(), content, space, 5f);
				space = writeParagraph(sonderfertigkeit.rule(), content, space, 15f);
			}
			// Remove last spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

package de.otpiccolo.dsa5.data.magischesonderfertigkeiten;

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
 * Class to write Magische Sonderfertigkeiten to a document.
 */
public class MagischeSonderfertigkeitWriter extends ADataWriter {

	private final Collection<MagischeSonderfertigkeitData> data;

	/**
	 * A collection containing Magische Sonderfertigkeiten.
	 *
	 * @param data
	 *            The Magische Sonderfertigkeiten.
	 */
	public MagischeSonderfertigkeitWriter(final Collection<MagischeSonderfertigkeitData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final MagischeSonderfertigkeitData sonderfertigkeit : data) {
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

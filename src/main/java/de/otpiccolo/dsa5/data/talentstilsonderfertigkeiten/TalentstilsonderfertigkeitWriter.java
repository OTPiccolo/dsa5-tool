package de.otpiccolo.dsa5.data.talentstilsonderfertigkeiten;

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
 * Class to write Talentstilsonderfertigkeit to a document.
 */
public class TalentstilsonderfertigkeitWriter extends ADataWriter {

	private final Collection<TalentstilsonderfertigkeitData> data;

	/**
	 * A collection containing Talentstilsonderfertigkeit.
	 *
	 * @param data
	 *            The Talentstilsonderfertigkeit.
	 */
	public TalentstilsonderfertigkeitWriter(final Collection<TalentstilsonderfertigkeitData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final TalentstilsonderfertigkeitData talentstilsonderfertigkeit : data) {
				space = writeTitle(talentstilsonderfertigkeit.name(), content, space, 5f);
				space = writeParagraph(talentstilsonderfertigkeit.rule(), content, space, 15f);
			}
			// Remove last Talentstilsonderfertigkeit spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

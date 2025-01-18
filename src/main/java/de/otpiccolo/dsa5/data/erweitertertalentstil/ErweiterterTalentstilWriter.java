package de.otpiccolo.dsa5.data.erweitertertalentstil;

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
 * Class to write Erweiterter Talentstil to a document.
 */
public class ErweiterterTalentstilWriter extends ADataWriter {

	private final Collection<ErweiterterTalentstilData> data;

	/**
	 * A collection containing Erweiterter Talentstil.
	 *
	 * @param data
	 *            The Erweiterter Talentstil.
	 */
	public ErweiterterTalentstilWriter(final Collection<ErweiterterTalentstilData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ErweiterterTalentstilData talentstilsonderfertigkeit : data) {
				space = writeTitle(talentstilsonderfertigkeit.name(), content, space, 5f);
				space = writeParagraph(talentstilsonderfertigkeit.rule(), content, space, 15f);
			}
			// Remove last Erweiterter Talentstil spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

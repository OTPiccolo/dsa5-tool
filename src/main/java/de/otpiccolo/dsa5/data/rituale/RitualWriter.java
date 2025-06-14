package de.otpiccolo.dsa5.data.rituale;

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
 * Class to write Rituale to a document.
 */
public class RitualWriter extends ADataWriter {

	private final Collection<RitualData> data;

	/**
	 * A collection containing Rituale.
	 *
	 * @param data
	 *            The Ritual.
	 */
	public RitualWriter(final Collection<RitualData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final RitualData ritual : data) {
				space = writeTitle(ritual.name(), content, space, 5f);
				space = writeParagraph(ritual.effect(), content, space, 5f);
				final String data = "Kosten: " + ritual.cost() + " / Ritualdauer: " + ritual.castTime();
				space = writeParagraph(data, content, space, 15f);
			}
			// Remove last Ritual spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

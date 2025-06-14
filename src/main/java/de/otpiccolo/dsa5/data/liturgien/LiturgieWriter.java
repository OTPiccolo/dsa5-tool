package de.otpiccolo.dsa5.data.liturgien;

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
 * Class to write Liturgien to a document.
 */
public class LiturgieWriter extends ADataWriter {

	private final Collection<LiturgieData> data;

	/**
	 * A collection containing Liturgie.
	 *
	 * @param data
	 *            The Liturgie.
	 */
	public LiturgieWriter(final Collection<LiturgieData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final LiturgieData liturgie : data) {
				space = writeTitle(liturgie.name(), content, space, 5f);
				space = writeParagraph(liturgie.effect(), content, space, 5f);
				final String data = "Kosten: " + liturgie.cost() + " / Liturgiedauer: " + liturgie.castTime();
				space = writeParagraph(data, content, space, 15f);
			}
			// Remove last Liturgie spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

package de.otpiccolo.dsa5.data.zauber;

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
 * Class to write Zauber to a document.
 */
public class ZauberWriter extends ADataWriter {

	private final Collection<ZauberData> data;

	/**
	 * A collection containing Zauber.
	 *
	 * @param data
	 *            The Zauber.
	 */
	public ZauberWriter(final Collection<ZauberData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ZauberData zauber : data) {
				space = writeTitle(zauber.name(), content, space, 5f);
				space = writeParagraph(zauber.effect(), content, space, 5f);
				final String data = "Kosten: " + zauber.cost() + " / Zauberzeit: " + zauber.castTime();
				space = writeParagraph(data, content, space, 15f);
			}
			// Remove last Zauber spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

package de.otpiccolo.dsa5.data.gewandzauber;

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
 * Class to write Gewandzauber to a document.
 */
public class GewandzauberWriter extends ADataWriter {

	private final Collection<GewandzauberData> data;

	/**
	 * A collection containing Gewandzauber.
	 *
	 * @param data
	 *            The Gewandzauber.
	 */
	public GewandzauberWriter(final Collection<GewandzauberData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final GewandzauberData gewandzauber : data) {
				space = writeTitle(gewandzauber.name(), content, space, 5f);
				space = writeParagraph(gewandzauber.effect(), content, space, 5f);
				final String data = "Kosten: " + gewandzauber.cost() + " / Volumen: " + gewandzauber.volume();
				space = writeParagraph(data, content, space, 15f);
			}
			// Remove last Gewandzauber spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

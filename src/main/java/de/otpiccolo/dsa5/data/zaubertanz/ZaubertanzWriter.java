package de.otpiccolo.dsa5.data.zaubertanz;

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
 * Class to write Zaubertänze to a document.
 */
public class ZaubertanzWriter extends ADataWriter {

	private final Collection<ZaubertanzData> data;

	/**
	 * A collection containing Zaubertänze.
	 *
	 * @param data
	 *            The Zaubertänze.
	 */
	public ZaubertanzWriter(final Collection<ZaubertanzData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ZaubertanzData zaubertanz : data) {
				space = writeTitle(zaubertanz.name(), content, space, 5f);
				space = writeParagraph(zaubertanz.effect(), content, space, 5f);
				final String data = "Kosten: " + zaubertanz.cost() + " / Dauer: " + zaubertanz.duration();
				space = writeParagraph(data, content, space, 15f);
			}
			// Remove last Zaubertanz spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

package de.otpiccolo.dsa5.data.zaubererweiterung;

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
 * Class to write Zaubererweiterung to a document.
 */
public class ZaubererweiterungWriter extends ADataWriter {

	private final Collection<ZaubererweiterungData> data;

	/**
	 * A collection containing Zaubererweiterungen.
	 *
	 * @param data
	 *            The Zaubererweiterungen.
	 */
	public ZaubererweiterungWriter(final Collection<ZaubererweiterungData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ZaubererweiterungData zaubererweiterung : data) {
				space = writeTitle(zaubererweiterung.name() + " (" + zaubererweiterung.zauberName() + ")", content, space, 5f);
				space = writeParagraph(zaubererweiterung.effect(), content, space, 15f);
			}
			// Remove last Zauber spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

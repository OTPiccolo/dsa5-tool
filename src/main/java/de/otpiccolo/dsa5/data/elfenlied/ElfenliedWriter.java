package de.otpiccolo.dsa5.data.elfenlied;

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
 * Class to write Elfenlied to a document.
 */
public class ElfenliedWriter extends ADataWriter {

	private final Collection<ElfenliedData> data;

	/**
	 * A collection containing Elfenlieder.
	 *
	 * @param data
	 *            The Elfenlieder.
	 */
	public ElfenliedWriter(final Collection<ElfenliedData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ElfenliedData elfenlied : data) {
				space = writeTitle(elfenlied.name(), content, space, 5f);
				space = writeParagraph(elfenlied.effect(), content, space, 5f);
				space = writeParagraph("Kosten: " + elfenlied.cost(), content, space, 15f);
			}
			// Remove last spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

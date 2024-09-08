package de.otpiccolo.dsa5.data.zauberstilsonderfertigkeiten;

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
 * Class to write Zauberstilsonderfertigkeit to a document.
 */
public class ZauberstilsonderfertigkeitWriter extends ADataWriter {

	private final Collection<ZauberstilsonderfertigkeitData> data;

	/**
	 * A collection containing Zauberstilsonderfertigkeit.
	 *
	 * @param data
	 *            The Zauberstilsonderfertigkeit.
	 */
	public ZauberstilsonderfertigkeitWriter(final Collection<ZauberstilsonderfertigkeitData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ZauberstilsonderfertigkeitData zauberstil : data) {
				space = writeTitle(zauberstil.name(), content, space, 5f);
				space = writeParagraph(zauberstil.rule(), content, space, 15f);
			}
			// Remove last Zauberstilsonderfertigkeit spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

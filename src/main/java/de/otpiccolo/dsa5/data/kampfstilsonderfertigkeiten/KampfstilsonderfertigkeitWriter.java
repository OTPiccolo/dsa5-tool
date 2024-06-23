package de.otpiccolo.dsa5.data.kampfstilsonderfertigkeiten;

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
 * Class to write Kampfstilsonderfertigkeit to a document.
 */
public class KampfstilsonderfertigkeitWriter extends ADataWriter {

	private final Collection<KampfstilsonderfertigkeitData> data;

	/**
	 * A collection containing Kampfstilsonderfertigkeit.
	 *
	 * @param data
	 *            The Kampfstilsonderfertigkeit.
	 */
	public KampfstilsonderfertigkeitWriter(final Collection<KampfstilsonderfertigkeitData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final KampfstilsonderfertigkeitData kampfstilsonderfertigkeit : data) {
				space = writeTitle(kampfstilsonderfertigkeit.name(), content, space, 5f);
				space = writeParagraph(kampfstilsonderfertigkeit.rule(), content, space, 15f);
			}
			// Remove last Kampfstilsonderfertigkeit spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

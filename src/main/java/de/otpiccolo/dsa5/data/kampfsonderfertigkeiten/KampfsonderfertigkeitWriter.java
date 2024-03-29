package de.otpiccolo.dsa5.data.kampfsonderfertigkeiten;

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
 * Class to write Kampfsonderfertigkeiten to a document.
 */
public class KampfsonderfertigkeitWriter extends ADataWriter {

	private final Collection<KampfsonderfertigkeitData> data;

	/**
	 * A collection containing Kampfsonderfertigkeiten.
	 *
	 * @param data
	 *            The Kampfsonderfertigkeiten.
	 */
	public KampfsonderfertigkeitWriter(final Collection<KampfsonderfertigkeitData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final KampfsonderfertigkeitData kampfsonderfertigkeit : data) {
				space = writeTitle(kampfsonderfertigkeit.name(), content, space, 5f);
				space = writeParagraph(kampfsonderfertigkeit.rule(), content, space, 15f);
			}
			// Remove last Kampfsonderfertigkeit spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

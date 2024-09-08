package de.otpiccolo.dsa5.data.karmaletradition;

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
 * Class to write Karmale Traditionen to a document.
 */
public class KarmaleTraditionWriter extends ADataWriter {

	private final Collection<KarmaleTraditionData> data;

	/**
	 * A collection containing Karmale Traditionen.
	 *
	 * @param data
	 *            The Karmale Traditionen.
	 */
	public KarmaleTraditionWriter(final Collection<KarmaleTraditionData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final KarmaleTraditionData tradition : data) {
				space = writeTitle("Karmale Tradition: " + tradition.name(), content, space, 5f);
				space = writeParagraph("Leiteigenschaft: " + tradition.mainAttribute(), content, space, 5f);
				space = writeParagraph("Wohlgefällige Talente: " + tradition.preferredTalents(), content, space, 5f);
				for (final String rule : tradition.rules()) {
					space = writeParagraph(rule, content, space, 5f);
				}
				space.setUpperRightY(space.getUpperRightY() - 10f);
			}
			// Remove last Karmale Tradition spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

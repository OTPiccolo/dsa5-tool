package de.otpiccolo.dsa5.datazaubertradition;

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
 * Class to write Zauber Traditionen to a document.
 */
public class ZauberTraditionWriter extends ADataWriter {

	private final Collection<ZauberTraditionData> data;

	/**
	 * A collection containing Zauber Traditionen.
	 *
	 * @param data
	 *            The Zauber Traditionen.
	 */
	public ZauberTraditionWriter(final Collection<ZauberTraditionData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ZauberTraditionData tradition : data) {
				space = writeTitle("Zauber Tradition: " + tradition.name(), content, space, 5f);
				space = writeParagraph("Leiteigenschaft: " + tradition.mainAttribute(), content, space, 5f);
				for (final String rule : tradition.rules()) {
					space = writeParagraph(rule, content, space, 5f);
				}
				space.setUpperRightY(space.getUpperRightY() - 10f);
			}
			// Remove last Zauber Tradition spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

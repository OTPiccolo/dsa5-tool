package de.otpiccolo.dsa5.data.hexenfluch;

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
 * Class to write Hexenflüche to a document.
 */
public class HexenfluchWriter extends ADataWriter {

	private final Collection<HexenfluchData> data;

	/**
	 * A collection containing Hexenflüche.
	 *
	 * @param data
	 *            The Hexenflüche.
	 */
	public HexenfluchWriter(final Collection<HexenfluchData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final HexenfluchData hexenfluch : data) {
				space = writeTitle(hexenfluch.name(), content, space, 5f);
				space = writeParagraph(hexenfluch.effect(), content, space, 5f);
				final String data = "Kosten: " + hexenfluch.cost();
				space = writeParagraph(data, content, space, 15f);
			}
			// Remove last Hexenfluch spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

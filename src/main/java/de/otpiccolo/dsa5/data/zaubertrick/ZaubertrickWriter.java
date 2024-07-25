package de.otpiccolo.dsa5.data.zaubertrick;

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
 * Class to write Zaubertrick to a document.
 */
public class ZaubertrickWriter extends ADataWriter {

	private final Collection<ZaubertrickData> data;

	/**
	 * A collection containing Zaubertricks.
	 *
	 * @param data
	 *            The Zaubertricks.
	 */
	public ZaubertrickWriter(final Collection<ZaubertrickData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ZaubertrickData zaubertrick : data) {
				space = writeTitle(zaubertrick.name(), content, space, 5f);
				space = writeParagraph(zaubertrick.effect(), content, space, 15f);
			}
			// Remove last spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

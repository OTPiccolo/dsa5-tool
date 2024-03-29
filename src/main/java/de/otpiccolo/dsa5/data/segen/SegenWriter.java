package de.otpiccolo.dsa5.data.segen;

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
 * Class to write Segen to a document.
 */
public class SegenWriter extends ADataWriter {

	private final Collection<SegenData> data;

	/**
	 * A collection containing Segen.
	 *
	 * @param data
	 *            The Segen.
	 */
	public SegenWriter(final Collection<SegenData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final SegenData segen : data) {
				space = writeTitle(segen.name(), content, space, 5f);
				space = writeParagraph(segen.rule(), content, space, 15f);
			}
			// Remove last Segen spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

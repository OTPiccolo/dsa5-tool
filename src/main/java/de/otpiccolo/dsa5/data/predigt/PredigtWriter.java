package de.otpiccolo.dsa5.data.predigt;

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
 * Class to write Predigten to a document.
 */
public class PredigtWriter extends ADataWriter {

	private final Collection<PredigtData> data;

	/**
	 * A collection containing Predigten.
	 *
	 * @param data
	 *            The Predigten.
	 */
	public PredigtWriter(final Collection<PredigtData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final PredigtData predigt : data) {
				space = writeTitle(predigt.name(), content, space, 5f);
				space = writeParagraph(predigt.rule(), content, space, 15f);
			}
			// Remove last Predigt spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

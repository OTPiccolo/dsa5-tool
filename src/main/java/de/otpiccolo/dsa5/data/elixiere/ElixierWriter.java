package de.otpiccolo.dsa5.data.elixiere;

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
 * Class to write Elixiere to a document.
 */
public class ElixierWriter extends ADataWriter {

	private final Collection<ElixierData> data;

	/**
	 * A collection containing Elixiere.
	 *
	 * @param data
	 *            The Elixiere.
	 */
	public ElixierWriter(final Collection<ElixierData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ElixierData elixier : data) {
				space = writeTitle(elixier.name(), content, space, 5f);
				int qsNo = 1;
				for (final String qs : elixier.qs()) {
					space = writeParagraph("QS " + qsNo++ + ": " + qs, content, space, 5f);
				}
				space = writeParagraph("Kosten: " + elixier.cost() + " (Silber pro QS)", content, space, 15f);
			}
			// Remove last Elixier spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

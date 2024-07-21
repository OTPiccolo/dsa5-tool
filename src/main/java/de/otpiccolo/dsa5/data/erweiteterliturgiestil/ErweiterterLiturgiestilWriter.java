package de.otpiccolo.dsa5.data.erweiteterliturgiestil;

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
 * Class to write Erweiterte Liturgiestile to a document.
 */
public class ErweiterterLiturgiestilWriter extends ADataWriter {

	private final Collection<ErweiterterLiturgiestilData> data;

	/**
	 * A collection containing Erweiterte Liturgiestile.
	 *
	 * @param data
	 *            The ErweiterteLiturgiestile.
	 */
	public ErweiterterLiturgiestilWriter(final Collection<ErweiterterLiturgiestilData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final ErweiterterLiturgiestilData liturgiestil : data) {
				space = writeTitle(liturgiestil.name(), content, space, 5f);
				space = writeParagraph(liturgiestil.rule(), content, space, 15f);
			}
			// Remove last spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

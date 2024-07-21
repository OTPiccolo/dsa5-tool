package de.otpiccolo.dsa5.data.liturgiestil;

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
 * Class to write Liturgiestile to a document.
 */
public class LiturgiestilWriter extends ADataWriter {

	private final Collection<LiturgiestilData> data;

	/**
	 * A collection containing Liturgiestile.
	 *
	 * @param data
	 *            The Liturgiestile.
	 */
	public LiturgiestilWriter(final Collection<LiturgiestilData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final LiturgiestilData liturgiestil : data) {
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

package de.otpiccolo.dsa5.pdf.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import de.otpiccolo.dsa5.pdf.data.IDataWriter;
import de.otpiccolo.pdf.PDUtil;

/**
 * Page to write a summary. Uses {@link IDataWriter}s to write the data.
 */
public class DefaultPage implements IPage {

	private final List<IDataWriter> writers = new ArrayList<>();
	private String title;

	private PDPage page;

	/**
	 * Constructor.
	 */
	public DefaultPage() {
		this(null);
	}

	/**
	 * Constructor.
	 *
	 * @param title
	 *            The title of the page.
	 */
	public DefaultPage(final String title) {
		this.title = title;
	}

	@Override
	public void writePage(final PDDocument doc) throws IOException {
		page = new PDPage(PDRectangle.A4);
		doc.addPage(page);

		final float pageMargin = PDUtil.PAGE_MARGIN;
		final PDRectangle mediaBox = page.getMediaBox();
		PDRectangle availableSpace = new PDRectangle(mediaBox.getLowerLeftX() + pageMargin, mediaBox.getLowerLeftY() + pageMargin, mediaBox.getWidth() - 2 * pageMargin, mediaBox.getHeight() - 2 * pageMargin);

		if (getTitle() != null) {
			try (PDPageContentStream content = new PDPageContentStream(doc, page)) {
				writeTitle(content, availableSpace);
				availableSpace.setUpperRightY(availableSpace.getUpperRightY() - 20f);
			}
		}

		for (final IDataWriter writer : getWriters()) {
			availableSpace = writer.writeData(doc, page, availableSpace);
			availableSpace.setUpperRightY(availableSpace.getUpperRightY() - 15f);
		}
	}

	private void writeTitle(final PDPageContentStream content, final PDRectangle availableSpace) throws IOException {
		final int fontSize = PDUtil.FONT_SIZE * 2;
		final PDFont font = PDUtil.FONT_BOLD;
		final float titleWidth = font.getStringWidth(getTitle()) / 1000 * fontSize;
		final float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

		content.beginText();
		content.setFont(font, fontSize);
		content.newLineAtOffset((availableSpace.getWidth() - titleWidth) / 2, availableSpace.getUpperRightY() - titleHeight);
		content.showText(getTitle());
		content.endText();

		availableSpace.setUpperRightY(availableSpace.getUpperRightY() - titleHeight);
	}

	/**
	 * Gets the title of the summary page.
	 *
	 * @return The title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the summary page.
	 *
	 * @param title
	 *            The title.
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * A list to add the {@link IDataWriter}s to that should be written.
	 *
	 * @return The list of {@link IDataWriter}s. Add to this list.
	 */
	public List<IDataWriter> getWriters() {
		return writers;
	}

}

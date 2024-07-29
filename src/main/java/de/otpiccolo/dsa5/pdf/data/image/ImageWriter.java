package de.otpiccolo.dsa5.pdf.data.image;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import de.otpiccolo.dsa5.pdf.data.IDataWriter;

/**
 * Class to write an image to a document.
 */
public class ImageWriter implements IDataWriter {

	private final ImageData data;

	/**
	 * Constructor
	 *
	 * @param data
	 *            The image data to write.
	 */
	public ImageWriter(final ImageData data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		final PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, data.imgData(), null);
		final PDRectangle imgPos = calculateImagePlacement(availableSpace, pdImage);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			drawImage(pdImage, content, imgPos);
		}
		return new PDRectangle(availableSpace.getLowerLeftX(), availableSpace.getLowerLeftY(), availableSpace.getWidth(), availableSpace.getHeight() - imgPos.getHeight());
	}

	private PDRectangle calculateImagePlacement(final PDRectangle availableSpace, final PDImage image) {
		// Image is small enough to fit into available space.
		if (image.getWidth() < availableSpace.getWidth() && image.getHeight() < availableSpace.getHeight()) {
			return new PDRectangle(availableSpace.getLowerLeftX(), availableSpace.getLowerLeftY() + (availableSpace.getHeight() - image.getHeight()), image.getWidth(), image.getHeight());
		}

		// Retain aspect ratio of image.
		// Try to fill image horizontally without clipping.
		final float horizontalScaling = availableSpace.getWidth() / image.getWidth();
		final float verticalOffset = availableSpace.getHeight() - (image.getHeight() * horizontalScaling);
		final PDRectangle horizontalFit = new PDRectangle(availableSpace.getLowerLeftX(), availableSpace.getLowerLeftY() + verticalOffset, image.getWidth() * horizontalScaling, image.getHeight() * horizontalScaling);
		if (horizontalFit.getHeight() <= availableSpace.getHeight()) {
			return horizontalFit;
		}

		// Fit vertically.
		final float verticalScaling = availableSpace.getHeight() / image.getHeight();
		final PDRectangle verticalFit = new PDRectangle(availableSpace.getLowerLeftX(), availableSpace.getLowerLeftY(), image.getWidth() * verticalScaling, image.getHeight() * verticalScaling);
		return verticalFit;
	}

	private void drawImage(final PDImageXObject pdImage, final PDPageContentStream content, final PDRectangle position) throws IOException {
		content.drawImage(pdImage, position.getLowerLeftX(), position.getLowerLeftY(), position.getWidth(), position.getHeight());
	}

}

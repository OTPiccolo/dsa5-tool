package de.otpiccolo.pdf.table;

import java.io.IOException;

import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * The table cell contains the data to draw a single cell of a table, containing
 * an image.
 *
 * @author OT Piccolo
 */
public class PDTableCellImage extends PDTableCell {

	/**
	 * The image to display in the table cell.
	 */
	public byte[] image;

	/**
	 * The size of the image.
	 */
	public BoundingBox size;

	/**
	 * Keep aspect ratio of image.
	 */
	public boolean keepAspectRatio = true;

	/**
	 * Constructor. <br>
	 * <br>
	 * {@link #image} and {@link #size} need to be set afterwards.
	 */
	public PDTableCellImage() {
		// Do nothing.
	}

	/**
	 * Constructor.
	 *
	 * @param image
	 *            The URL to the image to display in the table cell.
	 * @param size
	 *            The size of the image.
	 */
	public PDTableCellImage(final byte[] image, final BoundingBox size) {
		this.image = image;
		this.size = size;
	}

	/**
	 * Copy constructor.
	 *
	 * @param cell
	 *            The cell to copy all information from.
	 */
	public PDTableCellImage(final PDTableCellImage cell) {
		super(cell);
		image = cell.image;
		size = cell.size;
		keepAspectRatio = cell.keepAspectRatio;
	}

	@Override
	public void drawCell(final PDDocument doc, final PDPageContentStream content, final BoundingBox cellBounds) throws IOException {
		final PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, image, null);
		final BoundingBox imgPosition = positionImage(cellBounds, pdImage.getWidth(), pdImage.getHeight());
		content.drawImage(pdImage, imgPosition.getLowerLeftX(), imgPosition.getLowerLeftY(), imgPosition.getWidth(), imgPosition.getHeight());
	}

	@Override
	public BoundingBox calculateSize() {
		return new BoundingBox(0f, 0f, size.getWidth() + 2 * horizontalIndent, size.getHeight() + 2 * verticalIndent);
	}

	private BoundingBox positionImage(final BoundingBox cellBounds, final float imgWidth, final float imgHeight) {
		// This calculation with keepAspectRatio only works for quadratic cells.
		final float displayedWidth = size.getWidth() * (keepAspectRatio && imgWidth < imgHeight ? imgWidth / imgHeight : 1.0f);
		final float displayedHeight = size.getHeight() * (keepAspectRatio && imgHeight < imgWidth ? imgHeight / imgWidth : 1.0f);
		final float xPos = cellBounds.getLowerLeftX() + calculateHorizontalPositioning(cellBounds.getWidth(), displayedWidth);
		final float yPos = cellBounds.getLowerLeftY() + calculateVerticalPositioning(cellBounds.getHeight(), displayedHeight);
		return new BoundingBox(xPos, yPos, xPos + displayedWidth, yPos + displayedHeight);
	}

	private float calculateHorizontalPositioning(final float cellSize, final float imgWidth) {
		return switch (horizontalAlignment) {
		case BEGINNING -> horizontalIndent;
		case CENTER -> (cellSize - imgWidth) / 2f;
		case END -> cellSize - horizontalIndent - imgWidth;
		};
	}

	private float calculateVerticalPositioning(final float cellSize, final float imgHeight) {
		return switch (verticalAlignment) {
		case BEGINNING -> verticalIndent;
		case CENTER -> (cellSize - imgHeight) / 2f;
		case END -> cellSize - verticalIndent - imgHeight;
		};
	}

}

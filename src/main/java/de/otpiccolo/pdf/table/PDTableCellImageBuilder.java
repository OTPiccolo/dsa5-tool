package de.otpiccolo.pdf.table;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.fontbox.util.BoundingBox;

/**
 * Builder for a {@link PDTableCell}.
 */
public class PDTableCellImageBuilder {

	/**
	 * Empty builder.
	 *
	 * @return The builder.
	 */
	public static final PDTableCellImageBuilder createEmpty() {
		return new PDTableCellImageBuilder();
	}

	/**
	 * Builder with default values set.
	 *
	 * @return The builder
	 */
	public static final PDTableCellImageBuilder createDefault() {
		return createEmpty().hAlign(EAlignment.CENTER).vAlign(EAlignment.CENTER);
	}

	private final PDTableCellImage cell = new PDTableCellImage();

	private URL imgUrl;

	/**
	 * Build the cell.
	 *
	 * @return The created cell.
	 */
	public final PDTableCellImage build() {
		if (imgUrl != null) {
			System.out.println("Loading image from: " + imgUrl.toString());
			try (InputStream is = new BufferedInputStream(imgUrl.openStream())) {
				cell.image = is.readAllBytes();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return new PDTableCellImage(cell);
	}

	/**
	 * Sets the the image to display.
	 *
	 * @param image
	 *            The image.
	 * @return This builder for chaining.
	 * @see PDTableCell#i
	 */
	public final PDTableCellImageBuilder image(final byte[] image) {
		cell.image = image;
		return this;
	}

	/**
	 * Sets the URL of the image to display.
	 *
	 * @param imgUrl
	 *            The URL of the image.
	 * @return This builder for chaining.
	 * @see PDTableCell#i
	 */
	public final PDTableCellImageBuilder imgUrl(final URL imgUrl) {
		this.imgUrl = imgUrl;
		return this;
	}

	/**
	 * Sets the size of the image to display.
	 *
	 * @param size
	 *            The size of the image.
	 * @return This builder for chaining.
	 */
	public final PDTableCellImageBuilder size(final BoundingBox size) {
		cell.size = size;
		return this;
	}

	/**
	 * Sets the size of the image to display.
	 *
	 * @param width
	 *            The width of the image.
	 * @param height
	 *            The height of the image.
	 * @return This builder for chaining.
	 */
	public final PDTableCellImageBuilder size(final int width, final int height) {
		cell.size = new BoundingBox(0f, 0f, width, height);
		return this;
	}

	/**
	 * Whether to keep the aspect ratio of the image intact or not.
	 *
	 * @param aspectRatio
	 *            <code>true</code>, to keep the aspect ratio of the image
	 *            intact, <code>false</code> if it should be stretched to fill
	 *            out the whole size.
	 * @return This builder for chaining.
	 */
	public final PDTableCellImageBuilder aspectRatio(final boolean aspectRatio) {
		cell.keepAspectRatio = aspectRatio;
		return this;
	}

	/**
	 * Horizontal alignment of the cell.
	 *
	 * @param hAlign
	 *            Horizontal alignment of the cell.
	 * @return This builder for chaining.
	 * @see PDTableCell#horizontalAlignment
	 */
	public final PDTableCellImageBuilder hAlign(final EAlignment hAlign) {
		cell.horizontalAlignment = hAlign;
		return this;
	}

	/**
	 * Vertical alignment of the cell.
	 *
	 * @param vAlign
	 *            Vertical alignment of the cell.
	 * @return This builder for chaining.
	 * @see PDTableCell#verticalAlignment
	 */
	public final PDTableCellImageBuilder vAlign(final EAlignment vAlign) {
		cell.verticalAlignment = vAlign;
		return this;
	}

	/**
	 * Horizontal indent of the cell.
	 *
	 * @param hIndent
	 *            Horizontal indent of the cell.
	 * @return This builder for chaining.
	 * @see PDTableCell#horizontalIndent
	 */
	public final PDTableCellImageBuilder hIndent(final int hIndent) {
		cell.horizontalIndent = hIndent;
		return this;
	}

	/**
	 * Vertical indent of the cell.
	 *
	 * @param vIndent
	 *            Vertical indent of the cell.
	 * @return This builder for chaining.
	 * @see PDTableCell#verticalIndent
	 */
	public final PDTableCellImageBuilder vIndet(final int vIndent) {
		cell.verticalIndent = vIndent;
		return this;
	}

}

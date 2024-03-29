package de.otpiccolo.dsa5.pdf.data;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 * An interface to write arbitrary data to a document.
 */
public interface IDataWriter {

	/**
	 * Whether a rectangle should be written around the used up space by this
	 * data writer. Default is <code>false</code>.
	 *
	 * @return <code>true</code>, if a rectangle should be written around the
	 *         space that this data writer used, <code>false</code> otherwise.
	 */
	default boolean isDrawRectangle() {
		return true;
	}

	/**
	 * Writes data to a document.
	 *
	 * @param doc
	 *            The document to write on.
	 * @param page
	 *            The page to write on.
	 * @param availableSpace
	 *            The available space where data can be written to.
	 * @return The new available space. It is the available space given to this
	 *         function, without the used up space after data has been written.
	 * @throws IOException
	 *             If an error happened writing to the document.
	 */
	PDRectangle writeData(final PDDocument doc, final PDPage page, PDRectangle availableSpace) throws IOException;

}

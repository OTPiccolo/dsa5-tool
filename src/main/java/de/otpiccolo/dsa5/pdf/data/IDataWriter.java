package de.otpiccolo.dsa5.pdf.data;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 * An interface to write arbitrary data to a document.
 */
public interface IDataWriter {

	/**
	 * Writes data to a document.
	 *
	 * @param doc
	 *            The document to write on.
	 * @param page
	 *            The page to write on.
	 * @param verticalOffset
	 *            The vertical offset to start writing to.
	 * @return The consumed height of the data.
	 * @throws IOException
	 *             If an error happened writing to the document.
	 */
	float writeData(final PDDocument doc, final PDPage page, final float verticalOffset) throws IOException;

}

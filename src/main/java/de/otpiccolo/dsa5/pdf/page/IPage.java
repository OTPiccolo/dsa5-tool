package de.otpiccolo.dsa5.pdf.page;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Interface for writing a page to a document.
 */
public interface IPage {

	/**
	 * Constructor.
	 *
	 * @param doc
	 *            The document to write on. Will create a new page for it.
	 * @throws IOException
	 *             If a writing exception in the document happens.
	 */
	void writePage(final PDDocument doc) throws IOException;

}

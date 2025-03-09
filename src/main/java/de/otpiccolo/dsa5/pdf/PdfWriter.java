package de.otpiccolo.dsa5.pdf;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.otpiccolo.dsa5.pdf.page.IPage;

/**
 * Writes a PDF with page contents.
 */
public class PdfWriter {

	private static final Logger LOG = LoggerFactory.getLogger(PdfWriter.class);

	private File source;
	private File destination;
	private Stream<IPage> pages;

	/**
	 * Writes the PDF.
	 *
	 * @throws IOException
	 *             If an exception happens on reading the source PDF, or writing
	 *             the generated PDF.
	 */
	public void writeDocument() throws IOException {
		final PDDocument doc = loadDoc(getSource());
		writePages(doc, getPages());
		saveDoc(doc, getDestination());
	}

	private PDDocument loadDoc(final File file) throws IOException {
		LOG.info(file == null ? "Creating empty PDF." : "Loading source PDF from: " + file);
		return file == null ? new PDDocument() : Loader.loadPDF(file);
	}

	private void saveDoc(final PDDocument doc, final File file) throws IOException {
		LOG.info("Saving PDF to: {}", file);
		if (file != null) {
			doc.save(file);
		}
	}

	private void writePages(final PDDocument doc, final Stream<IPage> pages) {
		LOG.info("Writing PDF data.");
		pages.forEach(p -> {
			try {
				p.writePage(doc);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Gets the file of the source PDF. If not given, an empty PDF is used.
	 *
	 * @return The file name of the source PDF.
	 */
	public File getSource() {
		return source;
	}

	/**
	 * Sets the file of the source PDF. If not given, an empty PDF is used.
	 *
	 * @param source
	 *            The file name of the source PDF.
	 */
	public void setSource(final File source) {
		this.source = source;
	}

	/**
	 * Gets the file of the destination PDF. If not given, no PDF will be
	 * written.
	 *
	 * @return The file name of the destination PDF.
	 */
	public File getDestination() {
		return destination;
	}

	/**
	 * Gets the file of the destination PDF. If not given, no PDF will be
	 * written.
	 *
	 * @param destination
	 *            The file name of the destination PDF.
	 */
	public void setDestination(final File destination) {
		this.destination = destination;
	}

	/**
	 * Gets the stream of pages written to the PDF. If not given, no content
	 * will be written. If a source PDF was given, these pages will be appended
	 * at the end.
	 *
	 * @return The stream of pages.
	 */
	public Stream<IPage> getPages() {
		return pages;
	}

	/**
	 * Gets the stream of pages written to the PDF. If not given, no content
	 * will be written. If a source PDF was given, these pages will be appended
	 * at the end.
	 *
	 * @param pages
	 *            The stream of pages.
	 */
	public void setPages(final Stream<IPage> pages) {
		this.pages = pages;
	}

}

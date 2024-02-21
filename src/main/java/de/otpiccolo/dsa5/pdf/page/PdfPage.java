package de.otpiccolo.dsa5.pdf.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import de.otpiccolo.pdf.PDUtil;

/**
 * A page copying the PDF contents of another PDF.
 */
public class PdfPage implements IPage {

	private final PDDocument content;
	private final Collection<Integer> pageIndices;

	/**
	 * Constructor.
	 *
	 * @param content
	 *            The document to fully copy.
	 */
	public PdfPage(final PDDocument content) {
		this(content, new ArrayList<>(0));
	}

	/**
	 * Constructor
	 *
	 * @param content
	 *            The document to copy a page from.
	 * @param pageIndex
	 *            The page index to copy.
	 */
	public PdfPage(final PDDocument content, final int pageIndex) {
		this(content, Arrays.asList(pageIndex));
	}

	/**
	 * Constructor.
	 *
	 * @param content
	 *            The document to copy pages from.
	 * @param pageIndices
	 *            A collection containing the page indices to copy.
	 */
	public PdfPage(final PDDocument content, final Collection<Integer> pageIndices) {
		this.content = content;
		this.pageIndices = pageIndices;
	}

	@Override
	public void writePage(final PDDocument doc) throws IOException {
		if (getPageIndices().isEmpty()) {
			for (final Iterator<PDPage> iter = content.getPages().iterator(); iter.hasNext();) {
				final PDPage page = PDUtil.copyPage(iter.next());
				doc.addPage(page);
			}
		} else {
			for (final Integer pageNumber : getPageIndices()) {
				final PDPage page = PDUtil.copyPage(content.getPage(pageNumber));
				doc.addPage(page);
			}
		}
	}

	/**
	 * The page indices to copy from the original document. If this is empty,
	 * the whole document will be copied.
	 *
	 * @return The page indices.
	 */
	public Collection<Integer> getPageIndices() {
		return pageIndices;
	}

}

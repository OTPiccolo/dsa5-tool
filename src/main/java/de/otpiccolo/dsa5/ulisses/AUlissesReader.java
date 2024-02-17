package de.otpiccolo.dsa5.ulisses;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class to read data from the internet page Ulisses.
 *
 * @param <T>
 *            The type of data that will be read.
 */
public abstract class AUlissesReader<T> implements IUlissesReader<T> {

	private static final String ULISSES_URL = "https://dsa.ulisses-regelwiki.de";

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Loads the page on Ulisses.
	 *
	 * @param page
	 *            The page to load. Needs to be an absolute path within the
	 *            domain of Ulisses.
	 * @return The loaded HTML document.
	 * @throws IOException
	 *             If an exception happened during loading of the page.
	 */
	protected Document loadDocument(final String page) throws IOException {
		final String url = ULISSES_URL + page;
		getLog().info("Reading data from: {}", url);
		return Jsoup.connect(url).get();
	}

	/**
	 * Gets content of a table cell.
	 *
	 * @param root
	 *            The root element to look for the data. Usually the HTML
	 *            {@link Document}.
	 * @param name
	 *            The name of the data to retrieve, like displayed on the page.
	 *            For example, to get the 'Probe' entry, use
	 *            <code>getDataInTable(root, "Probe")</code>.
	 * @return The data in the table. If the given name is not present,
	 *         <code>"&lt;Not Found&gt;"</code> is returned.
	 */
	protected String getDataInTable(final Element root, final String name) {
		final String key = name + ":";
		boolean found = false;
		for (final Element element : root.selectXpath("//div[@class='body']/div")) {
			// Desired data is in next element.
			if (found) {
				return element.text();
			}
			found = key.equals(element.text());
		}
		return "<Not Found>";
	}

	/**
	 * Gets the logger.
	 *
	 * @return The log.
	 */
	protected Logger getLog() {
		return log;
	}

}

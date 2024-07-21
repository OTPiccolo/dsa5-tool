package de.otpiccolo.dsa5.data.zeremonialgegenstand;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Zeremonialgegenstandssonderfertigkeit from an Internet
 * source.
 */
public class ZeremonialgegenstandReader extends AUlissesReader<ZeremonialgegenstandData> {

	@Override
	public ZeremonialgegenstandData readData(final String name) {
		getLog().info("Getting data for \"{}\" Zeremonialgegenstandssonderfertigkeit.", name);
		String rule;
		String tradition;

		try {
			final Document doc = loadDocument(getPage(name));
			tradition = getDataInTable(doc, "Tradition");
			rule = getDataInTable(doc, "Wirkung");
		} catch (final IOException e) {
			getLog().error("Error reading Zeremonialgegenstandssonderfertigkeit \"" + name + "\". Error message: " + e.getMessage(), e);
			tradition = "<N/A>";
			rule = e.getMessage();
		}

		return new ZeremonialgegenstandData(name, tradition, rule);
	}

	private String getPage(final String sonderfertigkeitName) {
		return "/zeremonialgegenstands_sf.html?sonderfertigkeit=" + sonderfertigkeitName;
	}

}

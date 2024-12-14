package de.otpiccolo.dsa5.data.magischesonderfertigkeiten;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Magische Sonderfertigkeiten from an Internet source.
 */
public class MagischeSonderfertigkeitReader extends AUlissesReader<MagischeSonderfertigkeitData> {

	@Override
	public MagischeSonderfertigkeitData readData(final String name) {
		getLog().info("Getting data for \"{}\" Magische Sonderfertigkeit.", name);
		String rule;

		try {
			final Document doc = loadDocument(getPage(name));
			rule = getDataInTable(doc, "Regel");
		} catch (final IOException e) {
			getLog().error("Error reading Magische Sonderfertigkeit \"" + name + "\". Error message: " + e.getMessage(), e);
			rule = e.getMessage();
		}

		return new MagischeSonderfertigkeitData(name, rule);
	}

	private String getPage(final String sonderfertigkeitName) {
		return "/allgemeine_magische_sonderfertigkeit.html?sonderfertigkeit=" + sonderfertigkeitName;
	}

}

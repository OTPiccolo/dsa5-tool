package de.otpiccolo.dsa5.data.karmalesonderfertigkeiten;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Karmale Sonderfertigkeiten from an Internet source.
 */
public class KarmaleSonderfertigkeitReader extends AUlissesReader<KarmaleSonderfertigkeitData> {

	@Override
	public KarmaleSonderfertigkeitData readData(final String name) {
		getLog().info("Getting data for \"{}\" Karmale Sonderfertigkeit.", name);
		String rule;

		try {
			final Document doc = loadDocument(getPage(name));
			rule = getDataInTable(doc, "Regel");
		} catch (final IOException e) {
			getLog().error("Error reading Karmale Sonderfertigkeit \"" + name + "\". Error message: " + e.getMessage(), e);
			rule = e.getMessage();
		}

		return new KarmaleSonderfertigkeitData(name, rule);
	}

	private String getPage(final String sonderfertigkeitName) {
		return "/allgemeine_karmale_sonderfertigkeit.html?sonderfertigkeit=" + sonderfertigkeitName;
	}

}

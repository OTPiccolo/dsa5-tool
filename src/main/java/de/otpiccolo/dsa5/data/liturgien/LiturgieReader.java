package de.otpiccolo.dsa5.data.liturgien;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Liturgie from an Internet source.
 */
public class LiturgieReader extends AUlissesReader<LiturgieData> {

	@Override
	public LiturgieData readData(final String name) {
		getLog().info("Getting data for \"{}\" Liturgie.", name);
		String effect;
		String cost;
		String castTime;

		try {
			final Document doc = loadDocument(getPage(name));
			effect = getDataInTable(doc, "Wirkung");
			castTime = getDataInTable(doc, "Liturgiedauer");
			cost = getDataInTable(doc, "KaP-Kosten");
		} catch (final IOException e) {
			getLog().error("Error reading Liturgie \"" + name + "\". Error message: " + e.getMessage(), e);
			effect = e.getMessage();
			castTime = null;
			cost = null;
		}
		return new LiturgieData(name, castTime, cost, effect);
	}

	private String getPage(final String liturgieName) {
		return "/liturgie.html?liturgie=" + liturgieName;
	}

}

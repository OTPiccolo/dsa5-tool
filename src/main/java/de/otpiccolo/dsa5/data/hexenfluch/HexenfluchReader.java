package de.otpiccolo.dsa5.data.hexenfluch;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Hexenfluch from an Internet source.
 */
public class HexenfluchReader extends AUlissesReader<HexenfluchData> {

	@Override
	public HexenfluchData readData(final String name) {
		getLog().info("Getting data for \"{}\" Hexenfluch.", name);
		String effect;
		String cost;

		try {
			final Document doc = loadDocument(getPage(name));
			effect = getDataInTable(doc, "Wirkung");
			cost = getDataInTable(doc, "AsP-Kosten");
		} catch (final IOException e) {
			getLog().error("Error reading Hexenfluch \"" + name + "\". Error message: " + e.getMessage(), e);
			effect = e.getMessage();
			cost = null;
		}
		return new HexenfluchData(name, cost, effect);
	}

	private String getPage(final String hexenfluchName) {
		return "/hexenfluch.html?hexenfluch=" + hexenfluchName;
	}

}

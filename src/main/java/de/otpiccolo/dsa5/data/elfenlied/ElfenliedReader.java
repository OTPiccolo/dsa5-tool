package de.otpiccolo.dsa5.data.elfenlied;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Elfenlied from an Internet source.
 */
public class ElfenliedReader extends AUlissesReader<ElfenliedData> {

	@Override
	public ElfenliedData readData(final String name) {
		getLog().info("Getting data for \"{}\" Zauber.", name);
		String effect;
		String cost;

		try {
			final Document doc = loadDocument(getPage(name));
			effect = getDataInTable(doc, "Wirkung");
			cost = getDataInTable(doc, "AsP-Kosten");
		} catch (final IOException e) {
			getLog().error("Error reading Zauber \"" + name + "\". Error message: " + e.getMessage(), e);
			effect = e.getMessage();
			cost = null;
		}
		return new ElfenliedData(name, cost, effect);
	}

	private String getPage(final String zauberName) {
		return "/elfenlied.html?elfenlied=" + zauberName;
	}

}

package de.otpiccolo.dsa5.data.nachteile;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Nachteil from an Internet source.
 */
public class NachteilReader extends AUlissesReader<NachteilData> {

	@Override
	public NachteilData readData(final String name) {
		getLog().info("Getting data for \"{}\" Nachteil.", name);
		String rule;
		try {
			final Document doc = loadDocument(getPage(name));
			rule = getDataInTable(doc, "Regel");
		} catch (final IOException e) {
			getLog().error("Error reading Nachteil \"" + name + "\". Error message: " + e.getMessage(), e);
			rule = e.getMessage();
		}
		return new NachteilData(name, rule);
	}

	private String getPage(final String nachteilName) {
		return "/nachteil.html?nachteil=" + nachteilName;
	}

}

package de.otpiccolo.dsa5.data.zaubertrick;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Zaubertrick from an Internet source.
 */
public class ZaubertrickReader extends AUlissesReader<ZaubertrickData> {

	@Override
	public ZaubertrickData readData(final String name) {
		getLog().info("Getting data for \"{}\" Zaubertrick.", name);
		String effect;

		try {
			final Document doc = loadDocument(getPage(name));
			effect = getDataInTable(doc, "Wirkung");
		} catch (final IOException e) {
			getLog().error("Error reading Zaubertrick \"" + name + "\". Error message: " + e.getMessage(), e);
			effect = e.getMessage();
		}
		return new ZaubertrickData(name, effect);
	}

	private String getPage(final String zauberName) {
		return "/zaubertrick.html?zaubertrick=" + zauberName;
	}

}

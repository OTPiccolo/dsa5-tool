package de.otpiccolo.dsa5.data.segen;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Segen from an Internet source.
 */
public class SegenReader extends AUlissesReader<SegenData> {

	@Override
	public SegenData readData(final String name) {
		getLog().info("Getting data for \"{}\" Segen.", name);
		String rule;
		try {
			final Document doc = loadDocument(getPage(name));
			rule = getDataInTable(doc, "Wirkung");
		} catch (final IOException e) {
			getLog().error("Error reading Segen \"" + name + "\". Error message: " + e.getMessage(), e);
			rule = e.getMessage();
		}
		return new SegenData(name, rule);
	}

	private String getPage(final String segenName) {
		return "/segen.html?segen=" + segenName;
	}

}

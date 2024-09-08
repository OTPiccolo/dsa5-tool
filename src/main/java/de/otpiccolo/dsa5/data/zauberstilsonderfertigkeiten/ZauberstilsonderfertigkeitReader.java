package de.otpiccolo.dsa5.data.zauberstilsonderfertigkeiten;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Zauberstilsonderfertigkeit from an Internet source.
 */
public class ZauberstilsonderfertigkeitReader extends AUlissesReader<ZauberstilsonderfertigkeitData> {

	@Override
	public ZauberstilsonderfertigkeitData readData(final String name) {
		getLog().info("Getting data for \"{}\" Zauberstilsonderfertigkeit.", name);
		String rule;

		try {
			final Document doc = loadDocument(getPage(name));
			rule = getDataInTable(doc, "Regel");
		} catch (final IOException e) {
			getLog().error("Error reading Zauberstilsonderfertigkeit \"" + name + "\". Error message: " + e.getMessage(), e);
			rule = e.getMessage();
		}
		return new ZauberstilsonderfertigkeitData(name, rule);
	}

	private String getPage(final String zauberstilName) {
		return "/zauberstil_sf.html?zauberstil=" + zauberstilName;
	}

}

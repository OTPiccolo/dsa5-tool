package de.otpiccolo.dsa5.data.vorteile;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Vorteil from an Internet source.
 */
public class VorteilReader extends AUlissesReader<VorteilData> {

	@Override
	public VorteilData readData(final String name) {
		getLog().info("Getting data for \"{}\" Vorteil.", name);
		String rule;
		try {
			final Document doc = loadDocument(getPage(name));
			rule = getDataInTable(doc, "Regel");
		} catch (final IOException e) {
			getLog().error("Error reading Vorteil \"" + name + "\". Error message: " + e.getMessage(), e);
			rule = e.getMessage();
		}
		return new VorteilData(name, rule);
	}

	private String getPage(final String vorteilName) {
		return "/vorteil.html?vorteil=" + vorteilName;
	}

}

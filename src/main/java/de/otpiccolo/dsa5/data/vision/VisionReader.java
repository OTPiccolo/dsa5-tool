package de.otpiccolo.dsa5.data.vision;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Vision from an Internet source.
 */
public class VisionReader extends AUlissesReader<VisionData> {

	@Override
	public VisionData readData(final String name) {
		getLog().info("Getting data for \"{}\" Vision.", name);
		String rule;
		try {
			final Document doc = loadDocument(getPage(name));
			rule = getDataInTable(doc, "Regel");
		} catch (final IOException e) {
			getLog().error("Error reading Vision \"" + name + "\". Error message: " + e.getMessage(), e);
			rule = e.getMessage();
		}
		return new VisionData(name, rule);
	}

	private String getPage(final String visionName) {
		return "/vision.html?vision=" + visionName;
	}

}

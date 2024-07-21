package de.otpiccolo.dsa5.data.liturgiestil;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Liturgiestil from an Internet source.
 */
public class LiturgiestilReader extends AUlissesReader<LiturgiestilData> {

	@Override
	public LiturgiestilData readData(final String name) {
		getLog().info("Getting data for \"{}\" Liturgiestil.", name);
		String rule;

		try {
			final Document doc = loadDocument(getPage(name));
			rule = getDataInTable(doc, "Regel");
		} catch (final IOException e) {
			getLog().error("Error reading Liturgiestil \"" + name + "\". Error message: " + e.getMessage(), e);
			rule = e.getMessage();
		}

		return new LiturgiestilData(name, rule);
	}

	private String getPage(final String liturgiestilName) {
		return "/liturgiestilsonderfertigkeit.html?liturgiestil=" + liturgiestilName;
	}

}

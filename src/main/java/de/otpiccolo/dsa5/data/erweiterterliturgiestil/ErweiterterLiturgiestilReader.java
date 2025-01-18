package de.otpiccolo.dsa5.data.erweiterterliturgiestil;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Erweiteter Liturgiestil from an Internet source.
 */
public class ErweiterterLiturgiestilReader extends AUlissesReader<ErweiterterLiturgiestilData> {

	@Override
	public ErweiterterLiturgiestilData readData(final String name) {
		getLog().info("Getting data for \"{}\" Erweiteter Liturgiestil.", name);
		String rule;
		String matchingLiturgiestil;

		try {
			final Document doc = loadDocument(getPage(name));
			rule = getDataInTable(doc, "Regel");
			matchingLiturgiestil = getDataInTable(doc, "passender Liturgiestil");
		} catch (final IOException e) {
			getLog().error("Error reading Erweiteter Liturgiestil \"" + name + "\". Error message: " + e.getMessage(), e);
			matchingLiturgiestil = "<N/A>";
			rule = e.getMessage();
		}

		return new ErweiterterLiturgiestilData(name, rule, matchingLiturgiestil);
	}

	private String getPage(final String liturgiestilName) {
		return "/erweiterte_liturgiestilsonderfertigkeit.html?erw_lit_sf=" + liturgiestilName;
	}

}

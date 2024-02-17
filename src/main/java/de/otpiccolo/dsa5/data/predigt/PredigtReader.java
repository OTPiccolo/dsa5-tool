package de.otpiccolo.dsa5.data.predigt;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Predigt from an Internet source.
 */
public class PredigtReader extends AUlissesReader<PredigtData> {

	@Override
	public PredigtData readData(final String name) {
		getLog().info("Getting data for \"{}\" Predigt.", name);
		String rule;
		try {
			final Document doc = loadDocument(getPage(name));
			rule = getDataInTable(doc, "Regel");
		} catch (final IOException e) {
			getLog().error("Error reading Predigt \"" + name + "\". Error message: " + e.getMessage(), e);
			rule = e.getMessage();
		}
		return new PredigtData(name, rule);
	}

	private String getPage(final String nachteilName) {
		return "/predigt.html?predigt=" + nachteilName;
	}

}

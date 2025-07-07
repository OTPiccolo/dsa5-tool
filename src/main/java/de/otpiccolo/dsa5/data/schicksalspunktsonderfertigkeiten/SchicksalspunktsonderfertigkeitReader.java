package de.otpiccolo.dsa5.data.schicksalspunktsonderfertigkeiten;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Schicksalspunkt-Sonderfertigkeit from an Internet source.
 */
public class SchicksalspunktsonderfertigkeitReader extends AUlissesReader<SchicksalspunktsonderfertigkeitData> {

	private Map<String, String> pages;

	@Override
	public SchicksalspunktsonderfertigkeitData readData(final String name) {
		getLog().info("Getting data for \"{}\" Schicksalspunkt-Sonderfertigkeit.", name);
		String rule;

		final String pageName = getPage(name);
		if (pageName == null) {
			getLog().warn("Could not find Schicksalspunkt-Sonderfertigkeit with name \"{}\".", name);
			rule = "<Unknown Schicksalspunkt-Sonderfertigkeit>";
		} else {
			try {
				final Document doc = loadDocument(pageName);
				rule = getData(doc, "Regel");
			} catch (final IOException e) {
				getLog().error("Error reading Schicksalspunkt-Sonderfertigkeit \"" + name + "\". Error message: " + e.getMessage(), e);
				rule = e.getMessage();
			}
		}

		return new SchicksalspunktsonderfertigkeitData(name, rule);
	}

	private String getPage(final String sonderfertigkeitName) {
		if (pages == null) {
			pages = readSonderfertigkeiten();
		}
		return pages.get(sonderfertigkeitName);
	}

	private Map<String, String> readSonderfertigkeiten() {
		getLog().info("Initializinge Schicksalspunkt-Sonderfertigkeit data.");
		Document doc;
		try {
			doc = loadDocument("/SF_Schick.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of Schicksalspunkt-Sonderfertigkeit. Error message: " + e.getMessage(), e);
			return Collections.emptyMap();
		}

		final Map<String, String> map = new HashMap<>();
		for (final Element element : doc.selectXpath("//a[@class='ulSubMenu']")) {
			map.put(element.attr("title"), "/" + element.attr("href"));
		}
		return map;
	}

	private String getData(final Element root, final String name) {
		final String key = name + ": ";
		for (final Element element : root.selectXpath("//div[@class='ce_text block']/p")) {
			if (element.text().startsWith(name)) {
				return element.text().substring(key.length());
			}
		}
		return "<Not Found>";
	}

}

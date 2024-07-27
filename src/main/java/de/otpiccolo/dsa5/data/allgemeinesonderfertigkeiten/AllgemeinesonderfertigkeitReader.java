package de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Allgemeine Sonderfertigkeit from an Internet source.
 */
public class AllgemeinesonderfertigkeitReader extends AUlissesReader<AllgemeinesonderfertigkeitData> {

	private Map<String, String> pages;

	@Override
	public AllgemeinesonderfertigkeitData readData(final String name) {
		getLog().info("Getting data for \"{}\" Allgemeine Sonderfertigkeit.", name);
		String rule;

		final String pageName = getPage(name);
		if (pageName == null) {
			getLog().warn("Could not find Allgemeine Sonderfertigkeit with name \"{}\".", name);
			rule = "<Unknown Allgemeine Sonderfertigkeit>";
		} else {
			try {
				final Document doc = loadDocument(pageName);
				rule = getData(doc, "Regel");
			} catch (final IOException e) {
				getLog().error("Error reading Allgemeine Sonderfertigkeit \"" + name + "\". Error message: " + e.getMessage(), e);
				rule = e.getMessage();
			}
		}

		return new AllgemeinesonderfertigkeitData(name, rule);
	}

	private String getPage(final String sonderfertigkeitName) {
		if (pages == null) {
			pages = readSonderfertigkeiten();
		}
		return pages.get(sonderfertigkeitName);
	}

	private Map<String, String> readSonderfertigkeiten() {
		getLog().info("Initializinge Allgemeine Sonderfertigkeit data.");
		Document doc;
		try {
			doc = loadDocument("/sf_allgemeine_sonderfertigkeiten.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of Allgemeine Sonderfertigkeit. Error message: " + e.getMessage(), e);
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

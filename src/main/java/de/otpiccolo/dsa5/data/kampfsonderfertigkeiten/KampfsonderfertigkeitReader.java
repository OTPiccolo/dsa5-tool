package de.otpiccolo.dsa5.data.kampfsonderfertigkeiten;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Kampfsonderfertigkeit from an Internet source.
 */
public class KampfsonderfertigkeitReader extends AUlissesReader<KampfsonderfertigkeitData> {

	private Map<String, String> pages;

	@Override
	public KampfsonderfertigkeitData readData(final String name) {
		getLog().info("Getting data for \"{}\" Kampfsonderfertigkeit.", name);
		String rule;

		final String pageName = getPage(name);
		if (pageName == null) {
			getLog().warn("Could not find Kampfsonderfertigkeit with name \"{}\".", name);
			rule = "<Unknown Kampfsonderfertigkeit>";
		} else {
			try {
				final Document doc = loadDocument(pageName);
				rule = getData(doc, "Regel");
			} catch (final IOException e) {
				getLog().error("Error reading Kampfsonderfertigkeit \"" + name + "\". Error message: " + e.getMessage(), e);
				rule = e.getMessage();
			}
		}

		return new KampfsonderfertigkeitData(name, rule);
	}

	private String getPage(final String kampfsonderfertigkeitName) {
		if (pages == null) {
			pages = readKampfsonderfertigkeiten();
		}
		return pages.get(kampfsonderfertigkeitName);
	}

	private Map<String, String> readKampfsonderfertigkeiten() {
		getLog().info("Initializinge Kampfsonderfertigkeiten data.");
		Document doc;
		try {
			doc = loadDocument("/sf_kampfsonderfertigkeiten.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of Kampfsonderfertigkeiten. Error message: " + e.getMessage(), e);
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

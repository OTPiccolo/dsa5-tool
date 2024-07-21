package de.otpiccolo.dsa5.data.kampfstilsonderfertigkeiten;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Kampfstilsonderfertigkeit from an Internet source.
 */
public class KampfstilsonderfertigkeitReader extends AUlissesReader<KampfstilsonderfertigkeitData> {

	private Map<String, String> pages;

	@Override
	public KampfstilsonderfertigkeitData readData(final String name) {
		getLog().info("Getting data for \"{}\" Kampfstilsonderfertigkeit.", name);
		String rule;

		final String pageName = getPage(name);
		if (pageName == null) {
			getLog().warn("Could not find Kampfstilsonderfertigkeit with name \"{}\".", name);
			rule = "<Unknown Kampfstilsonderfertigkeit>";
		} else {
			try {
				final Document doc = loadDocument(pageName);
				rule = getRule(doc);
			} catch (final IOException e) {
				getLog().error("Error reading Kampfstilsonderfertigkeit \"" + name + "\". Error message: " + e.getMessage(), e);
				rule = e.getMessage();
			}
		}

		return new KampfstilsonderfertigkeitData(name, rule);
	}

	private String getPage(final String kampfsonderfertigkeitName) {
		if (pages == null) {
			pages = readKampfstilsonderfertigkeiten();
		}
		return pages.get(kampfsonderfertigkeitName);
	}

	private Map<String, String> readKampfstilsonderfertigkeiten() {
		getLog().info("Initializinge Kampfstilsonderfertigkeit data.");
		Document doc;
		try {
			doc = loadDocument("/SF_Kampfstilsonderfertigkeiten.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of Kampfstilsonderfertigkeit. Error message: " + e.getMessage(), e);
			return Collections.emptyMap();
		}

		final Map<String, String> map = new HashMap<>();
		for (final Element element : doc.selectXpath("//a[@class='ulSubMenu']")) {
			map.put(element.attr("title"), "/" + element.attr("href"));
		}
		return map;
	}

	private String getRule(final Element root) {
		final Elements elements = root.selectXpath("(//div[@class='ce_text block'])[1]/p");
		switch (elements.size()) {
		case 0:
			return "<Not Found>";

		case 1:
			return getRuleFromSingleElement(elements.first());

		default:
			return getRuleFromMultipleElements(elements);
		}
	}

	private String getRuleFromSingleElement(final Element ruleElement) {
		// Rule value is in first child node. It is not wrapped in any other
		// HTML entity.
		return ruleElement.childNode(1).toString().trim();
	}

	private String getRuleFromMultipleElements(final Elements elements) {
		final String ruleKey = "Regel:";
		for (final Element element : elements) {
			final String text = element.text();
			if (text.startsWith(ruleKey)) {
				return text.substring(ruleKey.length()).trim();
			}
		}
		return "<Not Found>";
	}

}

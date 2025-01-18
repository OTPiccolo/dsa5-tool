package de.otpiccolo.dsa5.data.erweitertertalentstil;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Erweiterter Talentstil from an Internet source.
 */
public class ErweiterterTalentstilReader extends AUlissesReader<ErweiterterTalentstilData> {

	private Map<String, String> pages;

	@Override
	public ErweiterterTalentstilData readData(final String name) {
		getLog().info("Getting data for \"{}\" Erweiterter Talentstil.", name);
		String rule;

		final String pageName = getPage(name);
		if (pageName == null) {
			getLog().warn("Could not find Erweiterter Talentstil with name \"{}\".", name);
			rule = "<Unknown Erweiterter Talentstil>";
		} else {
			try {
				final Document doc = loadDocument(pageName);
				rule = getRule(doc);
			} catch (final IOException e) {
				getLog().error("Error reading Erweiterter Talentstil \"" + name + "\". Error message: " + e.getMessage(), e);
				rule = e.getMessage();
			}
		}

		return new ErweiterterTalentstilData(name, rule);
	}

	private String getPage(final String erweiterterTalentstilName) {
		if (pages == null) {
			pages = readErweiterteTalentstile();
		}
		return pages.get(erweiterterTalentstilName);
	}

	private Map<String, String> readErweiterteTalentstile() {
		getLog().info("Initializinge Erweiterter Talentstil data.");
		Document doc;
		try {
			doc = loadDocument("/Erweiterte_Talentsonderfertigkeiten.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of Erweiterter Talentstil. Error message: " + e.getMessage(), e);
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

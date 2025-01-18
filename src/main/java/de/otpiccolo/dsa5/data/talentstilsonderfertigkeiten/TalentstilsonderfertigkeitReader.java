package de.otpiccolo.dsa5.data.talentstilsonderfertigkeiten;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Talentstilsonderfertigkeit from an Internet source.
 */
public class TalentstilsonderfertigkeitReader extends AUlissesReader<TalentstilsonderfertigkeitData> {

	private static final Pattern NAME_PATTERN = Pattern.compile("^(\\w++) (?:des|der) (\\w++)$", Pattern.UNICODE_CHARACTER_CLASS);

	private Map<String, String> pages;

	@Override
	public TalentstilsonderfertigkeitData readData(final String name) {
		getLog().info("Getting data for \"{}\" Talentstilsonderfertigkeit.", name);
		String rule;

		final String pageName = getPage(name);
		if (pageName == null) {
			getLog().warn("Could not find Talentstilsonderfertigkeit with name \"{}\".", name);
			rule = "<Unknown Talentstilsonderfertigkeit>";
		} else {
			try {
				final Document doc = loadDocument(pageName);
				rule = getRule(doc);
			} catch (final IOException e) {
				getLog().error("Error reading Talentstilsonderfertigkeit \"" + name + "\". Error message: " + e.getMessage(), e);
				rule = e.getMessage();
			}
		}

		return new TalentstilsonderfertigkeitData(name, rule);
	}

	private String getPage(final String talentstilsonderfertigkeitName) {
		if (pages == null) {
			pages = readTalentstilsonderfertigkeiten();
		}

		// Shorten "des/der" to "d." in name, to get uniform name.
		final Matcher matcher = NAME_PATTERN.matcher(talentstilsonderfertigkeitName);
		if (matcher.matches()) {
			return pages.get(matcher.group(1) + " d. " + matcher.group(2));
		}

		return pages.get(talentstilsonderfertigkeitName);
	}

	private Map<String, String> readTalentstilsonderfertigkeiten() {
		getLog().info("Initializinge Talentstilsonderfertigkeit data.");
		Document doc;
		try {
			doc = loadDocument("/Talentstilsonderfertigkeiten.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of Talentstilsonderfertigkeit. Error message: " + e.getMessage(), e);
			return Collections.emptyMap();
		}

		final Map<String, String> map = new HashMap<>();
		for (final Element element : doc.selectXpath("//a[@class='ulSubMenu']")) {
			String title = element.attr("title");
			// Shorten "des/der" to "d." in title, to get uniform name.
			final Matcher matcher = NAME_PATTERN.matcher(title);
			if (matcher.matches()) {
				title = matcher.group(1) + " d. " + matcher.group(2);
			}
			map.put(title, "/" + element.attr("href"));
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

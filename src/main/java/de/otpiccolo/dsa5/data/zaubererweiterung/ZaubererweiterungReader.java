package de.otpiccolo.dsa5.data.zaubererweiterung;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Zauber from an Internet source.
 */
public class ZaubererweiterungReader extends AUlissesReader<ZaubererweiterungData> {

	@Override
	public ZaubererweiterungData readData(final String name) {
		getLog().info("Getting data for \"{}\" Zaubererweiterung.", name);
		String erweiterungName;
		String zauberName;
		String effect;

		final String[] split = name.split("#", 2);
		if (split.length != 2) {
			erweiterungName = name;
			zauberName = null;
			effect = "Could not parse Zaubererweiterung. Expected: Zaubername#Zaubererweiturngname";
		} else {
			zauberName = split[0];
			erweiterungName = split[1];
			try {
				final Document doc = loadDocument(getPage(zauberName));
				effect = getErweiterungEffect(doc, erweiterungName);
			} catch (final IOException e) {
				getLog().error("Error reading Zaubererweiterung \"" + name + "\". Error message: " + e.getMessage(), e);
				effect = e.getMessage();
			}
		}

		return new ZaubererweiterungData(erweiterungName, zauberName, effect);
	}

	private String getPage(final String zauberName) {
		return "/zauber.html?zauber=" + zauberName;
	}

	private String getErweiterungEffect(final Element root, final String name) {
		final String key = name;
		boolean found = false;
		for (final Element element : root.selectXpath("//div[@class='body']/div")) {
			// Desired data is in next element.
			if (found) {
				return element.text();
			}
			for (final Element nameElement : element.selectXpath("i")) {
				found = key.equals(nameElement.text());
			}
		}
		return "<Not Found>";
	}

}

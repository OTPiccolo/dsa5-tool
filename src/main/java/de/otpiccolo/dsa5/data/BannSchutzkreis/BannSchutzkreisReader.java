package de.otpiccolo.dsa5.data.BannSchutzkreis;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Bann-/Schutzkreise from an Internet source.
 */
public class BannSchutzkreisReader extends AUlissesReader<BannSchutzkreisData> {

	private Map<String, String> pages;

	@Override
	public BannSchutzkreisData readData(final String name) {
		getLog().info("Getting data for Bann-/Schutzkreis \"{}\".", name);
		String bannEffect;
		String schutzEffect;
		String cost;

		try {
			final Document doc = loadDocument(getPage(name));
			bannEffect = getData(doc, "Bannkreis");
			schutzEffect = getData(doc, "Schutzkreis");
			cost = getData(doc, "AsP-Kosten");
		} catch (final IOException e) {
			getLog().error("Error reading Bann-/Schutzkreis \"" + name + "\". Error message: " + e.getMessage(), e);
			bannEffect = e.getMessage();
			schutzEffect = null;
			cost = null;
		}
		return new BannSchutzkreisData(name, cost, bannEffect, schutzEffect);
	}

	private String getPage(final String kreisName) {
		if (pages == null) {
			pages = readBannSchutzkreise();
		}
		return pages.get(kreisName);
	}

	private Map<String, String> readBannSchutzkreise() {
		getLog().info("Initializinge Bann-/Schutzkreis data.");
		Document doc;
		try {
			doc = loadDocument("/bannundschutz.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of Bann-/Schutzkreis. Error message: " + e.getMessage(), e);
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
		for (final Element element : root.selectXpath("//div[@class='ce_text default block']/p")) {
			if (element.text().startsWith(name)) {
				return element.text().substring(key.length());
			}
		}
		return "<Not Found>";
	}

}

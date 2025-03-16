package de.otpiccolo.dsa5.data.elixiere;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Elixier from an Internet source.
 */
public class ElixierReader extends AUlissesReader<ElixierData> {

	private static Pattern COST_PATTERN = Pattern.compile("^Kosten[\\s\\w]++: *+(\\d++)");
	private static Pattern QS_PATTERN = Pattern.compile("^(\\d++): *+(.++)");

	private Map<String, String> pages;

	@Override
	public ElixierData readData(final String name) {
		getLog().info("Getting data for \"{}\" Elixier.", name);
		int cost;
		List<String> qs;

		final String pageName = getPage(name);
		if (pageName == null) {
			getLog().warn("Could not find Elixier with name \"{}\".", name);
			qs = Collections.singletonList("<Unknown Elixier>");
			cost = -1;
		} else {
			try {
				final Document doc = loadDocument(pageName);
				cost = getCost(doc);
				qs = getQs(doc);
				if (qs.isEmpty()) {
					qs.add("<No QS found>");
				}
			} catch (final IOException e) {
				getLog().error("Error reading Elixier \"" + name + "\". Error message: " + e.getMessage(), e);
				qs = Collections.singletonList(e.getMessage());
				cost = -1;
			}
		}

		return new ElixierData(name, cost, qs);
	}

	private String getPage(final String elixierName) {
		if (pages == null) {
			pages = readElixiere();
		}
		return pages.get(elixierName);
	}

	private Map<String, String> readElixiere() {
		getLog().info("Initializinge Elixiere data.");
		final Map<String, String> elixiere = new HashMap<String, String>();
		addElixiere1(elixiere);
		addElixiere2(elixiere);
		return elixiere;
	}

	private void addElixiere1(final Map<String, String> map) {
		Document doc;
		try {
			doc = loadDocument("/Elixier.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of Elixiere. Error message: " + e.getMessage(), e);
			return;
		}

		for (final Element element : doc.selectXpath("//a[@class='ulSubMenu']")) {
			map.put(element.attr("title"), "/" + element.attr("href"));
		}
	}

	private void addElixiere2(final Map<String, String> map) {
		Document doc;
		try {
			doc = loadDocument("/her_a_elixiere.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of Elixiere. Error message: " + e.getMessage(), e);
			return;
		}

		for (final Element element : doc.selectXpath("//a[@class='ulSubMenu']")) {
			map.put(element.attr("title"), "/" + element.attr("href"));
		}
	}

	private int getCost(final Element root) {
		for (final Element element : root.selectXpath("//div[@class='ce_text block']/p")) {
			final Matcher matcher = COST_PATTERN.matcher(element.text());
			if (matcher.find()) {
				return (int) (Integer.parseInt(matcher.group(1)) * 1.5);
			}
		}
		return -1;
	}

	private List<String> getQs(final Element root) {
		final List<String> qs = new ArrayList<String>(6);
		boolean found = false;
		for (final Element element : root.selectXpath("//div[@class='ce_text block']/p")) {
			if (found) {
				final Matcher matcher = QS_PATTERN.matcher(element.text());
				if (matcher.find()) {
					qs.add(matcher.group(2));
				}
			} else if (element.text().startsWith("Qualitätsstufe")) {
				found = true;
			}
		}
		return qs;
	}

}

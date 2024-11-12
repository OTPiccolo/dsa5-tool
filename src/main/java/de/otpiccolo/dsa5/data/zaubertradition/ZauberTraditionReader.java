package de.otpiccolo.dsa5.data.zaubertradition;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Zauber Traditionen from an Internet source.
 */
public class ZauberTraditionReader extends AUlissesReader<ZauberTraditionData> {

	@Override
	public ZauberTraditionData readData(final String name) {
		getLog().info("Getting data for \"{}\" Zauber Tradition.", name);
		String mainAttribute;
		List<String> rules;

		try {
			final Document doc = loadDocument(getPage(name));
			mainAttribute = getDataInTable(doc, "Leiteigenschaft");
			rules = parseRules(getDataInTable(doc, "Regel"));
		} catch (final IOException e) {
			getLog().error("Error reading Zauber Tradition \"" + name + "\". Error message: " + e.getMessage(), e);
			rules = Collections.singletonList(e.getMessage());
			mainAttribute = null;
		}
		return new ZauberTraditionData(name, mainAttribute, rules);
	}

	private List<String> parseRules(final String ruleString) {
		final String[] rules = ruleString.split(" ?# ");
		// As the rulesString starts with '# ', split creates an empty string at
		// the beginning. This should be removed.
		return Arrays.asList(Arrays.copyOfRange(rules, 1, rules.length));
	}

	private String getPage(final String zauberTraditionName) {
		return "/magische_tradition.html?tradition=" + zauberTraditionName;
	}

}

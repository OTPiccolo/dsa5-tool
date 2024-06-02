package de.otpiccolo.dsa5.data.karmaletradition;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Karmale Traditionen from an Internet source.
 */
public class KarmaleTraditionReader extends AUlissesReader<KarmaleTraditionData> {

	@Override
	public KarmaleTraditionData readData(final String name) {
		getLog().info("Getting data for \"{}\" Karmale Tradition.", name);
		String mainAttribute;
		String preferredTalents;
		List<String> rules;

		try {
			final Document doc = loadDocument(getPage(name));
			mainAttribute = getDataInTable(doc, "Leiteigenschaft");
			preferredTalents = getDataInTable(doc, "Wohlgefällige Talente");
			rules = parseRules(getDataInTable(doc, "Regel"));
		} catch (final IOException e) {
			getLog().error("Error reading Karmale Tradition \"" + name + "\". Error message: " + e.getMessage(), e);
			rules = Collections.singletonList(e.getMessage());
			mainAttribute = null;
			preferredTalents = null;
		}
		return new KarmaleTraditionData(name, mainAttribute, preferredTalents, rules);
	}

	private List<String> parseRules(final String ruleString) {
		final String[] rules = ruleString.split(" ?# ");
		// As the rulesString starts with '# ', split creates an empty string at
		// the beginning. This should be removed.
		return Arrays.asList(Arrays.copyOfRange(rules, 1, rules.length));
	}

	private String getPage(final String karmaleTraditionName) {
		return "/karmale_tradition.html?tradition=Tradition+(" + karmaleTraditionName + ")";
	}

}

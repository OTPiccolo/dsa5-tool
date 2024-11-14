package de.otpiccolo.dsa5.data.gewandzauber;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Gewandzauber from an Internet source.
 */
public class GewandzauberReader extends AUlissesReader<GewandzauberData> {

	private static Pattern VOLUME_PATTERN = Pattern.compile("^ *+(\\d++)");

	@Override
	public GewandzauberData readData(final String name) {
		getLog().info("Getting data for Gewandzauber \"{}\".", name);
		String effect;
		String cost;
		int volume;

		try {
			final Document doc = loadDocument(getPage(name));
			effect = getDataInTable(doc, "Wirkung");
			volume = toVolume(getDataInTable(doc, "Volumen"));
			cost = getDataInTable(doc, "AsP-Kosten");
		} catch (final IOException e) {
			getLog().error("Error reading Gewandzauber \"" + name + "\". Error message: " + e.getMessage(), e);
			effect = e.getMessage();
			volume = -1;
			cost = null;
		}
		return new GewandzauberData(name, volume, cost, effect);
	}

	private String getPage(final String zaubertanzName) {
		return "/traditionsartefakt_sf_gewandzauber.html?sonderfertigkeit=" + zaubertanzName;
	}

	private int toVolume(final String volume) {
		final Matcher matcher = VOLUME_PATTERN.matcher(volume);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		}
		return -1;
	}

}

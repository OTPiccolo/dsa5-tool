package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Tsa Geweihten.
 */
public class TsaPage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public TsaPage() {
		super("Tsa");
		addMoralkodex();
		addTradition("Tsakirche");
		addMirakel();
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Lebenserhaltung", Collections.singleton(new ParagraphData("Die Tsageweihte achtet und schützt das Leben."))));
		getWriters().add(new ParagraphWriter("Waffenbann", Collections.singleton(new ParagraphData("Die Tsageweihte soll keine Waffen benutzen und damit Lebewesen schaden oder gar töten."))));
		getWriters().add(new ParagraphWriter("Erneuerung", Collections.singleton(new ParagraphData("Stillstand und Routine sind der Tsageweihten ein Gräuel. Sie versucht stets, ihren Horizont zu erweitern und pobiert gerne neue Sachen aus."))));
	}

}

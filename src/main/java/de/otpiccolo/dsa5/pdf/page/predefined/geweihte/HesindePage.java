package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Hesinde Geweihten.
 */
public class HesindePage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public HesindePage() {
		super("Hesinde");
		addMoralkodex();
		addTradition("Hesindekirche");
		addMirakel();
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Sammeln von Wissen", Collections.singleton(new ParagraphData("Artefakte, Bücher und anderes Wissen ist in den Augen der Göttin so wertvoll, dass es gesammelt werden muss."))));
		getWriters().add(new ParagraphWriter("Ewige Lehre", Collections.singleton(new ParagraphData("Der Geweihte sollte sich bemühen, sich stets fortzubilden."))));
		getWriters().add(new ParagraphWriter("Ästhetik", Collections.singleton(new ParagraphData("Die Welt ist schön un der Geweihte soll die Schönheit der Welt ehren und mehren."))));
	}

}

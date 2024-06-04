package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Rondra Geweihten.
 */
public class RondraPage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public RondraPage() {
		super("Rondra");
		addMoralkodex();
		addTradition("Rondrakirche");
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Verteidigung des Glaubens", Collections.singleton(new ParagraphData("Die Verteidigung des Glaubens ist die Pflicht jedes Rondrageweihten."))));
		getWriters().add(new ParagraphWriter("Ritterlichkeit", Collections.singleton(new ParagraphData("Der Rondrageweihte setzt im Kampf keine Armbrüste ein oder verhält sich unehrenhaft."))));
		getWriters().add(new ParagraphWriter("Verantwortung", Collections.singleton(new ParagraphData("Der Schutz aller Gläubigen, der Heiligtümer und der Tempel der Zwölfgötter steht im Vordergrund der Aufgaben eines Geweihten."))));
		getWriters().add(new ParagraphWriter("Zweikampf", Collections.singleton(new ParagraphData("Der ehrenhafte Zweikampf ist von allen Kampfhandlungen die rondragefälligste."))));
		getWriters().add(new ParagraphWriter("Schwertmeisterschaft", Collections.singleton(new ParagraphData("Sich in allen Waffengattungen auszukenn und sie zu meistern ist eine Selbstverständlichkeit für den Geweihten."))));
	}

}

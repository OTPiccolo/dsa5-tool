package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Firun Geweihten.
 */
public class FirunPage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public FirunPage() {
		super("Firun");
		addMoralkodex();
		addTradition("Firunkirche");
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Ernsthaftigkeit", Collections.singleton(new ParagraphData("Witze und ähnliche Verspieltheiten sind Zeitverschwendung und sollten gemieden werden."))));
		getWriters().add(new ParagraphWriter("Askese", Collections.singleton(new ParagraphData("Der Firungeweihte sollte nicht verschwenderisch mit Ressourcen umgehen. Er sollte nur das benutzen, was er auch wirklich braucht."))));
		getWriters().add(new ParagraphWriter("Belastbarkeit", Collections.singleton(new ParagraphData("Das Streben des Firungeweihten gilt den Herausforderungen, die ihm sein Gott schickt. Er sollte sich immer bis an die Grenzen seiner Belastbarkeit begeben und sich kontinuierlich steigern."))));
	}

}

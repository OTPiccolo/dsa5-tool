package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Phex Geweihten.
 */
public class PhexPage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public PhexPage() {
		super("Phex");
		addMoralkodex();
		addTradition("Phexkirche");
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Gegenleistung", Collections.singleton(new ParagraphData("Für eine zu erfüllende Aufgabe muss der Geweihte stets eine Gegenleistung verlangen."))));
		getWriters().add(new ParagraphWriter("Heimlichkeit", Collections.singleton(new ParagraphData("Der Geweihte soll seine Pläne im Verborgenen ausführen."))));
		getWriters().add(new ParagraphWriter("Herausforderung", Collections.singleton(new ParagraphData("Je größer die Herausforderung, desto größer der Ruhm. Der Geweihte soll große Herausforderungen suchen und sich ihnen stellen."))));
	}

}

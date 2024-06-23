package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Boron Geweihten.
 */
public class BoronPage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public BoronPage() {
		super("Boron");
		addMoralkodex();
		addTradition("Boronkirche");
		addMirakel();
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Bestattung", Collections.singleton(new ParagraphData("Kein Leichnam sollte unbestattet sein. Der Geweihte muss für die Totenruhe sorgen."))));
		getWriters().add(new ParagraphWriter("Schweigen", Collections.singleton(new ParagraphData("Schweigen ist eine Tugend. Kein Geweihter sollte ohne Grund reden."))));
		getWriters().add(new ParagraphWriter("Traum", Collections.singleton(new ParagraphData("Ergründe die Welt der Träume. In ihr kann der Geweihte den Willen Borons ergründen."))));
	}

}

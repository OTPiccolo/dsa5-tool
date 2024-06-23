package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Peraine Geweihten.
 */
public class PerainePage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public PerainePage() {
		super("Peraine");
		addMoralkodex();
		addTradition("Perainekirche");
		addMirakel();
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Hilfe", Collections.singleton(new ParagraphData("Leiste jedem Hilfe, der Hilfe benötigt."))));
		getWriters().add(new ParagraphWriter("Aufopferung", Collections.singleton(new ParagraphData("Arbeite hart und halte dich vom Müßiggang fern."))));
		getWriters().add(new ParagraphWriter("Bescheidenheit", Collections.singleton(new ParagraphData("Verschwende nicht die Gaben der Göttin."))));
		getWriters().add(new ParagraphWriter("Heilkunst", Collections.singleton(new ParagraphData("Bilde dich in der Heilkunst fort."))));
	}

}

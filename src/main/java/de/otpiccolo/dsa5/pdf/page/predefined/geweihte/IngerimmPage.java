package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Ingerimm Geweihten.
 */
public class IngerimmPage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public IngerimmPage() {
		super("Ingerimm");
		addMoralkodex();
		addTradition("Ingerimmkirche");
		addMirakel();
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Wahrung des Feuers", Collections.singleton(new ParagraphData("In der Gegenwart des Ingerimmgeweihten sollen alle Feuer brennen. Haus- und Waldbrände dürfen aber eingedämmt werden, und wenn ein Gläubiger durch den Brand in direkte Gefhar gerät, darf auch ein Ingerimmgeweihter Feuer löschen."))));
		getWriters().add(new ParagraphWriter("Opfer", Collections.singleton(new ParagraphData("Schöne Edelsteine und handwerkliche Erzeugnisse opfert der Ingerimmgrgeweihte voller Freude sinem Gott (bspw. durch Verbrennen)."))));
		getWriters().add(new ParagraphWriter("Demut und Geduld", Collections.singleton(new ParagraphData("Der Ingrerimmgeweihte muss Geduld bewahren und soll nicht mit seinen Fähigkeiten prahlen."))));
		getWriters().add(new ParagraphWriter("Fleiß und Geschick", Collections.singleton(new ParagraphData("Der Ingerimmgeweihte strebt in seinen Künsten nach Perfektion und verwendet auf die Verbesserung seines Handwerks viel Zeit."))));
		getWriters().add(new ParagraphWriter("Zorn und Kraft", Collections.singleton(new ParagraphData("Sollte es nötig sein, dann muss ein Ingerimmgeweihter seinen Zorn freien Lauf lassen."))));
	}

}

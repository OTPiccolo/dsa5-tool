package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Efferd Geweihten.
 */
public class EfferdPage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public EfferdPage() {
		super("Efferd");
		addMoralkodex();
		addTradition("Efferdkirche");
		addMirakel();
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Feuerbann und Ernährung", Collections.singleton(new ParagraphData("Efferdgeweihte benutzen kein Feuer als Lichtquelle und entzünden keine Flamme (dafür nutzen sie bspw. das Licht des Gwen Petryl-Steins in ihren Tempeln). Für die Wärmezufuhr nutzen sie warme Quellen. Zudem vermeiden sie gegartes und geräuchertes Essen (worunter auch Gegrilltes und Gebratenes fällt) und essen niemals Delphin, Wal oder Robbe, da ihnen dies untersagt ist."))));
		getWriters().add(new ParagraphWriter("Schicksalsergebenheit", Collections.singleton(new ParagraphData("Ein Efferdgeweihte fügt sich den Befehlen seines Vorgesetzten oder des Kapitäns."))));
		getWriters().add(new ParagraphWriter("Ausleben der Emotionen", Collections.singleton(new ParagraphData("Efferdgeweihte leben ihre Gefühle aus, egal ob sie Zorn, Trauer, Schmerz oder Freude empftinden. Sie halten sich nicht zurück und lassen diesen Gefühlen freien Lauf."))));
	}

}

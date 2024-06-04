package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Praios Geweihten.
 */
public class PraiosPage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public PraiosPage() {
		super("Praios");
		addMoralkodex();
		addTradition("Praioskirche");
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Gehorsam", Collections.singleton(new ParagraphData("Der Geweihte ist verpflichtet, sich an die Befehle von Personen zu halten, die über ihm in der Kirchenhierarchie stehen."))));
		getWriters().add(new ParagraphWriter("Offensichtlichkeit", Collections.singleton(new ParagraphData("Der Geweihte versteckt sich nicht. Wichtig sind auch Schutz von Gesetz und Staat: der Geweihte verteidigt zwölfgöttliche Reiche und Strukturen und achtet auf die Einahltung der Gesetze."))));
		getWriters().add(new ParagraphWriter("Magiebann", Collections.singleton(new ParagraphData("Magie, vor allem schwarze Magie, sollte gebannt werden. Weiße Magie kann unter Umständen toleriert werden. Das Gildenrecht der Magier ist zu achten."))));
		getWriters().add(new ParagraphWriter("Mission", Collections.singleton(new ParagraphData("Der Glaube an den Götterfürsten und seine Geschwister muss in alle Winkel Deres verbreitet werden."))));
	}

}

package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Travia Geweihten.
 */
public class TraviaPage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public TraviaPage() {
		super("Travia");
		addMoralkodex();
		addTradition("Traviakirche");
		addMirakel();
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Gastfreundschaft", Collections.singleton(new ParagraphData("Die Traviageweihte gewährt jedem, der dies wünscht, Gastfreundschaft und achtet auf die Einahltung der Gesetze der Gastfreundschaft (niemand soll einen anderen Gast beleidigen oder angreifen)."))));
		getWriters().add(new ParagraphWriter("Mäßigung und Wahrung von Sitte und Anstand", Collections.singleton(new ParagraphData("Die Traviageweihte achtet auf das friedliche Zusammenleben der Menschen. Sie verurteilt Prunksucht, Unzuverlässigkeit und unzüchtiges Benehmen."))));
		getWriters().add(new ParagraphWriter("Treue", Collections.singleton(new ParagraphData("Wer sich untreu verhält, egal ob in der Ehe oder weil er Versprechen bricht, sollte seinen Fehler wieder gut machen, worüber die Traviageweihte wacht."))));
	}

}

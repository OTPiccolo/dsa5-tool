package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;

/**
 * Page about a Rahja Geweihten.
 */
public class RahjaPage extends GeweihtePage {

	/**
	 * Constructor.
	 */
	public RahjaPage() {
		super("Rahja");
		addMoralkodex();
		addTradition("Rahjakirche");
		addMirakel();
	}

	private void addMoralkodex() {
		addMoralkodexHeading();
		getWriters().add(new ParagraphWriter("Freude & Harmonie", Collections.singleton(new ParagraphData("Die Rahjageweihte ist dazu bestimmt, Freude zu verbreiten und die Srogen der Menschen zu vertreiben. Dabei soll die Geweihte das passende Maß finden und auf die Vorlieben der Gläubigen eingehen. Aufdringliches Verhalten seitens der Geweihten steht einem harmonischen Anspruch entgegen."))));
		getWriters().add(new ParagraphWriter("Ekstase & Leidenschaft", Collections.singleton(new ParagraphData("Den Rauch der Göttin soll nicht nur die Geweihte erleben, sondern ihn auch den Gläubigen schenken. Alles, was die Rahjageweihte verschenkt, soll sie mit Leidenschaft verschenken."))));
		getWriters().add(new ParagraphWriter("Hingabe & Gleichmut", Collections.singleton(new ParagraphData("Die Rahjageweihte erträgt auch Zeiten, in denen sie keine Freude empfinden kann, mit Gleichmut. Ihre Hingabe soll besonders der göttlichen Ekstase dienen, nicht vergänglichen Dingen."))));
	}

}

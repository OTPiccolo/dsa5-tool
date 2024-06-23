package de.otpiccolo.dsa5.pdf.page.predefined;

import java.util.Arrays;
import java.util.List;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;

/**
 * Page about Entrückung and Trance.
 */
public class EntrueckungTrancePage extends DefaultPage {

	/**
	 * Constructor.
	 */
	public EntrueckungTrancePage() {
		super("Entrückung und Trance");
		addEntrueckung();
		addTrance();
	}

	private void addEntrueckung() {
		final ParagraphData pg1 = new ParagraphData("Stufe 1: Leicht entrückt; Alle Proben auf Talente und Zauber -1, so sie nicht dem Gott des Geweihten gefällig sind.");
		final ParagraphData pg2 = new ParagraphData("Stufe 2: Entrückt; alle dem Gott des Geweihten gefälligen Proben auf Talente und Zauber +1, alle anderen -2.");
		final ParagraphData pg3 = new ParagraphData("Stufe 3: Göttlich berührt; Alle dem Gott des Geweihten gefällige Proben auf Talente und Zauber +2, alle anderen -3.");
		final ParagraphData pg4 = new ParagraphData("Stufe 4: Ein Werkzeug des Gottes; Alle dem Gott des Geweihten gefällige Proben auf Talente und Zauber +3, alle anderen -4.");

		final List<ParagraphData> paragraphs = Arrays.asList(pg1, pg2, pg3, pg4);
		final ParagraphWriter writer = new ParagraphWriter("Entrückung", paragraphs);
		getWriters().add(writer);
	}

	private void addTrance() {
		final ParagraphData pg1 = new ParagraphData("Stufe 1: Keine AsP-Regeneration in der nächsten Regenerations-Phase.");
		final ParagraphData pg2 = new ParagraphData("Stufe 2: Alle Proben, außer auf Liturgien und wohlgefällige Talente sind um 2 erschwert, keine AsP-Regeneration in der nächsten Regenerationsphase.");
		final ParagraphData pg3 = new ParagraphData("Stufe 3: Alle Proben (auch wohlgefällige) sind um 3 erschwert, keine AsP-Regeneration in der nächsten Regenerationsphase.");
		final ParagraphData pg4 = new ParagraphData("Stufe 4: Handlungsunfähig; keine AsP-Regeneration in der nächsten Regenerationsphase.");

		final List<ParagraphData> paragraphs = Arrays.asList(pg1, pg2, pg3, pg4);
		final ParagraphWriter writer = new ParagraphWriter("Entrückung", paragraphs);
		getWriters().add(writer);
	}

}

package de.otpiccolo.dsa5.pdf.page.predefined;

import java.util.Arrays;
import java.util.List;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;

/**
 * Page about modifications for Zauber/Predigten/...
 */
public class ZauberModPage extends DefaultPage {

	/**
	 * Constructor.
	 */
	public ZauberModPage() {
		super("Zauber-/Predigt-Modifkationen");
		final ParagraphData pg1 = new ParagraphData("Erzwingen: Kosten um eine Stufe erhöht, Zauberprobe um 1 erleichtert.");
		final ParagraphData pg2 = new ParagraphData("Kosten senken: Kosten um eine Stufe gesenkt, Zauberprobe um 1 erschwert.");
		final ParagraphData pg3 = new ParagraphData("Reichweite erhöhen: Reichweite um eine Stufe erhöht, Zauberprobe um 1 erschwert.");
		final ParagraphData pg4 = new ParagraphData("Zauberdauer erhöhen: Zauberdauer um eine Stufe erhöht, Zauberprobe um 1 erleichtert.");
		final ParagraphData pg5 = new ParagraphData("Zauberdauer senken: Zauberdauer um eine Stufe gesenkt, Zauberprobe um 1 erschwert.");
		final ParagraphData pg6 = new ParagraphData("Geste oder Formel weglassen: Zauberprobe um jeweils 2 erschwert.");
		final ParagraphData pg7 = new ParagraphData("Emotionen (nur Hexe): Unter dem Einfluss zum Zauber passender Gefühle kann die Hexe eine Erleichterung von bis zu 2 für die Zauberprobe erhalten. Werden ihre Gefühle künstlich gedämpft, zum Beispiel durch Ilmenblatt, oder zaubert sie entgegen ihrer Gefühle, erschwert dies die Proben um bis zu 2. Die Hexe kann sich gezielt in ihre Emotionen hineinsteigern, sodass sie innerhalb von zwei Kampfrunden die Erleichterung um 1 erhöhen kann, bis zu dem angegebenen Maximum von 2. Dieser Vorgang erfordert Konzentration. Der Einsatz dieser Fähigkeit bedarf einer freien Aktion.");
		final List<ParagraphData> paragraphs = Arrays.asList(pg1, pg2, pg3, pg4, pg5, pg6, pg7);
		final ParagraphWriter writer = new ParagraphWriter(paragraphs);
		getWriters().add(writer);
	}

}

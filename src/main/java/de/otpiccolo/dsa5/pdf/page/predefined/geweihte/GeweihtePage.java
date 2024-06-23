package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.data.karmaletradition.KarmaleTraditionReader;
import de.otpiccolo.dsa5.data.karmaletradition.KarmaleTraditionWriter;
import de.otpiccolo.dsa5.pdf.data.ADataWriter;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;

/**
 * Page for a Geweihte. Has some useful predefined methods for describing a
 * Geweihten.
 */
public class GeweihtePage extends DefaultPage {

	/**
	 * Constructor.
	 *
	 * @param godName
	 *            Name of the god the Geweihte is following.
	 */
	public GeweihtePage(final String godName) {
		super(godName);
	}

	/**
	 * Adds the header for the 'Moralkodex'.
	 */
	public void addMoralkodexHeading() {
		getWriters().add(new ParagraphWriter("Moralkodex", Collections.emptyList()));
	}

	/**
	 * Adds the full tradition of the Geweihte.
	 *
	 * @param traditionName
	 *            The name of the Tradition. Example: Praioskirche
	 */
	public void addTradition(final String traditionName) {
		getWriters().add(ADataWriter.fillWriter(KarmaleTraditionWriter::new, KarmaleTraditionReader::new, traditionName));
	}

	/**
	 * Adds the 'Mirakel' ability of the Geweihte.
	 */
	public void addMirakel() {
		getWriters().add(new ParagraphWriter("Mirakel", Collections.singleton(new ParagraphData("Ein Mirakel ist ein Stoßgebet. Jede Gottheit gewährt ihren Geweihten die Möglichkeit, für den Einsatz von 4 Karmapunkten 2 Punkte auf den FW eines der Gottheit wohlgefälligen Talents dazuzugewinnen. Die Punkte müssen augenblicklich vor dem Ablegen der Probe bzw. der Durchführung der Aktion eingesetzt werden und das Mirakel kostet 1 freie Aktion. Sollten sie nicht sofort zum Einsatz kommen, verfallen die Punkte wieder. Auch bei Kampftechniken lassen sich Mirakel anwenden. In dem Fall bekommt der Geweihte +2 auf AT oder +2 auf PA der entsprechenden Kampftechnik. Fernkampftechniken erhalten +2 auf Fernkampf."))));
	}

}

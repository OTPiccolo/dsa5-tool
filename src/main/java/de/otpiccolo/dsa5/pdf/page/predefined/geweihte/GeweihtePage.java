package de.otpiccolo.dsa5.pdf.page.predefined.geweihte;

import java.util.Collections;

import de.otpiccolo.dsa5.data.karmaletradition.KarmaleTraditionReader;
import de.otpiccolo.dsa5.data.karmaletradition.KarmaleTraditionWriter;
import de.otpiccolo.dsa5.pdf.data.ADataWriter;
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

}

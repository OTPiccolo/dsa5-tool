package de.otpiccolo.dsa5.pdf.page.predefined;

import de.otpiccolo.dsa5.data.segen.SegenReader;
import de.otpiccolo.dsa5.data.segen.SegenWriter;
import de.otpiccolo.dsa5.pdf.data.ADataWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;

/**
 * Page about Segen for a Geweihter.
 */
public class SegenPage extends DefaultPage {

	/**
	 * Constructor.
	 */
	public SegenPage() {
		super("Geweihten-Segen");
		final SegenWriter writer = ADataWriter.fillWriter(SegenWriter::new, SegenReader::new, "Eidsegen", "Feuersegen", "Geburtssegen", "Glückssegen", "Grabsegen", "Harmoniesegen", "Kleiner Heilsegen", "Kleiner Schutzsegen", "Speisesegen", "Stärkungssegen", "Tranksegen", "Weisheitssegen");
		writer.setFontSize(9);
		getWriters().add(writer);
	}

}

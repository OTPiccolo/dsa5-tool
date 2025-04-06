package de.otpiccolo.dsa5.pdf.person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten.AllgemeinesonderfertigkeitReader;
import de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten.AllgemeinesonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.elixiere.ElixierReader;
import de.otpiccolo.dsa5.data.elixiere.ElixierWriter;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.nachteile.NachteilReader;
import de.otpiccolo.dsa5.data.nachteile.NachteilWriter;
import de.otpiccolo.dsa5.data.vorteile.VorteilReader;
import de.otpiccolo.dsa5.data.vorteile.VorteilWriter;
import de.otpiccolo.dsa5.data.weapon.WeaponReader;
import de.otpiccolo.dsa5.data.weapon.WeaponWriter;
import de.otpiccolo.dsa5.pdf.data.image.ImageReader;
import de.otpiccolo.dsa5.pdf.data.image.ImageWriter;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;
import de.otpiccolo.dsa5.pdf.page.IPage;
import de.otpiccolo.dsa5.pdf.page.predefined.SchicksalspunktePage;

/**
 * Information about Manjula.
 */
public class Aurelius extends Person {

	/**
	 * Constructor.
	 */
	public Aurelius() {
		final DefaultPage vorteilPage = new DefaultPage("Vorteile & Nachteile");
		vorteilPage.getWriters().add(fillWriter(VorteilWriter::new, VorteilReader::new, "Adel I-III", "Beidhändig", "Gutes Namensgedächtnis", "Vertrauenerweckend"));
		vorteilPage.getWriters().add(fillWriter(NachteilWriter::new, NachteilReader::new, "Böser Namensvetter", "Nachtblind", "Wahrer Name"));

		final DefaultPage kampfPage = new DefaultPage("Kampfsonderfertigkeiten");
		kampfPage.getWriters().add(fillWriter(KampfsonderfertigkeitWriter::new, KampfsonderfertigkeitReader::new, "Beidhändiger Kampf I-II", "Belastungsgewöhnung I-II", "Wuchtschlag I-III"));
		kampfPage.getWriters().add(fillWriter(WeaponWriter::new, WeaponReader::new, "Schwerter:Langschwert", "Dolche:Messer", "Schilde:Thorwalerschild"));

		final DefaultPage sonderfertigkeitenPage = new DefaultPage("Sonderfertigkeiten");
		sonderfertigkeitenPage.getWriters().add(fillWriter(AllgemeinesonderfertigkeitWriter::new, AllgemeinesonderfertigkeitReader::new, "Entfernungen schätzen", "Fertigkeitsspezialisierung (Talente)", "Geländekunde", "Wettervorhersage"));

		final DefaultPage itemPage = new DefaultPage("Gegenstände");
		itemPage.getWriters().add(fillWriter(ElixierWriter::new, ElixierReader::new, "Heiltrank", "Schmerzwein"));

		final IPage otherPage = getOtherPage();
		final IPage schipsPage = new SchicksalspunktePage();

		final DefaultPage imagePage = new DefaultPage();
		imagePage.getWriters().add(new ImageWriter(new ImageReader().readData("D:\\RP\\Bilder\\Ulmor Aurelius Stippwitz zu Wanderklamm.png")));

		setPages(Stream.of(vorteilPage, kampfPage, sonderfertigkeitenPage, otherPage, schipsPage, itemPage, imagePage));
	}

	private IPage getOtherPage() {
		final DefaultPage page = new DefaultPage("Sonstiges");

		final ParagraphData name = new ParagraphData("Name: Ulmor Aurelius Stippwitz zu Wanderklamm");
		final ParagraphData evilEnemy = new ParagraphData("Böser Namensvetter: Elfwin Wolfsgruber ist ein Entdecker/Archäologe, der sich häufig als Aurelius ausgiebt und sich auch sehr zwielichtig verhält. Sie sind Rivalen im auffinden von Schätzen.");
		final List<ParagraphData> other = Arrays.asList(name, evilEnemy);
		page.getWriters().add(new ParagraphWriter("Sonstiges", other));

		return page;
	}

}

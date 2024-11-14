package de.otpiccolo.dsa5.pdf.person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten.AllgemeinesonderfertigkeitReader;
import de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten.AllgemeinesonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.elixiere.ElixierReader;
import de.otpiccolo.dsa5.data.elixiere.ElixierWriter;
import de.otpiccolo.dsa5.data.gewandzauber.GewandzauberReader;
import de.otpiccolo.dsa5.data.gewandzauber.GewandzauberWriter;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.kampfstilsonderfertigkeiten.KampfstilsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.kampfstilsonderfertigkeiten.KampfstilsonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.nachteile.NachteilReader;
import de.otpiccolo.dsa5.data.nachteile.NachteilWriter;
import de.otpiccolo.dsa5.data.vorteile.VorteilReader;
import de.otpiccolo.dsa5.data.vorteile.VorteilWriter;
import de.otpiccolo.dsa5.data.weapon.dolche.DolchReader;
import de.otpiccolo.dsa5.data.weapon.dolche.DolchWriter;
import de.otpiccolo.dsa5.data.weapon.schwerter.SchwertReader;
import de.otpiccolo.dsa5.data.weapon.schwerter.SchwertWriter;
import de.otpiccolo.dsa5.data.zaubertanz.ZaubertanzReader;
import de.otpiccolo.dsa5.data.zaubertanz.ZaubertanzWriter;
import de.otpiccolo.dsa5.data.zaubertradition.ZauberTraditionReader;
import de.otpiccolo.dsa5.data.zaubertradition.ZauberTraditionWriter;
import de.otpiccolo.dsa5.data.zaubertrick.ZaubertrickReader;
import de.otpiccolo.dsa5.data.zaubertrick.ZaubertrickWriter;
import de.otpiccolo.dsa5.pdf.data.image.ImageReader;
import de.otpiccolo.dsa5.pdf.data.image.ImageWriter;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;
import de.otpiccolo.dsa5.pdf.page.IPage;
import de.otpiccolo.dsa5.pdf.page.predefined.SchicksalspunktePage;
import de.otpiccolo.dsa5.pdf.page.predefined.ZauberModPage;

/**
 * Information about Manjula.
 */
public class Manjula extends Person {

	/**
	 * Constructor.
	 */
	public Manjula() {
		final DefaultPage vorteilPage = new DefaultPage("Vorteile & Nachteile");
		vorteilPage.getWriters().add(fillWriter(VorteilWriter::new, VorteilReader::new, "Begabung", "Gutaussehend I-II", "Hart im Nehmen", "Verbesserte Regeneration (Astralenergie) I-III", "Verbesserte Regeneration (Lebensenergie) I-III", "Weitreichende Zaubertänze I-III"));
		vorteilPage.getWriters().add(fillWriter(NachteilWriter::new, NachteilReader::new, "Angst vor … I-III", "Giftanfällig I-II", "Krankheitsanfällig I-II", "Unfähig"));

		final DefaultPage kampfPage = new DefaultPage("Kampfsonderfertigkeiten");
		kampfPage.getWriters().add(fillWriter(KampfsonderfertigkeitWriter::new, KampfsonderfertigkeitReader::new, "Einhändiger Kampf", "Finte I-III", "Klingensturm"));
		kampfPage.getWriters().add(fillWriter(KampfstilsonderfertigkeitWriter::new, KampfstilsonderfertigkeitReader::new, "Baburin-Stil"));
		kampfPage.getWriters().add(fillWriter(SchwertWriter::new, SchwertReader::new, "Khunchomer"));
		kampfPage.getWriters().add(fillWriter(DolchWriter::new, DolchReader::new, "Waqqif"));

		final DefaultPage sonderfertigkeitenPage = new DefaultPage("Sonderfertigkeiten");
		sonderfertigkeitenPage.getWriters().add(fillWriter(AllgemeinesonderfertigkeitWriter::new, AllgemeinesonderfertigkeitReader::new, "Rahjasutra-Kenntnisse", "Ortskenntnis"));

		final DefaultPage zaubertanzPage = new DefaultPage("Zaubertänze");
		zaubertanzPage.getWriters().add(fillWriter(ZaubertanzWriter::new, ZaubertanzReader::new, "Tanz der Bilder", "Tanz der Liebe", "Tanz der Unantastbarkeit", "Tanz der Verteidigung", "Tanz ohne Ende"));

		final DefaultPage gewandzauberPage = new DefaultPage("Gewandzauber");
		gewandzauberPage.getWriters().add(fillWriter(GewandzauberWriter::new, GewandzauberReader::new, "Besitzanspruch", "Diebesgewand", "Schmutzabweisend"));

		final DefaultPage sonstigeZauberPage = new DefaultPage("Zaubertricks & Tradition");
		sonstigeZauberPage.getWriters().add(fillWriter(ZaubertrickWriter::new, ZaubertrickReader::new, "Bauchreden", "Duft", "Glücksgriff", "Schminken"));
		sonstigeZauberPage.getWriters().add(fillWriter(ZauberTraditionWriter::new, ZauberTraditionReader::new, "Zaubertänzer"));

		final DefaultPage itemPage = new DefaultPage("Gegenstände");
		itemPage.getWriters().add(fillWriter(ElixierWriter::new, ElixierReader::new, "Heiltrank", "Zaubertrank"));

		final IPage otherPage = getOtherPage();
		final IPage zauberModPage = new ZauberModPage();
		final IPage schipsPage = new SchicksalspunktePage();

		final DefaultPage imagePage = new DefaultPage();
		imagePage.getWriters().add(new ImageWriter(new ImageReader().readData("D:\\RP\\Bilder\\Manjula bint Kirisha at Baburin.jpg")));

		setPages(Stream.of(vorteilPage, kampfPage, sonderfertigkeitenPage, zaubertanzPage, gewandzauberPage, sonstigeZauberPage, otherPage, zauberModPage, schipsPage, itemPage, imagePage));
	}

	private IPage getOtherPage() {
		final DefaultPage page = new DefaultPage("Sonstiges");

		final ParagraphData name = new ParagraphData("Name: Manjula bint Kirisha at Baburin (Manjula, Tochter von Kirisha aus Baburin)");
		final List<ParagraphData> other = Arrays.asList(name);
		page.getWriters().add(new ParagraphWriter("Sonstiges", other));

		return page;
	}

}

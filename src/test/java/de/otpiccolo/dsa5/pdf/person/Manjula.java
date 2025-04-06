package de.otpiccolo.dsa5.pdf.person;

import java.util.Arrays;
import java.util.Collections;
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
import de.otpiccolo.dsa5.data.magischesonderfertigkeiten.MagischeSonderfertigkeitReader;
import de.otpiccolo.dsa5.data.magischesonderfertigkeiten.MagischeSonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.nachteile.NachteilReader;
import de.otpiccolo.dsa5.data.nachteile.NachteilWriter;
import de.otpiccolo.dsa5.data.talentstilsonderfertigkeiten.TalentstilsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.talentstilsonderfertigkeiten.TalentstilsonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.vorteile.VorteilReader;
import de.otpiccolo.dsa5.data.vorteile.VorteilWriter;
import de.otpiccolo.dsa5.data.weapon.WeaponReader;
import de.otpiccolo.dsa5.data.weapon.WeaponWriter;
import de.otpiccolo.dsa5.data.zauber.ZauberReader;
import de.otpiccolo.dsa5.data.zauber.ZauberWriter;
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
import de.otpiccolo.dsa5.pdf.page.predefined.geweihte.RondraPage;

/**
 * Information about Manjula.
 */
public class Manjula extends Person {

	/**
	 * Constructor.
	 */
	public Manjula() {
		final DefaultPage vorteilPage = new DefaultPage("Vorteile & Nachteile");
		vorteilPage.getWriters().add(fillWriter(VorteilWriter::new, VorteilReader::new, "Begabung", "Gutaussehend I-II", "Hart im Nehmen", "Verbesserte Regeneration (Astralenergie) I-III", "Weitreichende Zaubertänze I-III"));
		vorteilPage.getWriters().add(fillWriter(NachteilWriter::new, NachteilReader::new, "Angst vor … I-III", "Giftanfällig I-II", "Krankheitsanfällig I-II", "Unfähig"));

		final DefaultPage kampfPage = new DefaultPage("Kampfsonderfertigkeiten");
		kampfPage.getWriters().add(fillWriter(KampfsonderfertigkeitWriter::new, KampfsonderfertigkeitReader::new, "Einhändiger Kampf", "Finte I-III", "Klingensturm"));
		kampfPage.getWriters().add(fillWriter(KampfstilsonderfertigkeitWriter::new, KampfstilsonderfertigkeitReader::new, "Baburin-Stil"));
		kampfPage.getWriters().add(fillWriter(WeaponWriter::new, WeaponReader::new, "Schwerter:Khunchomer", "Dolche:Waqqif"));
		kampfPage.getWriters().add(new ParagraphWriter("Kor-Amulett", Collections.singleton(new ParagraphData("Das Kor-Amulett ermöglicht es dem Träger, einen weiteren Angriff in dieser Kampfrunde durchzuführen. Dafür muss der Träger 2 HP dem Amulett opfern. Dieser Verlust von HP kann nicht verhindert werden."))));

		final DefaultPage sonderfertigkeitenPage = new DefaultPage("Sonderfertigkeiten");
		sonderfertigkeitenPage.getWriters().add(fillWriter(MagischeSonderfertigkeitWriter::new, MagischeSonderfertigkeitReader::new, "Magische Regeneration I-II", "Meisterliche Regeneration"));
		sonderfertigkeitenPage.getWriters().add(fillWriter(AllgemeinesonderfertigkeitWriter::new, AllgemeinesonderfertigkeitReader::new, "Ortskenntnis", "Rahjasutra-Kenntnisse"));
		sonderfertigkeitenPage.getWriters().add(fillWriter(TalentstilsonderfertigkeitWriter::new, TalentstilsonderfertigkeitReader::new, "Weg der Künstlerin"));
		sonderfertigkeitenPage.getWriters().add(new ParagraphWriter("Rondras Segen", Collections.singleton(new ParagraphData("Rondras Segen ermöglicht einen einmal pro Tag, für ein Rondra wohlgefällige Tat zusätzliche 4FP zu bekommen. Dies kann nach einem Wurf dazu verwendet werden, um den QS der Probe zu erhöhen oder einen Misserfolg noch zu einem Erfolg zu machen. Dies gilt auch für Angriffe/Verteidigungen."))));

		final DefaultPage zaubertanzPage = new DefaultPage("Zaubertänze");
		zaubertanzPage.getWriters().add(fillWriter(ZaubertanzWriter::new, ZaubertanzReader::new, "Tanz der Bilder", "Tanz der Liebe", "Tanz der Verteidigung", "Tanz ohne Ende"));

		final DefaultPage gewandzauberPage = new DefaultPage("Gewandzauber & Zaubersprüche");
		gewandzauberPage.getWriters().add(fillWriter(GewandzauberWriter::new, GewandzauberReader::new, "Besitzanspruch", "Diebesgewand", "Schmutzabweisend", "Schutzgewand I-II"));
		gewandzauberPage.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Flim Flam"));
		gewandzauberPage.getWriters().add(new ParagraphWriter(Collections.singleton(new ParagraphData("Der Flim Flam wird über die Fähigkeit \"Tanzen\" aktiviert und darüber wird auch der QS bestimmt. Die einmaligen Aktivierungskosten entfallen, es müssen nur die AsP zum aufrechterhalten des Zaubers ausgegeben werden. Das Licht geht immer vom Zaubernden aus und strahlt wie die Sonne."))));

		final DefaultPage sonstigeZauberPage = new DefaultPage("Zaubertricks & Tradition");
		sonstigeZauberPage.getWriters().add(fillWriter(ZaubertrickWriter::new, ZaubertrickReader::new, "Bauchreden", "Duft", "Glücksgriff", "Schminken"));
		sonstigeZauberPage.getWriters().add(fillWriter(ZauberTraditionWriter::new, ZauberTraditionReader::new, "Zaubertänzer"));

		final DefaultPage itemPage = new DefaultPage("Gegenstände");
		itemPage.getWriters().add(fillWriter(ElixierWriter::new, ElixierReader::new, "Berserkerelixier", "Heiltrank", "Schmerzwein", "Zaubertrank"));

		final IPage rondraPage = new RondraPage();
		final IPage otherPage = getOtherPage();
		final IPage zauberModPage = new ZauberModPage();
		final IPage schipsPage = new SchicksalspunktePage();

		final DefaultPage imagePage = new DefaultPage();
		imagePage.getWriters().add(new ImageWriter(new ImageReader().readData("D:\\RP\\Bilder\\Manjula bint Kirisha at Baburin.jpg")));

		setPages(Stream.of(vorteilPage, kampfPage, sonderfertigkeitenPage, zaubertanzPage, gewandzauberPage, sonstigeZauberPage, otherPage, rondraPage, zauberModPage, schipsPage, itemPage, imagePage));
	}

	private IPage getOtherPage() {
		final DefaultPage page = new DefaultPage("Sonstiges");

		final ParagraphData name = new ParagraphData("Name: Manjula bint Kirisha at Baburin (Manjula, Tochter von Kirisha aus Baburin)");
		final List<ParagraphData> other = Arrays.asList(name);
		page.getWriters().add(new ParagraphWriter("Sonstiges", other));

		return page;
	}

}

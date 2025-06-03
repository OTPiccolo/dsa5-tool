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
import de.otpiccolo.dsa5.pdf.data.IDataWriter;
import de.otpiccolo.dsa5.pdf.data.image.ImageReader;
import de.otpiccolo.dsa5.pdf.data.image.ImageWriter;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;
import de.otpiccolo.dsa5.pdf.data.table.TableData;
import de.otpiccolo.dsa5.pdf.data.table.TableWriter;
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
		vorteilPage.getWriters().add(fillWriter(VorteilWriter::new, VorteilReader::new, "Begabung", "Gutaussehend I-II", "Hart im Nehmen", "Verbesserte Regeneration (Astralenergie) I-III", "Weitreichende Zaubertänze I-III", "Zäher Hund"));
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
		sonderfertigkeitenPage.getWriters().add(new ParagraphWriter("Krieger der Rondra", Collections.singleton(new ParagraphData("Bringt Erleichterung von 1 auf Überreden und Bekehren/Überzeugen bei Geweihten der Ronder, Praios und Kor."))));
		sonderfertigkeitenPage.getWriters().add(new ParagraphWriter("Stille der Nacht", Collections.singleton(new ParagraphData("Erleichterung von 1 auf Verbergen in Dunkelheit."))));
		sonderfertigkeitenPage.getWriters().add(new ParagraphWriter("Spezieller Stoff für Gewand", Collections.singleton(new ParagraphData("Erhält 2 RS gegen kurze Waffen."))));

		final DefaultPage zaubertanzPage = new DefaultPage("Zaubertänze");
		zaubertanzPage.getWriters().add(fillWriter(ZaubertanzWriter::new, ZaubertanzReader::new, "Tanz der Bilder", "Tanz der Liebe", "Tanz der Verteidigung", "Tanz ohne Ende"));

		final DefaultPage gewandzauberPage = new DefaultPage("Gewandzauber");
		gewandzauberPage.getWriters().add(fillWriter(GewandzauberWriter::new, GewandzauberReader::new, "Besitzanspruch", "Diebesgewand", "Schmutzabweisend", "Schutzgewand I-II"));

		final DefaultPage zauberPage = new DefaultPage("Zaubersprüche");
		zauberPage.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Chamaelioni"));
		zauberPage.getWriters().add(new ParagraphWriter(Collections.singleton(new ParagraphData("Chamelioni wird mit einem FP 14 gewirkt. Attribute sind: IN/CH/GE"))));
		zauberPage.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Flim Flam"));
		zauberPage.getWriters().add(new ParagraphWriter(Collections.singleton(new ParagraphData("Der Flim Flam wird über die Fähigkeit \"Tanzen\" aktiviert und darüber wird auch der QS bestimmt. Die einmaligen Aktivierungskosten entfallen, es müssen nur die AsP zum aufrechterhalten des Zaubers ausgegeben werden. Das Licht geht immer vom Zaubernden aus und strahlt wie die Sonne."))));

		final DefaultPage sonstigeZauberPage = new DefaultPage("Zaubertricks & Tradition");
		sonstigeZauberPage.getWriters().add(fillWriter(ZaubertrickWriter::new, ZaubertrickReader::new, "Bauchreden", "Duft", "Glücksgriff", "Schminken"));
		sonstigeZauberPage.getWriters().add(fillWriter(ZauberTraditionWriter::new, ZauberTraditionReader::new, "Zaubertänzer"));

		final IPage itemPage = getItemPage();
		final IPage rondraPage = new RondraPage();
		final IPage otherPage = getOtherPage();
		final IPage zauberModPage = new ZauberModPage();
		final IPage schipsPage = new SchicksalspunktePage();

		final DefaultPage imagePage = new DefaultPage();
		imagePage.getWriters().add(new ImageWriter(new ImageReader().readData("D:\\RP\\Bilder\\Manjula bint Kirisha at Baburin.jpg")));

		setPages(Stream.of(vorteilPage, kampfPage, sonderfertigkeitenPage, zaubertanzPage, gewandzauberPage, zauberPage, sonstigeZauberPage, otherPage, rondraPage, zauberModPage, schipsPage, itemPage, imagePage));
	}

	private IPage getItemPage() {
		final ParagraphWriter feuersalbe = new ParagraphWriter("Feuersalbe", Collections.singleton(new ParagraphData("Die Feuersalbe wird für 1 Minute auf die Haut aufgetragen und hält dann für die nächsten 8 Stunden. In der Zeit kann man nicht den Status 'Brennen' erhalten, und erhält 2 Schaden weniger durch Feuerschaden.")));
		final ParagraphWriter platzhalter = new ParagraphWriter("Platzhalter", Collections.singleton(new ParagraphData("Platzhalter")));
		final List<IDataWriter> row1 = List.of(fillWriter(ElixierWriter::new, ElixierReader::new, "Heiltrank"), fillWriter(ElixierWriter::new, ElixierReader::new, "Zaubertrank"));
		final List<IDataWriter> row2 = List.of(fillWriter(ElixierWriter::new, ElixierReader::new, "Berserkerelixier"), fillWriter(ElixierWriter::new, ElixierReader::new, "Schmerzwein"));
		final List<IDataWriter> row3 = List.of(feuersalbe, platzhalter);
		final List<List<IDataWriter>> rows = List.of(row1, row2, row3);
		final TableData table = new TableData(rows, 5f);
		final DefaultPage itemPage = new DefaultPage("Gegenstände");
		itemPage.getWriters().add(new TableWriter(table));
		return itemPage;
	}

	private IPage getOtherPage() {
		final DefaultPage page = new DefaultPage("Sonstiges");

		final ParagraphData name = new ParagraphData("Name: Manjula bint Kirisha at Baburin (Manjula, Tochter von Kirisha aus Baburin)");
		final ParagraphData item1 = new ParagraphData("Blut von Belkelel: Eine Phiole mit Blut von Belkelel. Ist ein Talisman, der Manjula den Vorteil 'Zäher Hund' gewährt.");
		final ParagraphData item2 = new ParagraphData("Stoff einer Magierrobe: Der Stoff der Magierrobe ist in Manjulas Kleidung eingewebt. Diese verleiht ihrer Kleidung 2RS gegen kurze Waffen.");
		final ParagraphData title1 = new ParagraphData("Rondras Segen: Erhalten als Belohnung für den Sieg im Rondrakampf. Den Stahlgrafen im Duell im Finale bezwungen.");
		final ParagraphData title2 = new ParagraphData("Krieger der Rondra: Erhalten durch die Verteidigung des Rondra Tempels gegen den Drachen Maldrakar.");
		final ParagraphData boon = new ParagraphData("Stille der Nacht: Geschenk Borons. Gibt den Zauber \"Chamelioni\" auf FW14. Gibt auch +1 Erleichterung auf \"Verbergen\" in Dunkelheit.");
		final List<ParagraphData> other = Arrays.asList(name, item1, item2, title1, title2, boon);
		page.getWriters().add(new ParagraphWriter("Sonstiges", other));

		return page;
	}

}

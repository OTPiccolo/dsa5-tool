package de.otpiccolo.dsa5.pdf.person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import de.otpiccolo.dsa5.data.elixiere.ElixierReader;
import de.otpiccolo.dsa5.data.elixiere.ElixierWriter;
import de.otpiccolo.dsa5.data.hexenfluch.HexenfluchReader;
import de.otpiccolo.dsa5.data.hexenfluch.HexenfluchWriter;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.nachteile.NachteilReader;
import de.otpiccolo.dsa5.data.nachteile.NachteilWriter;
import de.otpiccolo.dsa5.data.vorteile.VorteilReader;
import de.otpiccolo.dsa5.data.vorteile.VorteilWriter;
import de.otpiccolo.dsa5.data.zauber.ZauberReader;
import de.otpiccolo.dsa5.data.zauber.ZauberWriter;
import de.otpiccolo.dsa5.data.zaubererweiterung.ZaubererweiterungReader;
import de.otpiccolo.dsa5.data.zaubererweiterung.ZaubererweiterungWriter;
import de.otpiccolo.dsa5.data.zaubertradition.ZauberTraditionReader;
import de.otpiccolo.dsa5.data.zaubertradition.ZauberTraditionWriter;
import de.otpiccolo.dsa5.pdf.data.image.ImageReader;
import de.otpiccolo.dsa5.pdf.data.image.ImageWriter;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;
import de.otpiccolo.dsa5.pdf.page.IPage;
import de.otpiccolo.dsa5.pdf.page.predefined.SchicksalspunktePage;
import de.otpiccolo.dsa5.pdf.page.predefined.ZauberModPage;

/**
 * Information about Thyra.
 */
public class Thyra extends Person {

	/**
	 * Constructor.
	 */
	public Thyra() {
		final DefaultPage vorteilNachteilPage = new DefaultPage("Vorteile & Nachteile");
		vorteilNachteilPage.getWriters().add(fillWriter(VorteilWriter::new, VorteilReader::new, "Altersresistenz (*)", "Beidhändig", "Kälteresistenz", "Verbesserte Regeneration (Astralenergie) I-III", "Waffenbegabung"));
		vorteilNachteilPage.getWriters().add(fillWriter(NachteilWriter::new, NachteilReader::new, "Hitzeempfindlich", "Verpflichtungen I-III", "Zauberanfällig I-II"));

		final DefaultPage kampfPage = new DefaultPage("Kampfsonderfertigkeiten");
		kampfPage.getWriters().add(fillWriter(KampfsonderfertigkeitWriter::new, KampfsonderfertigkeitReader::new, "Beidhändiger Kampf I-II", "Belastungsgewöhnung I-II", "Finte I-III", "Wuchtschlag I-III"));

		final DefaultPage zauberPage1 = new DefaultPage("Zaubersprüche");
		zauberPage1.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Balsam Salabunde", "Blick in die Gedanken"));
		zauberPage1.getWriters().add(fillWriter(ZaubererweiterungWriter::new, ZaubererweiterungReader::new, "Blick in die Gedanken#Sichtung 1"));
		zauberPage1.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Harmlose Gestalt", "Hexenknoten"));
		final DefaultPage zauberPage2 = new DefaultPage();
		zauberPage2.getWriters().add(fillWriter(ZaubererweiterungWriter::new, ZaubererweiterungReader::new, "Hexenknoten#Ausgenommen Freunde 1"));
		zauberPage2.getWriters().add(fillWriter(HexenfluchWriter::new, HexenfluchReader::new, "Hagelschlag"));
		zauberPage2.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Katzenaugen"));
		zauberPage2.getWriters().add(fillWriter(ZaubererweiterungWriter::new, ZaubererweiterungReader::new, "Katzenaugen#Längere Aufrechterhaltung"));
		zauberPage2.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Krötensprung", "Odem Arcanum"));
		final DefaultPage zauberPage3 = new DefaultPage();
		zauberPage3.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Tiergedanken"));
		final DefaultPage zauberTraditionPage = new DefaultPage();
		zauberTraditionPage.getWriters().add(fillWriter(ZauberTraditionWriter::new, ZauberTraditionReader::new, "Hexen"));

		final DefaultPage itemPage = new DefaultPage("Gegenstände");
		itemPage.getWriters().add(fillWriter(ElixierWriter::new, ElixierReader::new, "Berserkerelixier", "Heiltrank", "Zaubertrank"));

		final IPage otherPage = getOtherPage();
		final IPage modPage = new ZauberModPage();
		final IPage schipsPage = new SchicksalspunktePage();

		final DefaultPage imagePage = new DefaultPage();
		imagePage.getWriters().add(new ImageWriter(new ImageReader().readData("D:\\RP\\Bilder\\Thyra.jpg")));

		setPages(Stream.of(vorteilNachteilPage, kampfPage, zauberPage1, zauberPage2, zauberPage3, zauberTraditionPage, itemPage, otherPage, modPage, schipsPage, imagePage));
	}

	private IPage getOtherPage() {
		final DefaultPage page = new DefaultPage("Sonstiges");

		final ParagraphData father = new ParagraphData("Vater: Kjaskar. Tyrann. Tot (vermutlich).");
		final ParagraphData mother = new ParagraphData("Mutter: Norhild. Unklar. Vermutlich bei Geburt gestorben, aber offen.");
		final ParagraphData mentor = new ParagraphData("Lehrmeisterin: Runa. Hexe. Ehemaliger 'Reaper'. Erhält 'Kälteimmunität' von ihr.");
		final ParagraphData protector = new ParagraphData("Beschützerin von: Tiri. Ehemalige Adelige, deren Eltern getötet wurden.");
		final List<ParagraphData> family = Arrays.asList(father, mother, mentor, protector);
		page.getWriters().add(new ParagraphWriter("Familie", family));

		final ParagraphData village = new ParagraphData("Dorf Drakkarsheim auf der Insel Lassir.");
		final List<ParagraphData> home = Arrays.asList(village);
		page.getWriters().add(new ParagraphWriter("Heimat", home));

		final ParagraphData sword = new ParagraphData("Kristallschlag: Magisches Schwert der Kälte. Ehemalige Klinge von Runa. Kann nur mit 'Kälteimmunität' geführt werden. Macht Frostschaden.");
		final ParagraphData amulett = new ParagraphData("Eulenamulett: Geschenk von Tiri. Ermöglicht einmal pro Tag für 10 Minuten, +4 Zähigkeit/Schaden/Körperkraft. Eine Aktion als Aktivierung.");
		final ParagraphData poem1 = new ParagraphData("In Thorwals stürmischer, wilder See, Segelt mutig eine Maid, so kühn und frei. Mit Herz voll Güte, barmherzig wie Schnee, Bringt sie Hoffnung, wo Dunkelheit sei.");
		final ParagraphData poem2 = new ParagraphData("An ihrer Seite, eine Schneeeule weiß, Ein stiller Wächter in der dunklen Nacht. Sie kämpfen gemeinsam, so sicher und leis, Gegen das Böse, mit mutiger Macht.");
		final ParagraphData poem3 = new ParagraphData("Schwarzmagier und Piraten sind ihr Ziel, Mit Schwert und Magie, stets voll Gnade. Für eine Welt, die in Frieden erblüht, Dank der Thorwalerin und ihrer heldenhaften Pfade.");
		final List<ParagraphData> items = Arrays.asList(sword, amulett);
		page.getWriters().add(new ParagraphWriter("Gegenstände", items));
		final List<ParagraphData> poem = Arrays.asList(poem1, poem2, poem3);
		page.getWriters().add(new ParagraphWriter("Gedicht von Tiri", poem));

		final ParagraphData heroOfZyklopenInsel = new ParagraphData("Held der Zyklopen-Insel: Hat die Stadt <Name> vor dem Untergang durch einen Angriff der Fischmenschen gerettet. Dafür wurde sie zur Heldin der Zyklopen-Inseln ernannt. Kriegt +1 auf alle sozialen Interaktionen mit Horasiern.");
		final ParagraphData erweiterung = new ParagraphData("Erweiterung Ausgetauscht: Optolith kann anscheinend \"Blick in die Gedanken # Sichtung 1\" nicht. Darum ist angzeigte Erweiterung \"#Kampfhandlung vorhersehen\" als Ersatz genommen.");
		final List<ParagraphData> other = Arrays.asList(heroOfZyklopenInsel, erweiterung);
		page.getWriters().add(new ParagraphWriter("Sonstiges", other));

		return page;
	}

}

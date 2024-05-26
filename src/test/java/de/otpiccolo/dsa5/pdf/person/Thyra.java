package de.otpiccolo.dsa5.pdf.person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

		final IPage bioPage = getOtherPage();
		final IPage modPage = new ZauberModPage();
		final IPage schipsPage = new SchicksalspunktePage();

		final DefaultPage imagePage = new DefaultPage();
		imagePage.getWriters().add(new ImageWriter(new ImageReader().readData("D:\\RP\\Bilder\\Thyra.jpg")));

		setPages(Stream.of(bioPage, vorteilNachteilPage, kampfPage, zauberPage1, zauberPage2, zauberPage3, bioPage, modPage, schipsPage, imagePage));
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
		final List<ParagraphData> items = Arrays.asList(sword, amulett);
		page.getWriters().add(new ParagraphWriter("Gegenstände", items));

		final ParagraphData heroOfZyklopenInsel = new ParagraphData("Held der Zyklopen-Insel: Hat die Stadt <Name> vor dem Untergang durch einen Angriff der Fischmenschen gerettet. Dafür wurde sie zur Heldin der Zyklopen-Inseln ernannt. Kriegt +1 auf alle sozialen Interaktionen mit Horasiern.");
		final ParagraphData erweiterung = new ParagraphData("Erweiterung Ausgetauscht: Optolith kann anscheinend \"Blick in die Gedanken # Sichtung 1\" nicht. Darum ist angzeigte Erweiterung \"#Kampfhandlung vorhersehen\" als Ersatz genommen.");
		final List<ParagraphData> other = Arrays.asList(heroOfZyklopenInsel, erweiterung);
		page.getWriters().add(new ParagraphWriter("Sonstiges", other));

		return page;
	}

}

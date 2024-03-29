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
import de.otpiccolo.dsa5.pdf.data.image.ImageReader;
import de.otpiccolo.dsa5.pdf.data.image.ImageWriter;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;
import de.otpiccolo.dsa5.pdf.page.IPage;
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
		kampfPage.getWriters().add(fillWriter(KampfsonderfertigkeitWriter::new, KampfsonderfertigkeitReader::new, "Beidhändiger Kampf I-II", "Belastungsgewöhnung I-II", "Finte I-III"));

		final DefaultPage zauberPage1 = new DefaultPage("Zaubersprüche");
		zauberPage1.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Balsam Salabunde", "Blick in die Gedanken", "Harmlose Gestalt", "Hexenknoten"));
		final DefaultPage zauberPage2 = new DefaultPage();
		zauberPage2.getWriters().add(fillWriter(HexenfluchWriter::new, HexenfluchReader::new, "Hagelschlag"));
		zauberPage2.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Katzenaugen", "Krötensprung", "Odem Arcanum", "Tiergedanken"));

		final IPage bioPage = getBioPage();
		final IPage modPage = new ZauberModPage();

		final DefaultPage imagePage = new DefaultPage();
		imagePage.getWriters().add(new ImageWriter(new ImageReader().readData("D:\\RP\\Bilder\\Thyra.jpg")));

		setPages(Stream.of(bioPage, vorteilNachteilPage, kampfPage, zauberPage1, zauberPage2, modPage, imagePage));
	}

	private IPage getBioPage() {
		final DefaultPage page = new DefaultPage("Sonstiges");

		final ParagraphData father = new ParagraphData("Vater: Kjaskar. Tyrann. Tot (vermutlich).");
		final ParagraphData mother = new ParagraphData("Mutter: Norhild. Unklar. Vermutlich bei Geburt gestorben, aber offen.");
		final ParagraphData mentor = new ParagraphData("Lehrmeisterin: Runa. Hexe. Ehemaliger 'Reaper'. Erhält 'Kälteimmunität' von ihr.");
		final List<ParagraphData> family = Arrays.asList(father, mother, mentor);
		page.getWriters().add(new ParagraphWriter("Familie", family));

		final ParagraphData village = new ParagraphData("Dorf Drakkarsheim auf der Insel Lassir.");
		final List<ParagraphData> home = Arrays.asList(village);
		page.getWriters().add(new ParagraphWriter("Heimat", home));

		final ParagraphData sword = new ParagraphData("Kristallschlag: Magisches Schwert der Kälte. Ehemalige Klinge von Runa. Kann nur mit 'Kälteimmunität' geführt werden. Macht Frostschaden.");
		final List<ParagraphData> items = Arrays.asList(sword);
		page.getWriters().add(new ParagraphWriter("Gegenstände", items));

		return page;
	}

}

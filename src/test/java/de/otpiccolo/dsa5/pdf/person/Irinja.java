package de.otpiccolo.dsa5.pdf.person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.kampfstilsonderfertigkeiten.KampfstilsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.kampfstilsonderfertigkeiten.KampfstilsonderfertigkeitWriter;
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
import de.otpiccolo.dsa5.pdf.page.predefined.SchicksalspunktePage;

/**
 * Information about Irinja.
 */
public class Irinja extends Person {

	/**
	 * Constructor.
	 */
	public Irinja() {
		final DefaultPage vorteilNachteilPage = new DefaultPage("Vorteile & Nachteile");
		vorteilNachteilPage.getWriters().add(fillWriter(VorteilWriter::new, VorteilReader::new, "Begabung", "Beidhändig", "Nichtschläfer (*)", "Schlangenmensch", "Waffenbegabung", "Zauberer"));
		vorteilNachteilPage.getWriters().add(new ParagraphWriter("Schlangenzunge", Collections.singleton(new ParagraphData("Geruchssinn auf 10m Meter. Gespaltene Zunge wie bei einer Schlange."))));
		vorteilNachteilPage.getWriters().add(fillWriter(NachteilWriter::new, NachteilReader::new, "Niedrige Zähigkeit", "Unverträglichkeit gegenüber Alkohol"));

		final DefaultPage kampfPage = new DefaultPage("Kampfsonderfertigkeiten");
		kampfPage.getWriters().add(fillWriter(KampfsonderfertigkeitWriter::new, KampfsonderfertigkeitReader::new, "Beidhändiger Kampf I-II", "Finte I-III", "Präziser Schuss/Wurf I-III", "Präziser Stich I-III", "Schnellziehen", "Verbessertes Ausweichen I-III"));
		kampfPage.getWriters().add(fillWriter(KampfstilsonderfertigkeitWriter::new, KampfstilsonderfertigkeitReader::new, "Fedorino-Stil"));

		final DefaultPage zauberPage = new DefaultPage("Zaubersprüche");
		zauberPage.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Axxeleratus", "Chamaelioni", "Exposami"));

		final IPage otherPage = getOtherPage();
		final IPage schipsPage = new SchicksalspunktePage();

		final DefaultPage imagePage = new DefaultPage();
		imagePage.getWriters().add(new ImageWriter(new ImageReader().readData("D:\\RP\\Bilder\\Irinja.png")));

		setPages(Stream.of(vorteilNachteilPage, kampfPage, zauberPage, otherPage, schipsPage, imagePage));
	}

	private IPage getOtherPage() {
		final DefaultPage page = new DefaultPage("Sonstiges");

		final ParagraphData father = new ParagraphData("Vater: Andrej Rastislav.");
		final ParagraphData circus = new ParagraphData("Zirkus: Wandernder Hut, geleitet von der Großartigen und Mächtigen Trrrrrrrixie!");
		final ParagraphData ravenna = new ParagraphData("Ravenna: Eine junge Zauberin/Diebin, mit der Irinja zusammen reist!");
		final List<ParagraphData> family = Arrays.asList(father, circus, ravenna);
		page.getWriters().add(new ParagraphWriter("Familie", family));

		final ParagraphData boneDagger = new ParagraphData("Knochendolch: +4 Schaden auf Meucheln.");
		final ParagraphData discDagger = new ParagraphData("Scheibendolch: Gegner mit RK >= 4 erhalten -2 auf RK und +1 Schaden. Kein Kreuzblock möglich.");
		final ParagraphData poisonSpit = new ParagraphData("Giftspucke: Wird regeltechnisch wie 'Wurfdolch' gehandhabt. 1/Tag, 1W6+5 Schaden + Ätzend (W3/KR) ");
		final List<ParagraphData> weapons = Arrays.asList(boneDagger, discDagger, poisonSpit);
		page.getWriters().add(new ParagraphWriter("Waffen", weapons));

		final ParagraphData butteflyAmulet = new ParagraphData("Schmetterlings-Amulett: Kann 1/Tag Saturias Herrlichkeit zaubern als QS1 für 3 Stunden.");
		final ParagraphData baronRing = new ParagraphData("Ring des Barons: Erhält den Vorteil 'Nichtschläfer', aber auch -1 LP Regeneration. Untote ignorieren den Träger, solange der Träger nicht aktiv gegen die Untoten vorgeht. Beim Abziehen des Rings gibt es 3W6 Schaden.");
		final ParagraphData peraineSymbol = new ParagraphData("Peraine Symbol: Erhält +1 LP/AsP pro Rast. Einmal pro Tag als freie Aktion kann eine Stufe Betäubung/Paralyse/Schmerz entfernt werden.");
		final List<ParagraphData> items = Arrays.asList(butteflyAmulet, baronRing, peraineSymbol);
		page.getWriters().add(new ParagraphWriter("Gegenstände", items));

		return page;
	}

}

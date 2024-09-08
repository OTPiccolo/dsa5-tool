package de.otpiccolo.dsa5.pdf.person;

import java.util.stream.Stream;

import de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten.AllgemeinesonderfertigkeitReader;
import de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten.AllgemeinesonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.elfenlied.ElfenliedReader;
import de.otpiccolo.dsa5.data.elfenlied.ElfenliedWriter;
import de.otpiccolo.dsa5.data.elixiere.ElixierReader;
import de.otpiccolo.dsa5.data.elixiere.ElixierWriter;
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
import de.otpiccolo.dsa5.data.zauberstilsonderfertigkeiten.ZauberstilsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.zauberstilsonderfertigkeiten.ZauberstilsonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.zaubertrick.ZaubertrickReader;
import de.otpiccolo.dsa5.data.zaubertrick.ZaubertrickWriter;
import de.otpiccolo.dsa5.datazaubertradition.ZauberTraditionReader;
import de.otpiccolo.dsa5.datazaubertradition.ZauberTraditionWriter;
import de.otpiccolo.dsa5.pdf.data.image.ImageReader;
import de.otpiccolo.dsa5.pdf.data.image.ImageWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;
import de.otpiccolo.dsa5.pdf.page.IPage;
import de.otpiccolo.dsa5.pdf.page.predefined.SchicksalspunktePage;
import de.otpiccolo.dsa5.pdf.page.predefined.ZauberModPage;

/**
 * Information about Thyra.
 */
public class Adariel extends Person {

	/**
	 * Constructor.
	 */
	public Adariel() {
		final DefaultPage vorteilPage = new DefaultPage("Vorteile");
		vorteilPage.getWriters().add(fillWriter(VorteilWriter::new, VorteilReader::new, "Altersresistenz (*)", "Dunkelsicht I-II (*)", "Nichtschläfer (*)", "Verbesserte Regeneration (Astralenergie) I-III", "Wohlklang", "Zweistimmiger Gesang (*)"));

		final DefaultPage nachteilPage = new DefaultPage("Nachteile");
		nachteilPage.getWriters().add(fillWriter(NachteilWriter::new, NachteilReader::new, "Körpergebundene Kraft", "Lästige Mindergeister", "Sensibler Geruchssinn (*)", "Unfähig", "Unverträglichkeit gegenüber Alkohol", "Wahrer Name"));

		final DefaultPage kampfPage = new DefaultPage("Kampfsonderfertigkeiten");
		kampfPage.getWriters().add(fillWriter(KampfsonderfertigkeitWriter::new, KampfsonderfertigkeitReader::new, "Belastungsgewöhnung I-II", "Schnellladen (Kampftechnik)"));
		kampfPage.getWriters().add(fillWriter(KampfstilsonderfertigkeitWriter::new, KampfstilsonderfertigkeitReader::new, "Odilmar-Stil"));

		final DefaultPage sonderfertigkeitenPage = new DefaultPage("Sonderfertigkeiten");
		sonderfertigkeitenPage.getWriters().add(fillWriter(AllgemeinesonderfertigkeitWriter::new, AllgemeinesonderfertigkeitReader::new, "Fertigkeitsspezialisierung (Talente)", "Geländekunde", "Ortskenntnis"));
		sonderfertigkeitenPage.getWriters().add(fillWriter(ZauberTraditionWriter::new, ZauberTraditionReader::new, "Elfen"));
		sonderfertigkeitenPage.getWriters().add(fillWriter(ZauberstilsonderfertigkeitWriter::new, ZauberstilsonderfertigkeitReader::new, "Wildnisläufer"));

		final DefaultPage zauberPage1 = new DefaultPage("Zauber");
		zauberPage1.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Axxeleratus", "Balsam Salabunde", "Bannbaladin", "Hilfreiche Pfote"));
		final DefaultPage zauberPage2 = new DefaultPage();
		zauberPage2.getWriters().add(fillWriter(ZauberWriter::new, ZauberReader::new, "Humuspfeil", "Spurlos", "Tiergedanken"));

		final DefaultPage sonstigeZauberPage = new DefaultPage("Zaubertricks & Elfenlieder");
		sonstigeZauberPage.getWriters().add(fillWriter(ZaubertrickWriter::new, ZaubertrickReader::new, "Blütenduft", "Elfenhaar", "Putziges Tierchen", "Schmutzabweisend"));
		sonstigeZauberPage.getWriters().add(fillWriter(ElfenliedWriter::new, ElfenliedReader::new, "Friedenslied", "Lied der Reinheit"));

		final DefaultPage itemPage = new DefaultPage("Gegenstände");
		itemPage.getWriters().add(fillWriter(ElixierWriter::new, ElixierReader::new, "Heiltrank", "Zaubertrank"));

		final IPage modPage = new ZauberModPage();
		final IPage schipsPage = new SchicksalspunktePage();

		final DefaultPage imagePage = new DefaultPage();
		imagePage.getWriters().add(new ImageWriter(new ImageReader().readData("D:\\RP\\Bilder\\Adariel Abendfreundin.png")));

		setPages(Stream.of(vorteilPage, nachteilPage, kampfPage, sonderfertigkeitenPage, zauberPage1, zauberPage2, sonstigeZauberPage, itemPage, modPage, schipsPage, imagePage));
	}

}

package de.otpiccolo.dsa5.pdf.person;

import java.util.stream.Stream;

import de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten.AllgemeinesonderfertigkeitReader;
import de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten.AllgemeinesonderfertigkeitWriter;
import de.otpiccolo.dsa5.data.elfenlied.ElfenliedReader;
import de.otpiccolo.dsa5.data.elfenlied.ElfenliedWriter;
import de.otpiccolo.dsa5.data.elixiere.ElixierReader;
import de.otpiccolo.dsa5.data.elixiere.ElixierWriter;
import de.otpiccolo.dsa5.data.nachteile.NachteilReader;
import de.otpiccolo.dsa5.data.nachteile.NachteilWriter;
import de.otpiccolo.dsa5.data.vorteile.VorteilReader;
import de.otpiccolo.dsa5.data.vorteile.VorteilWriter;
import de.otpiccolo.dsa5.data.zauber.ZauberReader;
import de.otpiccolo.dsa5.data.zauber.ZauberWriter;
import de.otpiccolo.dsa5.data.zaubertrick.ZaubertrickReader;
import de.otpiccolo.dsa5.data.zaubertrick.ZaubertrickWriter;
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
		final DefaultPage vorteilNachteilPage = new DefaultPage("Vorteile & Nachteile");
		vorteilNachteilPage.getWriters().add(fillWriter(VorteilWriter::new, VorteilReader::new, "Altersresistenz (*)", "Dunkelsicht I-II (*)", "Nichtschläfer (*)", "Verbesserte Regeneration (Astralenergie) I-III", "Zweistimmiger Gesang (*)"));
		vorteilNachteilPage.getWriters().add(fillWriter(NachteilWriter::new, NachteilReader::new, "Körpergebundene Kraft", "Sensibler Geruchssinn (*)", "Unfähig", "Unverträglichkeit gegenüber Alkohol", "Wahrer Name"));

		final DefaultPage kampfPage = new DefaultPage("Sonderfertigkeiten");
		kampfPage.getWriters().add(fillWriter(AllgemeinesonderfertigkeitWriter::new, AllgemeinesonderfertigkeitReader::new, "Fertigkeitsspezialisierung (Talente)", "Geländekunde", "Ortskenntnis"));

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

		setPages(Stream.of(vorteilNachteilPage, kampfPage, zauberPage1, zauberPage2, sonstigeZauberPage, itemPage, modPage, schipsPage, imagePage));
	}

}

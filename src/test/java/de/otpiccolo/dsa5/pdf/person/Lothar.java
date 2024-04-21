package de.otpiccolo.dsa5.pdf.person;

import java.util.stream.Stream;

import de.otpiccolo.dsa5.data.liturgien.LiturgieReader;
import de.otpiccolo.dsa5.data.liturgien.LiturgieWriter;
import de.otpiccolo.dsa5.data.nachteile.NachteilReader;
import de.otpiccolo.dsa5.data.nachteile.NachteilWriter;
import de.otpiccolo.dsa5.data.predigt.PredigtReader;
import de.otpiccolo.dsa5.data.predigt.PredigtWriter;
import de.otpiccolo.dsa5.data.vision.VisionReader;
import de.otpiccolo.dsa5.data.vision.VisionWriter;
import de.otpiccolo.dsa5.data.vorteile.VorteilReader;
import de.otpiccolo.dsa5.data.vorteile.VorteilWriter;
import de.otpiccolo.dsa5.data.zeremonien.ZeremonieReader;
import de.otpiccolo.dsa5.data.zeremonien.ZeremonieWriter;
import de.otpiccolo.dsa5.pdf.data.image.ImageReader;
import de.otpiccolo.dsa5.pdf.data.image.ImageWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;
import de.otpiccolo.dsa5.pdf.page.IPage;
import de.otpiccolo.dsa5.pdf.page.predefined.SchicksalspunktePage;
import de.otpiccolo.dsa5.pdf.page.predefined.SegenPage;
import de.otpiccolo.dsa5.pdf.page.predefined.ZauberModPage;

/**
 * Information about Lothar.
 */
public class Lothar extends Person {

	/**
	 * Constructor.
	 */
	public Lothar() {
		final DefaultPage vorteilNachteilPage = new DefaultPage("Vorteile & Nachteile");
		vorteilNachteilPage.getWriters().add(fillWriter(VorteilWriter::new, VorteilReader::new, "Verbesserte Regeneration (Lebensenergie) I-III", "Vertrauenerweckend", "Zäher Hund"));
		vorteilNachteilPage.getWriters().add(fillWriter(NachteilWriter::new, NachteilReader::new, "Behäbig", "Fettleibig", "Verpflichtungen I-III"));

		final DefaultPage karmaPage = new DefaultPage("Karmale Sonderfertigkeiten");
		karmaPage.getWriters().add(fillWriter(PredigtWriter::new, PredigtReader::new, "Predigt des Gottvertrauens"));
		karmaPage.getWriters().add(fillWriter(VisionWriter::new, VisionReader::new, "Vision der Gottheit"));

		final DefaultPage liturgiePage = new DefaultPage("Liturgien");
		liturgiePage.getWriters().add(fillWriter(LiturgieWriter::new, LiturgieReader::new, "Helfende Hand", "Friedvolle Aura"));

		final DefaultPage zeremoniePage = new DefaultPage("Zeremonien");
		zeremoniePage.getWriters().add(fillWriter(ZeremonieWriter::new, ZeremonieReader::new, "Gänsegestalt", "Nahrungsreinigung", "Reisesegen", "Sättigung", "Segnung des Heims", "Speisung", "Weihe des Heims"));

		final IPage segenPage = new SegenPage();
		final IPage modPage = new ZauberModPage();
		final IPage schipsPage = new SchicksalspunktePage();

		final DefaultPage imagePage = new DefaultPage();
		imagePage.getWriters().add(new ImageWriter(new ImageReader().readData("D:\\RP\\Bilder\\Lothar.png")));

		setPages(Stream.of(vorteilNachteilPage, karmaPage, liturgiePage, zeremoniePage, segenPage, modPage, schipsPage, imagePage));
	}

}

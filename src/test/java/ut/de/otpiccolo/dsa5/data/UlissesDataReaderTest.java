package ut.de.otpiccolo.dsa5.data;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.otpiccolo.dsa5.data.hexenfluch.HexenfluchData;
import de.otpiccolo.dsa5.data.hexenfluch.HexenfluchReader;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitData;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.kampfstilsonderfertigkeiten.KampfstilsonderfertigkeitData;
import de.otpiccolo.dsa5.data.kampfstilsonderfertigkeiten.KampfstilsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.karmaletradition.KarmaleTraditionData;
import de.otpiccolo.dsa5.data.karmaletradition.KarmaleTraditionReader;
import de.otpiccolo.dsa5.data.liturgien.LiturgieData;
import de.otpiccolo.dsa5.data.liturgien.LiturgieReader;
import de.otpiccolo.dsa5.data.nachteile.NachteilData;
import de.otpiccolo.dsa5.data.nachteile.NachteilReader;
import de.otpiccolo.dsa5.data.predigt.PredigtData;
import de.otpiccolo.dsa5.data.predigt.PredigtReader;
import de.otpiccolo.dsa5.data.segen.SegenData;
import de.otpiccolo.dsa5.data.segen.SegenReader;
import de.otpiccolo.dsa5.data.vision.VisionData;
import de.otpiccolo.dsa5.data.vision.VisionReader;
import de.otpiccolo.dsa5.data.vorteile.VorteilData;
import de.otpiccolo.dsa5.data.vorteile.VorteilReader;
import de.otpiccolo.dsa5.data.zauber.ZauberData;
import de.otpiccolo.dsa5.data.zauber.ZauberReader;
import de.otpiccolo.dsa5.data.zaubererweiterung.ZaubererweiterungData;
import de.otpiccolo.dsa5.data.zaubererweiterung.ZaubererweiterungReader;
import de.otpiccolo.dsa5.data.zeremonien.ZeremonieData;
import de.otpiccolo.dsa5.data.zeremonien.ZeremonieReader;

/**
 * Test to make sure that all Reader correctly read data from Ulisses pages and
 * they didn't change how the information is posted on their pages.
 */
@SuppressWarnings("javadoc")
public class UlissesDataReaderTest {

	@Test
	public void testHexenfluch() {
		final var reader = new HexenfluchReader();
		final var data = reader.readData("Hagelschlag");

		final var expected = new HexenfluchData("Hagelschlag", "21 AsP", "Auf einer Fläche von bis zu (QS x 20.000) Rechtschritt geht ein schwerer Hagelsturm nieder. Je nach Region kann auch ein Schnee- oder Sandsturm die Fläche verheeren. Solche Stürme können Ernten zerstören, Dächer abdecken und bei ungeschützten Tieren und Personen 2W6 TP Schaden pro 5 Minuten verursachen.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testKampfsonderfertigkeit() {
		final var reader = new KampfsonderfertigkeitReader();
		final var data = reader.readData("Aufmerksamkeit");

		final var expected = new KampfsonderfertigkeitData("Aufmerksamkeit", "Bei einem Hinterhalt oder wenn es darum geht, ob er überrascht wird, erhält er eine Erleichterung von 2 auf Sinnesschärfe (Hinterhalt entdecken), um seine Gegner zu entdecken.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testKampfstilsonderfertigkeit_Style1() {
		final var reader = new KampfstilsonderfertigkeitReader();
		final var data = reader.readData("Ederion-Stil");

		final var expected = new KampfstilsonderfertigkeitData("Ederion-Stil", "In der ersten KR eines jeden Kampfes erhält der Held nach Wahl des Spielers entweder einen Bonus von +2 AT oder +2 FK oder +2 TP.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testKampfstilsonderfertigkeit_Style2() {
		final var reader = new KampfstilsonderfertigkeitReader();
		final var data = reader.readData("Dajin-Stil");

		final var expected = new KampfstilsonderfertigkeitData("Dajin-Stil", "Sollte der Dajin-Buskur auf seiner Position verharren (sprich: er hat keine Aktion oder freie Aktion in dieser KR für Bewegung aufgewendet) und greift er den Feind an, der ihn in dieser KR zuerst angegriffen hat, so erhält er gegen diesen einen Bonus von +1 AT und +1 TP.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testKarmaleTradition() {
		final var reader = new KarmaleTraditionReader();
		final var data = reader.readData("Praioskirche");

		final List<String> rules = new ArrayList<String>(3);
		rules.add("Wille des Götterfürsten: Bei der Liturgiemodifikation Erzwingen erhält der Geweihte eine Erleichterung von 2 (statt 1).");
		rules.add("Magieschutz: Wird Magie gegen den Praiosgeweihten gewirkt, so verbessert sich hiergegen seine Seelenkraft um 1.");
		rules.add("Ein Praiosgeweihter muss sich an den Moralkodex (Prinzipientreue) halten. Die Wahl des Nachteils ist Voraussetzung, wenn der Spieler einen Geweihten der Kirche spielen will.");
		final var expected = new KarmaleTraditionData("Praioskirche", "Klugheit", "Bekehren & Überzeugen, Einschüchtern, Etikette, Menschenkenntnis, Willenskraft, Orientierung, Götter & Kulte, Magiekunde, Rechtskunde, Sphärenkunde", rules);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testLiturgie() {
		final var reader = new LiturgieReader();
		final var data = reader.readData("Bärenhaut");

		final var expected = new LiturgieData("Bärenhaut", "4 Aktion(en)", "8 KaP (Kosten sind nicht modifizierbar)", "Der Geweihte erhält einen natürlichen RS von 2, der mit RS aus anderen Quellen kombiniert werden kann.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testNachteil() {
		final var reader = new NachteilReader();
		final var data = reader.readData("Arm I-III");

		final var expected = new NachteilData("Arm I-III", "Durch den Nachteil Arm erhält ein Held bei der Generierung 250 Silbertaler weniger pro Stufe. Wählt er also 3 Stufen Arm, beginnt er ohne Startkapital und besitzt nur ein Kleiderpaket der Kategorie arm.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testPredigt() {
		final var reader = new PredigtReader();
		final var data = reader.readData("Predigt der Zuversicht");

		final var expected = new PredigtData("Predigt der Zuversicht", "QS ausgewählte Zuhörer können 1 Stunde lang eine einzige Stufe Furcht ignorieren. Nach Ablauf der Stunde wirkt die Stufe wieder. Die Wirkung wurde lediglich unterbrochen. Während der Stunde können die Zuhörer aber neue Stufen Furcht erleiden. Kein Zuhörer kann innerhalb von 24 Stunden mehr als einmal von dieser Predigt profitieren. Die Heldin muss für die Predigt 1 Schip einsetzen und sie ist nur einmal alle 24 Stunden einsetzbar.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testSegen() {
		final var reader = new SegenReader();
		final var data = reader.readData("Glückssegen");

		final var expected = new SegenData("Glückssegen", "Der Gesegnete hat einmal während der Wirkungsdauer der Segnung ein Fünkchen Glück. Er kann nach dem Ablegen einer Fertigkeitsprobe 1 FP hinzuaddieren, um z. B. eine höhere Qualitätsstufe zu erreichen.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testVision() {
		final var reader = new VisionReader();
		final var data = reader.readData("Vision der Entrückung");

		final var expected = new VisionData("Vision der Entrückung", "Diese Vision kann der Held jederzeit empfangen, was 30 Minuten dauert. In dieser Zeit erleidet er den Status Handlungsunfähig. Der Held bekommt 2 Stufen Entrückung. Der Held muss für die Vision 1 Schip einsetzen und kann sie nur einmal alle 24 Stunden einsetzen.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testVorteil() {
		final var reader = new VorteilReader();
		final var data = reader.readData("Adel I-III");

		final var expected = new VorteilData("Adel I-III", "Der Held ist angesehen, genießt die Privilegien des Adels und kann vom Meister Erleichterungen zugesprochen bekommen, wenn er gegenüber Rangniedrigeren agiert.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testZauber() {
		final var reader = new ZauberReader();
		final var data = reader.readData("Atemnot");

		final var expected = new ZauberData("Atemnot", "1 Aktion(en)", "4 AsP", "Der Verzauberte leidet augenblicklich unter Atemnot. Er erhält eine Stufe des Zustands Betäubung.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testZaubererweiterung() {
		final var reader = new ZaubererweiterungReader();
		final var data = reader.readData("Balsam Salabunde#Zielkategorie Lebewesen");

		final var expected = new ZaubererweiterungData("Zielkategorie Lebewesen", "Balsam Salabunde", "Der Zauber umfasst auch die Zielkategorie Lebewesen.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testZeremonie() {
		final var reader = new ZeremonieReader();
		final var data = reader.readData("Frostschutz");

		final var expected = new ZeremonieData("Frostschutz", "5 Minuten", "4 KaP (Aktivierung der Zeremonie) + 2 KaP pro 30 Minuten", "Der Firungeweihte erleidet kaum noch negative Auswirkungen durch eine kalte Umgebung. Für ihn gilt die Kältestufe als um QS/2 gesenkt.");
		Assertions.assertEquals(expected, data);
	}

}

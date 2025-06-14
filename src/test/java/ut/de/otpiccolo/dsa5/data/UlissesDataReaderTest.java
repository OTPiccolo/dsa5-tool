package ut.de.otpiccolo.dsa5.data;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten.AllgemeinesonderfertigkeitData;
import de.otpiccolo.dsa5.data.allgemeinesonderfertigkeiten.AllgemeinesonderfertigkeitReader;
import de.otpiccolo.dsa5.data.elfenlied.ElfenliedData;
import de.otpiccolo.dsa5.data.elfenlied.ElfenliedReader;
import de.otpiccolo.dsa5.data.elixiere.ElixierData;
import de.otpiccolo.dsa5.data.elixiere.ElixierReader;
import de.otpiccolo.dsa5.data.erweiterterliturgiestil.ErweiterterLiturgiestilData;
import de.otpiccolo.dsa5.data.erweiterterliturgiestil.ErweiterterLiturgiestilReader;
import de.otpiccolo.dsa5.data.erweitertertalentstil.ErweiterterTalentstilData;
import de.otpiccolo.dsa5.data.erweitertertalentstil.ErweiterterTalentstilReader;
import de.otpiccolo.dsa5.data.gewandzauber.GewandzauberData;
import de.otpiccolo.dsa5.data.gewandzauber.GewandzauberReader;
import de.otpiccolo.dsa5.data.hexenfluch.HexenfluchData;
import de.otpiccolo.dsa5.data.hexenfluch.HexenfluchReader;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitData;
import de.otpiccolo.dsa5.data.kampfsonderfertigkeiten.KampfsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.kampfstilsonderfertigkeiten.KampfstilsonderfertigkeitData;
import de.otpiccolo.dsa5.data.kampfstilsonderfertigkeiten.KampfstilsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.karmalesonderfertigkeiten.KarmaleSonderfertigkeitData;
import de.otpiccolo.dsa5.data.karmalesonderfertigkeiten.KarmaleSonderfertigkeitReader;
import de.otpiccolo.dsa5.data.karmaletradition.KarmaleTraditionData;
import de.otpiccolo.dsa5.data.karmaletradition.KarmaleTraditionReader;
import de.otpiccolo.dsa5.data.liturgien.LiturgieData;
import de.otpiccolo.dsa5.data.liturgien.LiturgieReader;
import de.otpiccolo.dsa5.data.liturgiestil.LiturgiestilData;
import de.otpiccolo.dsa5.data.liturgiestil.LiturgiestilReader;
import de.otpiccolo.dsa5.data.magischesonderfertigkeiten.MagischeSonderfertigkeitData;
import de.otpiccolo.dsa5.data.magischesonderfertigkeiten.MagischeSonderfertigkeitReader;
import de.otpiccolo.dsa5.data.nachteile.NachteilData;
import de.otpiccolo.dsa5.data.nachteile.NachteilReader;
import de.otpiccolo.dsa5.data.predigt.PredigtData;
import de.otpiccolo.dsa5.data.predigt.PredigtReader;
import de.otpiccolo.dsa5.data.rituale.RitualData;
import de.otpiccolo.dsa5.data.rituale.RitualReader;
import de.otpiccolo.dsa5.data.segen.SegenData;
import de.otpiccolo.dsa5.data.segen.SegenReader;
import de.otpiccolo.dsa5.data.talentstilsonderfertigkeiten.TalentstilsonderfertigkeitData;
import de.otpiccolo.dsa5.data.talentstilsonderfertigkeiten.TalentstilsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.vision.VisionData;
import de.otpiccolo.dsa5.data.vision.VisionReader;
import de.otpiccolo.dsa5.data.vorteile.VorteilData;
import de.otpiccolo.dsa5.data.vorteile.VorteilReader;
import de.otpiccolo.dsa5.data.zauber.ZauberData;
import de.otpiccolo.dsa5.data.zauber.ZauberReader;
import de.otpiccolo.dsa5.data.zaubererweiterung.ZaubererweiterungData;
import de.otpiccolo.dsa5.data.zaubererweiterung.ZaubererweiterungReader;
import de.otpiccolo.dsa5.data.zauberstilsonderfertigkeiten.ZauberstilsonderfertigkeitData;
import de.otpiccolo.dsa5.data.zauberstilsonderfertigkeiten.ZauberstilsonderfertigkeitReader;
import de.otpiccolo.dsa5.data.zaubertanz.ZaubertanzData;
import de.otpiccolo.dsa5.data.zaubertanz.ZaubertanzReader;
import de.otpiccolo.dsa5.data.zaubertradition.ZauberTraditionData;
import de.otpiccolo.dsa5.data.zaubertradition.ZauberTraditionReader;
import de.otpiccolo.dsa5.data.zaubertrick.ZaubertrickData;
import de.otpiccolo.dsa5.data.zaubertrick.ZaubertrickReader;
import de.otpiccolo.dsa5.data.zeremonialgegenstand.ZeremonialgegenstandData;
import de.otpiccolo.dsa5.data.zeremonialgegenstand.ZeremonialgegenstandReader;
import de.otpiccolo.dsa5.data.zeremonien.ZeremonieData;
import de.otpiccolo.dsa5.data.zeremonien.ZeremonieReader;

/**
 * Test to make sure that all Reader correctly read data from Ulisses pages and
 * they didn't change how the information is posted on their pages.
 */
@SuppressWarnings("javadoc")
public class UlissesDataReaderTest {

	@Test
	public void testAllgemeineSonderfertigkeit() {
		final var reader = new AllgemeinesonderfertigkeitReader();
		final var data = reader.readData("Fischer");

		final var expected = new AllgemeinesonderfertigkeitData("Fischer", "Mit dieser Sonderfertigkeit kann der Held erfolgreicher fischen. Bei einer erfolgreichen Probe auf Fischen & Angeln um Nahrung zu erbeuten kann er 1 QS mehr Nahrung erjagen. Ob es in dem Gewässer genügend essbare Fische und Meeresfrüchte gibt, muss der Spielleiter vorher entscheiden.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testElfenlied() {
		final var reader = new ElfenliedReader();
		final var data = reader.readData("Erinnerungsmelodie");

		final var expected = new ElfenliedData("Erinnerungsmelodie", "1 AsP pro 5 Minuten", "Mit dem Lied ruft sich der Elf eine Erinnerung aus lang vergangener Zeit zurück. Für je ein Jahr, das das Ereignis zurückliegt, muss er 5 Minuten spielen. Zusätzlich spielt er so lange, wie die Situation dauert, an die er sich zurückentsinnen will.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testElexier1() {
		// From 'Elixier' page.
		final var reader = new ElixierReader();
		final var data = reader.readData("Kaltes Licht");

		final List<String> qs = new ArrayList<String>(6);
		qs.add("Das Kalte Licht besitzt den Lichtradius einer Kerze für 0,5 Stunden.");
		qs.add("Das Kalte Licht besitzt den Lichtradius einer Kerze für 1 Stunde.");
		qs.add("Das Kalte Licht besitzt den Lichtradius einer Kerze für 1,5 Stunden.");
		qs.add("Das Kalte Licht besitzt den Lichtradius einer Fackel für 2 Stunden.");
		qs.add("Das Kalte Licht besitzt den Lichtradius einer Fackel für 2,5 Stunden.");
		qs.add("Das Kalte Licht besitzt den Lichtradius einer Fackel für 3 Stunden.");
		final var expected = new ElixierData("Kaltes Licht", 22, qs);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testElexier2() {
		// From 'Alchemistische Rezepte -> Elixier' page.
		final var reader = new ElixierReader();
		final var data = reader.readData("Fingerfertigkeitselixier");

		final List<String> qs = new ArrayList<String>(6);
		qs.add("+1 FF für 20 Minuten");
		qs.add("+1 FF, +1 FK für 20 Minuten");
		qs.add("+2 FF, +1 FK für 20 Minuten");
		qs.add("+2 FF, +2 FK für 20 Minuten");
		qs.add("+3 FF, +2 FK für 20 Minuten");
		qs.add("+3 FF, +3 FK für 20 Minuten");
		final var expected = new ElixierData("Fingerfertigkeitselixier", 45, qs);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testErweiterterLiturgiestil() {
		final var reader = new ErweiterterLiturgiestilReader();
		final var data = reader.readData("Altes Zwergenwissen");

		final var expected = new ErweiterterLiturgiestilData("Altes Zwergenwissen", "Setzt der Geweihte bei einem wohlgefälligen Talent ein Mirakel ein, erhält er dafür zusätzlich eine Erleichterung von +1.", "Hüter der Tradition");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testErweiterterTalentstil() {
		final var reader = new ErweiterterTalentstilReader();
		final var data = reader.readData("Allgemeinwissen");

		final var expected = new ErweiterterTalentstilData("Allgemeinwissen", "Die Heldin kann bei einer Probe auf ein Wissenstalent, die maximal um 3 erschwert sein darf, entscheiden, ob sie die Probe ablegen möchte oder diese automatisch als mit QS 1 gelungen gilt.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testGewandzauber() {
		final var reader = new GewandzauberReader();
		final var data = reader.readData("Schmutzabweisend");

		final var expected = new GewandzauberData("Schmutzabweisend", 1, "keine", "Die Kleidung wird nie schmutzig, nimmt keine fremden Gerüche auf und muss nie gereinigt werden.");
		Assertions.assertEquals(expected, data);
	}

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
	public void testKarmaleSonderfertigkeit() {
		final var reader = new KarmaleSonderfertigkeitReader();
		final var data = reader.readData("Abgeschwächte Liturgie ");

		final var expected = new KarmaleSonderfertigkeitData("Abgeschwächte Liturgie ", "Der Held kann nach dem Ablegen einer Liturgieprobe QS zurückbehalten und muss nicht alle erzielten QS einsetzen. Hat er beispielsweise 3 QS erreicht, kann er sich auch dafür entscheiden, dass die Liturgie nur mit einer QS von 2 oder 1 wirken soll.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testKarmaleTradition() {
		final var reader = new KarmaleTraditionReader();
		final var data = reader.readData("Praioskirche");

		final List<String> rules = new ArrayList<String>(3);
		rules.add("Wille des Götterfürsten: Bei der Liturgiemodifikation Erzwingen erhält die Geweihte eine Erleichterung von +2 (statt +1).");
		rules.add("Magieschutz: Wird Magie gegen die Praiosgeweihte gewirkt, so verbessert sich ihre Seelenkraft hiergegen um 1.");
		rules.add("Eine Praiosgeweihte muss sich an den Moralkodex (Prinzipientreue) halten. Die Wahl des Nachteils ist Voraussetzung, wenn der Spieler eine Geweihte der Kirche spielen will.");
		final var expected = new KarmaleTraditionData("Praioskirche", "Klugheit", "Bekehren & Überzeugen, Einschüchtern, Etikette, Menschenkenntnis, Willenskraft, Orientierung, Götter & Kulte, Magiekunde, Rechtskunde, Sphärenkunde", rules);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testLiturgie() {
		final var reader = new LiturgieReader();
		final var data = reader.readData("Bärenhaut");

		final var expected = new LiturgieData("Bärenhaut", "2 Aktion(en)", "8 KaP (Kosten sind nicht modifizierbar)", "Der Geweihte erhält einen natürlichen RS von 2, der mit RS aus anderen Quellen kombiniert werden kann. Kältestufen werden für den Geweihten außerdem um 1 Stufe reduziert, bis zu einem Minimum von 0.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testLiturgiestil() {
		final var reader = new LiturgiestilReader();
		final var data = reader.readData("Anhänger des guten Kampfes");

		final var expected = new LiturgiestilData("Anhänger des guten Kampfes", "Gegen Dämonen und Ungeheuer verfügen Anhänger des guten Kampfes über +1 RS. Dieser RS ist mit anderem RS kombinierbar, wenn dieser auch gegen das Ungeheuer schützt");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testMagischeSonderfertigkeit() {
		final var reader = new MagischeSonderfertigkeitReader();
		final var data = reader.readData("Lieblingszauber");

		final var expected = new MagischeSonderfertigkeitData("Lieblingszauber", "Der Held kann einen einzigen Zauber zu seinem Lieblingszauber erklären. Dieser Zauber hat bei Gelingen +2 FP (bis zu einem Maximum von 18 FP). Diese SF kann ein Zauberer nur einmal für einen einzigen Zauber erwerben.");
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
	public void testRitual() {
		final var reader = new RitualReader();
		final var data = reader.readData("Eins mit der Natur");

		final var expected = new RitualData("Eins mit der Natur", "5 Minuten", "16 AsP", "Der Zauberer erhält während der Wirkungsdauer die SF Geländekunde für ein beliebiges Gelände. Besitzt er die SF bereits, so verdoppelt sich der Bonus, den er auf die Fertigkeiten erhält.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testTalentstilsonderfertigkeit_Style1() {
		final var reader = new TalentstilsonderfertigkeitReader();
		final var data = reader.readData("Weg der Künstlerin");

		final var expected = new TalentstilsonderfertigkeitData("Weg der Künstlerin", "Die Heldin kann ein Talent, das zur Anwendung ihrer Kunst entscheidend ist, mit Erwerb dieses Stils aussuchen und fortan damit Geld verdienen (siehe Aventurisches Kompendium Seite 11). Sie bekommt statt Kreuzern oder Heller für die Anwendung ihrer Kunst Silbertaler.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testTalentstilsonderfertigkeit_Style2() {
		final var reader = new TalentstilsonderfertigkeitReader();
		final var data = reader.readData("Weg d. Bardin");

		final var expected = new TalentstilsonderfertigkeitData("Weg d. Bardin", "Die Heldin erhält bei Musizieren und Singen eine Erleichterung von 1.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testSegen() {
		final var reader = new SegenReader();
		final var data = reader.readData("Glückssegen");

		final var expected = new SegenData("Glückssegen", "Der Gesegnete hat einmal während der Wirkungsdauer der Segnung ein Fünkchen Glück. Er kann nach dem Ablegen einer Fertigkeitsprobe 1 FP hinzuaddieren, um z. B. eine höhere Qualitätsstufe zu erreichen. Diese Segnung kann bspw. dabei helfen, eine Probe doch noch zu bestehen oder eine höhere QS zu erreichen.");
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
	public void testZauberstilSonderfertigkeit() {
		final var reader = new ZauberstilsonderfertigkeitReader();
		final var data = reader.readData("Bewahrer");

		final var expected = new ZauberstilsonderfertigkeitData("Bewahrer", "Ein Bewahrer sticht durch seine Heilzauberei heraus. Auf Zauber, die LeP regenerieren, erhält er eine Begabung. Sollte er schon eine Begabung in einem dieser Zauber haben, so kann er statt 1W20 sogar 2W20 noch einmal würfeln.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testZaubertanz() {
		final var reader = new ZaubertanzReader();
		final var data = reader.readData("Tanz der Beweglichkeit");

		final var expected = new ZaubertanzData("Tanz der Beweglichkeit", "kurz, aufrechterhaltend", "8 AsP", "Die Rahkisa erhält einen Bonus von QS/2 auf GS und Ausweichen. Die Wirkung hält 1 Stunde nach dem Ende des Tanzes an.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testZauberTradition() {
		final var reader = new ZauberTraditionReader();
		final var data = reader.readData("Nachtalben");

		final List<String> rules = new ArrayList<String>(3);
		rules.add("Ein Zauber der nachtalbischen Tradition erfordert Sicht auf das Ziel sowie eine magische Geste und ausgesprochene Formel, die auch unauffällig sein können.");
		rules.add("Nachtalbische Zauber können in Zauberbücher und Schriftrollen niedergeschrieben und aus ihnen ohne Lehrmeister erlernt werden.");
		rules.add("Nachtalben können prinzipiell elfische Zauberlieder erlernen und nutzen, da sie automatisch den Zweistimmigen Gesang beherrschen. Sie verfügen durch ihre bisherige Abgeschiedenheit aber über einen gänzlich eigenen Satz an Verzerrten Elfenliedern.");
		rules.add("Zauber des Merkmals Dämonisch verfügen über eine verdoppelte Wirkungsdauer und kosten 1 AsP weniger. Die AsP-Kosten können dadurch nicht unter 1 sinken.");
		rules.add("Die Leiteigenschaft der Tradition ist Mut.");
		final var expected = new ZauberTraditionData("Nachtalben", "Mut", rules);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testZaubertrick() {
		final var reader = new ZaubertrickReader();
		final var data = reader.readData("Blütenduft");

		final var expected = new ZaubertrickData("Blütenduft", "In einem Radius von 16 Schritt um den Magier verströmt die Umgebung einen angenehmen Geruch nach Blüten und Waldesgrün.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testZeremonialgegenstandssonderfertigkeit() {
		final var reader = new ZeremonialgegenstandReader();
		final var data = reader.readData("Angsteinflößendes Tier");

		final var expected = new ZeremonialgegenstandData("Angsteinflößendes Tier", "Levthankult", "Der Levthanpriester kann auf bis zu 16 Schritt ein Ziel benennen. Dieses erleidet 1 Stufe Furcht. Nur Kulturschaffende können davon betroffen sein. Der Einsatz dieser Fähigkeit kostet 1 Aktion und verursacht 1 Stufe Trance.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testZeremonie() {
		final var reader = new ZeremonieReader();
		final var data = reader.readData("Frostschutz");

		final var expected = new ZeremonieData("Frostschutz", "5 Minuten", "4 KaP (Aktivierung der Zeremonie) + 2 KaP pro 30 Minuten", "Der Geweihte erleidet kaum noch negative Auswirkungen durch eine kalte Umgebung. Für ihn gilt die Kältestufe als um QS/2 gesenkt.");
		Assertions.assertEquals(expected, data);
	}

}

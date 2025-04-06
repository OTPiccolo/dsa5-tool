package ut.de.otpiccolo.dsa5.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.otpiccolo.dsa5.data.weapon.WeaponData;
import de.otpiccolo.dsa5.data.weapon.WeaponReader;

/**
 * Test to make sure that all Weapon Reader correctly read data from Ulisses
 * pages and they didn't change how the information is posted on their pages.
 */
@SuppressWarnings("javadoc")
public class UlissesWeaponReaderTest {

	@Test
	public void testArmbrueste() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Armbrüste:Gastraphetes");

		final var expected = new WeaponData("Gastraphetes", "Armbrüste", "Auf Reichweite kurz verursacht die Gastraphetes +1 TP zusätzlich", "Bei bestätigten Patzern erleidet der Schütze zusätzlich 1W3 SP.", null);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testBoegen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Bögen:Kriegsbogen");

		final var expected = new WeaponData("Kriegsbogen", "Bögen", "Auf eine Distanz von bis zu 50 Schritt richtet der Kriegsbogen +2 TP an.", "Für jeden Punkt KK unter 16 sind die Proben auf FK um 1 erschwert.", null);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testDolche() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Dolche:Messer");

		final var expected = new WeaponData("Messer", "Dolche", "Ein Messer richtet gegen Seile und Netze +2 TP an.", "Das Manöver Kreuzblock ist gegen Kettenwaffen, Stangenwaffen und Zweihandhiebwaffen nicht durchführbar.", null);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testFechtwaffen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Fechtwaffen:Stockdegen");

		final var expected = new WeaponData("Stockdegen", "Fechtwaffen", "Um den Stockdegen als Waffe zu identifizieren, muss eine Probe auf Sinnesschärfe gelingen.", "Proben eines Gegners auf Verteidigung sind nicht wie üblich durch die Kampftechnik Fechtwaffen um 1 erschwert (siehe RegelwerkSeite 235).", null);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testFaecher() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Fächer:Kriegsfächer");

		final var expected = new WeaponData("Kriegsfächer", "Fächer", "Beim Einsatz des Manövers Angetäuschter Angriff, bekommt der Träger des Kriegsfächers +2 TP (statt nur +1 TP).", "Bei einem bestätigten Patzer erleidet der Träger der Neunschwänzigen 2 SP zusätzlich zu allen anderen Auswirkungen des Patzers.", "kleiner Schild, 15 Strukturpunkte, oder Parierwaffe (PA-Bonus +1 für die Hauptwaffe). Fächer ein- oder ausklappen dauert jeweils 1 Aktion. Der AT-Abzug gilt nur für den Angriff mit dem ausgeklappten Fächer als Schild, aber nicht, wenn man ihn und eine andere Hauptwaffe führt. Mit dem ausgeklappten Fächer kann man versuchen, ein Geschoss zu parieren, allerdings erleidet man bei der Parade zusätzlich zu den üblichen Modifikatoren (siehe Regelwerk Seite 244) noch eine Erschwernis von 1. Mit einem offenen Fächer können nur Angriffe der Kampftechniken Raufen, Dolche und Fechtwaffen pariert werden sowie Fernkampftechniken (siehe oben). Nur ein offener Fächer kann als kleiner Schild dienen. Mit geschlossenem Fächer können Waffen aller Kampftechniken außer Kettenwaffen, Zweihandhiebwaffen, Zweihandschwerter und Fernkampftechniken pariert werden. Nur ein geschlossener Fächer kann als Parierwaffe dienen.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testHiebwaffen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Hiebwaffen:Ingerimmshammer");

		final var expected = new WeaponData("Ingerimmshammer", "Hiebwaffen", "Bei Einsatz des Manövers Zertrümmern (siehe Aventurisches Kompendium Seite 166) werden 2W6 für die TP benutzt und das bessere Ergebnis der beiden Würfel zählt.", "Das Manöver Finte ist um 1 erschwert.", "geweiht (Ingerimm), Waffe ist nur von Ingerimmgeweihten erwerbbar");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testKettenwaffen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Kettenwaffen:Kettenstab");

		final var expected = new WeaponData("Kettenstab", "Kettenwaffen", "Das Manöver Entwaffnen ist um 4 erleichtert.", "Nach einem Patzer wird das Würfelergebnis des Bestätigungswurfes um 1 erhöht.", null);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testLanzen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Lanzen:Ferdoker Kriegslanze");

		final var expected = new WeaponData("Ferdoker Kriegslanze", "Lanzen", "Rüstungen mit RS 4 oder höher erhalten gegenüber einer Ferdoker Kriegslanze einen Malus von –2 RS.", "Aufgrund der kurzen Führung der Lanze kann der Angegriffene nach einer erfolgreichen Verteidigung die Ferdoker Kriegslanze einen Passierschlag gegen den Reiter oder das Reittier ausführen.", "Diese Waffe kann nur bei einem Lanzenangriff eingesetzt werden.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testPeitschen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Peitschen:Neunschwänzige");

		final var expected = new WeaponData("Neunschwänzige", "Peitschen", "Verteidigungen gegen die Neunschwänzige sind um 1 erschwert.", "Bei einem bestätigten Patzer erleidet der Träger der Neunschwänzigen 1 SP zusätzlich zu allen anderen Auswirkungen des Patzers.", null);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testRaufen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Raufen:Veteranenhand");

		final var expected = new WeaponData("Veteranenhand", "Raufen", "Wird ein mit der Veteranenhand ausgeführter Angriff von einem Bewaffneten pariert, erhält der Träger der Veteranenhand nicht wie beim Raufen üblich den halben Waffenschaden. Bei Angriffen mit der Veteranenhand muss man die Paraden eines Bewaffneten also nicht mehr fürchten - sehr wohl aber immer noch ihre Angriffe.", "Nach einem Patzer wird das Würfelergebnis des Bestätigungswurfes um 2 erhöht.", "Um die Klingen aus der Veteranenhand fahren zu lassen, benötigt man 1 freie Aktion, um sie wieder einfahren zu lassen 2 Aktionen. An der Hand, an der die Veteranenhand übergezogen ist, kann keine weitere Waffe genutzt werden.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testSchilde() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Schilde:Lederschild");

		final var expected = new WeaponData("Lederschild", "Schilde", "Bei Einsatz des Manövers Verteidigungshaltung steigt die PA um 1 weiteren Punkt.", "Das Manöver Schildstoß richtet mit einem Lederschild nur 1 TP an.", "kleiner Schild, 15 Strukturpunkte");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testSchleudern() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Schleudern:Hylaische Schleuder");

		final var expected = new WeaponData("Hylaische Schleuder", "Schleudern", "Auf Reichweite kurz verursacht die Hylaische Schleuder +1 TP zusätzlich.", "Bei einem bestätigten Patzer erleidet der Nutzer der Hylaischen Schleuder zusätzlich zu allen anderen Nachteilen 1W3+1 SP.", null);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testSchwerter() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Schwerter:Säbel");

		final var expected = new WeaponData("Säbel", "Schwerter", "Von einem Reittier aus geführt, bekommt der Träger des Säbels einen Bonus von +1 TP.", "Gegen Rüstungen mit RS 4 oder höher richtet die Waffe –1 TP an.", null);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testSpiesswaffen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Spießwaffen:Pike (2H)");

		final var expected = new WeaponData("Pike (2H)", "Spießwaffen", "Um einen Pikenträger in einer Formation anzugreifen, muss ein Gegner im Nahkampf eine Erschwernis von 8 auf seine AT hinnehmen.", "Sollte dem Gegner eine AT gegen den Pikenträger gelingen, hat dieser eine Erschwernis von 4 auf seine Verteidigung.", "Diese Waffe kann nur in einem Formationskampf (siehe Seite 124) eingesetzt werden.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testStangenwaffen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Stangenwaffen:Dreizack (2H)");

		final var expected = new WeaponData("Dreizack (2H)", "Stangenwaffen", "Die Abzüge für den Kampf im Wasser sinken um 2. Das Manöver Entwaffnen ist um 1 erleichtert.", "Gegen Rüstungen mit RS 2 und mehr richtet der Dreizack –1 TP an.", null);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testWurfwaffen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Wurfwaffen:Magierkugel");

		final var expected = new WeaponData("Magierkugel", "Wurfwaffen", "Die Probe auf FK in der Reichweite kurz ist um +1 erleichtert.", "Das Manöver Präziser Schuss/Wurf ist um –1 erschwert und richtet 1 TP weniger an.", "Um eine Magierkugel bei der Heldenerschaffung zu erwerben, muss der Held der Tradition Gildenmagier angehören.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testZweihandhiebwaffen() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Zweihandhiebwaffen:Barbarenstreitaxt (2H)");

		final var expected = new WeaponData("Barbarenstreitaxt (2H)", "Zweihandhiebwaffen", "Erzielt der Träger einer Barbarenstreitaxt einen Kritischen Erfolg oder richtet gegen einen Feind 14+ SP an, so müssen alle Gegner in Angriffsdistanz eine Probe auf Willenskraft (Einschüchtern widerstehen) erschwert um 1 ablegen. Bei Misslingen erhält ein Gegner 1 Stufe Furcht für 5 KR.", "Nach einem bestätigten Patzer bei einer Attacke oder Parade erhält der Träger zusätzlich 1 Stufe Betäubung.", null);
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testZweihandschwerter() {
		final var reader = new WeaponReader();
		final var data = reader.readData("Zweihandschwerter:Doppelkhunchomer (2H)");

		final var expected = new WeaponData("Doppelkhunchomer (2H)", "Zweihandschwerter", "Gelingt dem Träger eines Doppelkhunchomers ein Kritischer Erfolg, ist der Bestätigungswurf um 2 Punkte erleichtert.", "Mit dem Doppelkhunchomer lassen sich keine Finten einsetzen.", null);
		Assertions.assertEquals(expected, data);
	}

}

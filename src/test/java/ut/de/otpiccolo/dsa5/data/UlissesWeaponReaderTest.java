package ut.de.otpiccolo.dsa5.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.otpiccolo.dsa5.data.weapon.dolche.DolchData;
import de.otpiccolo.dsa5.data.weapon.dolche.DolchReader;
import de.otpiccolo.dsa5.data.weapon.schilde.SchildData;
import de.otpiccolo.dsa5.data.weapon.schilde.SchildReader;
import de.otpiccolo.dsa5.data.weapon.schwerter.SchwertData;
import de.otpiccolo.dsa5.data.weapon.schwerter.SchwertReader;

/**
 * Test to make sure that all Weapon Reader correctly read data from Ulisses
 * pages and they didn't change how the information is posted on their pages.
 */
@SuppressWarnings("javadoc")
public class UlissesWeaponReaderTest {

	@Test
	public void testDolche() {
		final var reader = new DolchReader();
		final var data = reader.readData("Messer");

		final var expected = new DolchData("Messer", "Ein Messer richtet gegen Seile und Netze +2 TP an.", "Das Manöver Kreuzblock ist gegen Kettenwaffen, Stangenwaffen und Zweihandhiebwaffen nicht durchführbar.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testSchilde() {
		final var reader = new SchildReader();
		final var data = reader.readData("Lederschild");

		final var expected = new SchildData("Lederschild", "Bei Einsatz des Manövers Verteidigungshaltung steigt die PA um 1 weiteren Punkt.", "Das Manöver Schildstoß richtet mit einem Lederschild nur 1 TP an.");
		Assertions.assertEquals(expected, data);
	}

	@Test
	public void testSchwerter() {
		final var reader = new SchwertReader();
		final var data = reader.readData("Säbel");

		final var expected = new SchwertData("Säbel", "Von einem Reittier aus geführt, bekommt der Träger des Säbels einen Bonus von +1 TP.", "Gegen Rüstungen mit RS 4 oder höher richtet die Waffe –1 TP an.");
		Assertions.assertEquals(expected, data);
	}

}

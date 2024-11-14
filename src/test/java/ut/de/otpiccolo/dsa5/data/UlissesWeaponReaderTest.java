package ut.de.otpiccolo.dsa5.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.otpiccolo.dsa5.data.weapon.schwerter.SchwertData;
import de.otpiccolo.dsa5.data.weapon.schwerter.SchwertReader;

/**
 * Test to make sure that all Weapon Reader correctly read data from Ulisses
 * pages and they didn't change how the information is posted on their pages.
 */
@SuppressWarnings("javadoc")
public class UlissesWeaponReaderTest {

	@Test
	public void testSchwerter() {
		final var reader = new SchwertReader();
		final var data = reader.readData("Säbel");

		final var expected = new SchwertData("Säbel", "Von einem Reittier aus geführt, bekommt der Träger des Säbels einen Bonus von +1 TP.", "Gegen Rüstungen mit RS 4 oder höher richtet die Waffe –1 TP an.");
		Assertions.assertEquals(expected, data);
	}

}

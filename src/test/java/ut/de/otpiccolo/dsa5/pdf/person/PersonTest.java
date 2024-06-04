package ut.de.otpiccolo.dsa5.pdf.person;

import java.awt.Desktop;
import java.io.File;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.otpiccolo.dsa5.pdf.person.Irinja;
import de.otpiccolo.dsa5.pdf.person.Lothar;
import de.otpiccolo.dsa5.pdf.person.Thyra;

@SuppressWarnings("javadoc")
public class PersonTest {

	@Test
	@Disabled
	public void writeIrinja() throws Exception {
		final String source = "D:\\Schieb\\Irinja.pdf";
		final String destination = "C:\\Users\\OT Piccolo\\Desktop\\temp\\test.pdf";

		final Irinja irinja = new Irinja();
		irinja.setSource(source);
		irinja.setDestination(destination);
		irinja.writeDocument();

		Desktop.getDesktop().open(new File(destination));
	}

	@Test
	@Disabled
	public void writeLothar() throws Exception {
		final String source = "D:\\Schieb\\Lothar.pdf";
		final String destination = "C:\\Users\\OT Piccolo\\Desktop\\temp\\test.pdf";

		final Lothar lothar = new Lothar();
		lothar.setSource(source);
		lothar.setDestination(destination);
		lothar.writeDocument();

		Desktop.getDesktop().open(new File(destination));
	}

	@Test
	@Disabled
	public void writeThyra() throws Exception {
		final String source = "D:\\Schieb\\Thyra Kjaskardottir.pdf";
		final String destination = "C:\\Users\\OT Piccolo\\Desktop\\temp\\test.pdf";

		final Thyra thyra = new Thyra();
		thyra.setSource(source);
		thyra.setDestination(destination);
		thyra.writeDocument();

		Desktop.getDesktop().open(new File(destination));
	}

}

package ut.de.otpiccolo.dsa5.pdf.person;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.otpiccolo.dsa5.pdf.person.Adariel;
import de.otpiccolo.dsa5.pdf.person.Aurelius;
import de.otpiccolo.dsa5.pdf.person.Irinja;
import de.otpiccolo.dsa5.pdf.person.Lothar;
import de.otpiccolo.dsa5.pdf.person.Manjula;
import de.otpiccolo.dsa5.pdf.person.Person;
import de.otpiccolo.dsa5.pdf.person.Thyra;

@SuppressWarnings("javadoc")
public class PersonTest {

	@Test
	@Disabled
	public void writeAdariel() throws Exception {
		final String source = "D:\\Schieb\\Adariel Abendfreundin.pdf";
		final String destination = "C:\\Users\\OT Piccolo\\Desktop\\temp\\Adariel Abendfreundin.pdf";
		writePerson(new Adariel(), source, destination);
	}

	@Test
	@Disabled
	public void writeAurelius() throws Exception {
		final String source = "D:\\Schieb\\Aurelius.pdf";
		final String destination = "C:\\Users\\OT Piccolo\\Desktop\\temp\\Aurelius.pdf";
		writePerson(new Aurelius(), source, destination);
	}

	@Test
	@Disabled
	public void writeIrinja() throws Exception {
		final String source = "D:\\Schieb\\Irinja.pdf";
		final String destination = "C:\\Users\\OT Piccolo\\Desktop\\temp\\Irinja.pdf";
		writePerson(new Irinja(), source, destination);
	}

	@Test
	@Disabled
	public void writeLothar() throws Exception {
		final String source = "D:\\Schieb\\Lothar.pdf";
		final String destination = "C:\\Users\\OT Piccolo\\Desktop\\temp\\Lothar.pdf";
		writePerson(new Lothar(), source, destination);
	}

	@Test
	@Disabled
	public void writeManjula() throws Exception {
		final String source = "D:\\Schieb\\Manjula.pdf";
		final String destination = "C:\\Users\\OT Piccolo\\Desktop\\temp\\Manjula.pdf";
		writePerson(new Manjula(), source, destination);
	}

	@Test
	@Disabled
	public void writeThyra() throws Exception {
		final String source = "D:\\Schieb\\Thyra Kjaskardottir.pdf";
		final String destination = "C:\\Users\\OT Piccolo\\Desktop\\temp\\Thyra Kjaskardottir.pdf";
		writePerson(new Thyra(), source, destination);
	}

	private void writePerson(final Person person, final String source, final String destination) throws IOException {
		person.setSource(new File(source));
		person.setDestination(new File(destination));
		person.writeDocument();
		Desktop.getDesktop().open(new File(destination));
	}

}

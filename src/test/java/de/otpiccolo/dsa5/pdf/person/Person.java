package de.otpiccolo.dsa5.pdf.person;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

import de.otpiccolo.dsa5.pdf.PdfWriter;
import de.otpiccolo.dsa5.pdf.data.ADataWriter;
import de.otpiccolo.dsa5.pdf.data.IDataReader;
import de.otpiccolo.dsa5.pdf.data.IDataWriter;

/**
 * Abstract class to write the details of a DSA person to a PDF.
 */
public class Person extends PdfWriter {

	/**
	 * Convenience method to create a data writer filled with information from
	 * its corresponding reader.
	 *
	 * @param <T>
	 *            The type of data writer used.
	 * @param <U>
	 *            The type of data reader used.
	 * @param <V>
	 *            The type of read data.
	 * @param writer
	 *            A function to create the writer with the read data.
	 * @param reader
	 *            A supplier for the reader.
	 * @param data
	 *            The data that needs to be read by the reader.
	 * @return The data writer, already filled with data.
	 */
	protected <T extends IDataWriter, U extends IDataReader<String, V>, V> T fillWriter(final Function<Collection<V>, T> writer, final Supplier<U> reader, final String... data) {
		return ADataWriter.fillWriter(writer, reader, data);
	}

}

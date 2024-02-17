package de.otpiccolo.dsa5.ulisses;

import de.otpiccolo.dsa5.pdf.data.IDataReader;

/**
 * Reader to read data from the DSA internet page Ulisses.
 *
 * @param <T>
 *            The type of data that will be read.
 */
public interface IUlissesReader<T> extends IDataReader<String, T> {

	@Override
	T readData(final String name);

}

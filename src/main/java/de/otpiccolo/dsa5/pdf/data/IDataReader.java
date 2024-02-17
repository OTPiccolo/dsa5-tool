package de.otpiccolo.dsa5.pdf.data;

/**
 * An interface to read arbitrary data from a source.
 *
 * @param <T>
 *            The type of query to retrieve data.
 * @param <R>
 *            The type of data that will be read.
 */
public interface IDataReader<T, R> {

	/**
	 * Read data from the source.
	 *
	 * @param query
	 *            The query to retrieve data.
	 * @return The returned data.
	 */
	R readData(T query);

}

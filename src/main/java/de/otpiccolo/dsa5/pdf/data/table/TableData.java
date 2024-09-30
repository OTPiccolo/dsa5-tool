package de.otpiccolo.dsa5.pdf.data.table;

import java.util.List;

import de.otpiccolo.dsa5.pdf.data.IDataWriter;

/**
 * A record containing table data.
 *
 * @param columnWeights
 *            The weight of each column. Each entry in the array represents a
 *            column, and the value is a ratio to each other column on how much
 *            space of the total table it should take. If all entries are the
 *            same, each column takes on the same space in the table. The values
 *            can add up to any arbitrary number. For example, an entry like
 *            [1,2,1] would make the middle column take up twice as much space
 *            as each other column (as much as both other columns combined).
 * @param cellData
 *            A nested list of each cells that should be drawn in the table.
 *            Each inner list is a complete row, containing for each column in
 *            order the data writer that will populate the cell.
 * @param cellIndent
 *            The indentation inside of cells on each side.
 *
 */
public record TableData(int[] columnWeights, List<List<IDataWriter>> cellData, float cellIndent) {

	/**
	 * A record containing table data. Using a default cell indentation of zero.
	 *
	 * @param columnWeights
	 *            The weight of each column. Each entry in the array represents
	 *            a column, and the value is a ratio to each other column on how
	 *            much space of the total table it should take. If all entries
	 *            are the same, each column takes on the same space in the
	 *            table. The values can add up to any arbitrary number. For
	 *            example, an entry like [1,2,1] would make the middle column
	 *            take up twice as much space as each other column (as much as
	 *            both other columns combined).
	 * @param cellData
	 *            A nested list of each cells that should be drawn in the table.
	 *            Each inner list is a complete row, containing for each column
	 *            in order the data writer that will populate the cell.
	 */
	public TableData(final int[] columnWeights, final List<List<IDataWriter>> cellData) {
		this(columnWeights, cellData, 0);
	}

	/**
	 * Gets the column aspect ratio, with each entry in the array being a number
	 * between 0.0 and 1.0, describing how many percent of the whole table each
	 * column gets.
	 *
	 * @return An array containing the column aspect ratio. The sum of its
	 *         content will be 1.0.
	 */
	public float[] getColumnRatio() {
		int combinedWeights = 0;
		for (final int weight : columnWeights) {
			combinedWeights += weight;
		}
		final float[] ratio = new float[columnWeights.length];
		for (int i = 0; i < columnWeights.length; i++) {
			ratio[i] = (float) columnWeights[i] / (float) combinedWeights;
		}
		return ratio;
	}

}

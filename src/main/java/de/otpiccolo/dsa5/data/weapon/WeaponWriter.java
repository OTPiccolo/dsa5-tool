package de.otpiccolo.dsa5.data.weapon;

import java.io.IOException;
import java.util.Collection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import de.otpiccolo.dsa5.pdf.data.ADataWriter;
import de.otpiccolo.pdf.PDUtil;

/**
 * Class to write weapons to a document.
 */
public class WeaponWriter extends ADataWriter {

	private final Collection<WeaponData> data;

	/**
	 * A collection containing weapons.
	 *
	 * @param data
	 *            The weapons.
	 */
	public WeaponWriter(final Collection<WeaponData> data) {
		this.data = data;
	}

	@Override
	public PDRectangle writeData(final PDDocument doc, final PDPage page, final PDRectangle availableSpace) throws IOException {
		PDRectangle space = PDUtil.copyRectangle(availableSpace);
		try (PDPageContentStream content = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true)) {
			for (final WeaponData weapon : data) {
				space = writeTitle(weapon.name(), content, space, 5f);
				space = writeParagraph("Vorteil: " + weapon.upside(), content, space, 5f);
				space = writeParagraph("Nachteil: " + weapon.downside(), content, space, 15f);
			}
			// Remove last weapon spacing.
			space.setUpperRightY(space.getUpperRightY() + 15f);
			drawRectangle(content, space, availableSpace);
		}
		return space;
	}

}

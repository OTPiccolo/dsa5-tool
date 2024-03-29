package de.otpiccolo.dsa5.pdf.data.paragraph;

/**
 * A record of Paragraph data.
 *
 * @param paragraph
 *            The paragraph to write.
 */
public record ParagraphData(String paragraph, String blub) {

	public ParagraphData(final String paragraph) {
		this(paragraph, "Yeah!");
	}

}

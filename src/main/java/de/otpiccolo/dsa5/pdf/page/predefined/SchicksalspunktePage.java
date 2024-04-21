package de.otpiccolo.dsa5.pdf.page.predefined;

import java.util.Arrays;
import java.util.List;

import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;

/**
 * Page about Schicksalspunkte and what to use them for.
 */
public class SchicksalspunktePage extends DefaultPage {

	/**
	 * Constructor.
	 */
	public SchicksalspunktePage() {
		super("Schicksalspunkte");
		final ParagraphData pg1 = new ParagraphData("Erster: Wer bereit ist, 1 Schip auszugeben, kann in einer Kampfrunde einmalig als Erster handeln, egal welche Initiative er hat. In der nächsten Kampfrunde ist der Held dann mit seiner gewöhnlichen Initiative an der Reihe. Setzen mehrere Personen diese Fähigkeit ein, handeln sie vor allen anderen, aber in der Reihenfolge ihrer Initiative. Dieser Einsatz von Schicksalspunkten muss zu Beginn einer Kampfrunde angekündigt werden.");
		final ParagraphData pg2 = new ParagraphData("Neuer Wurf: Wer einen, zwei oder alle Würfel bei einer Eigenschafts- oder Fertigkeitsprobe oder bei einer Probe auf Attacke, Verteidigung und Fernkampf wiederholen will, darf 1 Schip zum nochmaligen Würfeln investieren. Dabei ist es egal, ob der Held bei der erforderlichen Probe einen, zwei oder drei Würfel neu würfeln muss – es kostet ihn insgesamt nur 1 Schip. Bei einem Patzer ist dieser Einsatz nicht erlaubt. Bevor du diese Einsatzmöglichkeit wählst, musst du die Probe komplett abgelegt haben, denn nur so kannst du sehen, ob du beispielsweise einen Patzer gewürfelt hast. Das zweite Ergebnis ist bindend. Dieser Einsatz ist nur einmal pro Probe gültig.");
		final ParagraphData pg3 = new ParagraphData("Qualität verbessern: Für den Einsatz von 1 Schip kann der Held die Qualitätsstufe einer Fertigkeitsprobe um eine Stufe erhöhen. Der Held kann auch eine bessere QS erhalten als er eigentlich durch FP erzeugen kann. Die Probe muss gelungen sein, eine misslungene Probe kann über diesen Einsatz nicht zu einer gelungenen Probe umgewandelt werden. Dieser Einsatz ist nur einmal pro Fertigkeitsprobe gültig.");
		final ParagraphData pg4 = new ParagraphData("Schadenswurf wiederholen: Durch den Einsatz von 1 Schip kann 1W6 beim Auswürfeln der TP wiederholt werden. Ein Held kann nur seinen eigenen Würfel zur TP-Bestimmung wiederholen, nicht den des Gegners. Bei Waffen mit mehr als 1W6 kann nur 1W6 wiederholt nicht, nicht 2 oder mehr.");
		final ParagraphData pg5 = new ParagraphData("Verteidigung: Wer seine Verteidigung im Kampf stärken will, der gibt 1 Schip aus und erhält bis zum Ende der aktuellen Kampfrunde einen Bonus von 4 auf alle Verteidigungen. Dies kann zu einem beliebigen Zeitpunkt der Kampfrunde erfolgen (aber vor dem Würfeln der Verteidigung). Der Bonus erhöht auch die Anzahl der maximalen Verteidigungen eines Helden, da bei mehreren Verteidigungen sein Verteidigungswert erst später auf 0 sinkt.");
		final ParagraphData pg6 = new ParagraphData("Zustand ignorieren: Es kostet ebenfalls 1 Schip, alle Zustände, durch die man betroffen ist, für eine Kampfrunde zu ignorieren.");
		final List<ParagraphData> paragraphs = Arrays.asList(pg1, pg2, pg3, pg4, pg5, pg6);
		final ParagraphWriter writer = new ParagraphWriter(paragraphs);
		getWriters().add(writer);
	}

}

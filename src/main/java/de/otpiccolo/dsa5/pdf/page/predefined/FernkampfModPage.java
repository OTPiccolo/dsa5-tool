package de.otpiccolo.dsa5.pdf.page.predefined;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.otpiccolo.dsa5.pdf.data.IDataWriter;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphData;
import de.otpiccolo.dsa5.pdf.data.paragraph.ParagraphWriter;
import de.otpiccolo.dsa5.pdf.data.table.TableData;
import de.otpiccolo.dsa5.pdf.data.table.TableWriter;
import de.otpiccolo.dsa5.pdf.page.DefaultPage;

/**
 * Page about Fernkampf-Modifikationen.
 */
public class FernkampfModPage extends DefaultPage {

	/**
	 * Constructor.
	 */
	public FernkampfModPage() {
		super("Fernkampf-Modifikationen");
		addReichweite();
		addGroesse();
		addBewegung();
		addSicht();
		addPferd();
		addZielen();
		addKampfgetuemmel();
	}

	private void addReichweite() {
		final ParagraphData pg11 = new ParagraphData("Nah:");
		final ParagraphData pg21 = new ParagraphData("Mittel:");
		final ParagraphData pg31 = new ParagraphData("Fern:");
		final ParagraphData pg12 = new ParagraphData("+2 auf FK (+1 TP)");
		final ParagraphData pg22 = new ParagraphData("+/–0 auf FK");
		final ParagraphData pg32 = new ParagraphData("–2 auf FK (–1 TP)");

		final List<IDataWriter> header = Arrays.asList(new ParagraphWriter("Reichweite", Collections.emptySet()));
		final List<IDataWriter> row1 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg11)), new ParagraphWriter(Collections.singleton(pg12)));
		final List<IDataWriter> row2 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg21)), new ParagraphWriter(Collections.singleton(pg22)));
		final List<IDataWriter> row3 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg31)), new ParagraphWriter(Collections.singleton(pg32)));

		final List<List<IDataWriter>> cellData = Arrays.asList(header, row1, row2, row3);
		final TableData data = new TableData(cellData, new int[] { 1, 9 });
		final TableWriter table = new TableWriter(data);
		getWriters().add(table);
	}

	private void addGroesse() {
		final ParagraphData pg11 = new ParagraphData("Winzig:");
		final ParagraphData pg21 = new ParagraphData("Klein:");
		final ParagraphData pg31 = new ParagraphData("Mittel:");
		final ParagraphData pg41 = new ParagraphData("Groß:");
		final ParagraphData pg51 = new ParagraphData("Riesig:");
		final ParagraphData pg12 = new ParagraphData("–8 auf FK");
		final ParagraphData pg22 = new ParagraphData("–4 auf FK");
		final ParagraphData pg32 = new ParagraphData("+/–0 auf FK");
		final ParagraphData pg42 = new ParagraphData("+4 auf FK");
		final ParagraphData pg52 = new ParagraphData("+8 auf FK");
		final ParagraphData pg13 = new ParagraphData("Ratte, Kröte, Spatz");
		final ParagraphData pg23 = new ParagraphData("Rehkitz, Schaf, Ziege");
		final ParagraphData pg33 = new ParagraphData("Mensch, Zwerg, Esel");
		final ParagraphData pg43 = new ParagraphData("Oger, Troll, Rind");
		final ParagraphData pg53 = new ParagraphData("Drache, Riese, Elefant");

		final List<IDataWriter> header = Arrays.asList(new ParagraphWriter("Größe", Collections.emptySet()));
		final List<IDataWriter> row1 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg11)), new ParagraphWriter(Collections.singleton(pg12)), new ParagraphWriter(Collections.singleton(pg13)));
		final List<IDataWriter> row2 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg21)), new ParagraphWriter(Collections.singleton(pg22)), new ParagraphWriter(Collections.singleton(pg23)));
		final List<IDataWriter> row3 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg31)), new ParagraphWriter(Collections.singleton(pg32)), new ParagraphWriter(Collections.singleton(pg33)));
		final List<IDataWriter> row4 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg41)), new ParagraphWriter(Collections.singleton(pg42)), new ParagraphWriter(Collections.singleton(pg43)));
		final List<IDataWriter> row5 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg51)), new ParagraphWriter(Collections.singleton(pg52)), new ParagraphWriter(Collections.singleton(pg53)));

		final List<List<IDataWriter>> cellData = Arrays.asList(header, row1, row2, row3, row4, row5);
		final TableData data = new TableData(cellData, new int[] { 1, 2, 7 });
		final TableWriter table = new TableWriter(data);
		getWriters().add(table);
	}

	private void addBewegung() {
		final ParagraphData pg11 = new ParagraphData("Ziel steht still");
		final ParagraphData pg21 = new ParagraphData("Ziel bewegt sich leicht (4 Schritt und weniger in letzter Handlung)");
		final ParagraphData pg31 = new ParagraphData("Ziel bewegt sich leicht (5 Schritt oder mehr in letzter Handlung)");
		final ParagraphData pg41 = new ParagraphData("Ziel schlägt Haken");
		final ParagraphData pg51 = new ParagraphData("Schütze geht (4 Schritt und weniger in letzter Handlung)");
		final ParagraphData pg61 = new ParagraphData("Schütze rennt (5 Schritt oder mehr in letzter Handlung)");
		final ParagraphData pg12 = new ParagraphData("+2 auf FK");
		final ParagraphData pg22 = new ParagraphData("+/–0 auf FK");
		final ParagraphData pg32 = new ParagraphData("–2 auf FK");
		final ParagraphData pg42 = new ParagraphData("–4 auf FK, GS des Ziels halbiert");
		final ParagraphData pg52 = new ParagraphData("–2 auf FK");
		final ParagraphData pg62 = new ParagraphData("–4 auf FK");

		final List<IDataWriter> header = Arrays.asList(new ParagraphWriter("Bewegung", Collections.emptySet()));
		final List<IDataWriter> row1 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg11)), new ParagraphWriter(Collections.singleton(pg12)));
		final List<IDataWriter> row2 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg21)), new ParagraphWriter(Collections.singleton(pg22)));
		final List<IDataWriter> row3 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg31)), new ParagraphWriter(Collections.singleton(pg32)));
		final List<IDataWriter> row4 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg41)), new ParagraphWriter(Collections.singleton(pg42)));
		final List<IDataWriter> row5 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg51)), new ParagraphWriter(Collections.singleton(pg52)));
		final List<IDataWriter> row6 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg61)), new ParagraphWriter(Collections.singleton(pg62)));

		final List<List<IDataWriter>> cellData = Arrays.asList(header, row1, row2, row3, row4, row5, row6);
		final TableData data = new TableData(cellData, new int[] { 7, 3 });
		final TableWriter table = new TableWriter(data);
		getWriters().add(table);
	}

	private void addSicht() {
		final ParagraphData pg11 = new ParagraphData("Sicht klar und ungestört");
		final ParagraphData pg21 = new ParagraphData("Leichte Störung der Sicht");
		final ParagraphData pg31 = new ParagraphData("Ziel als Silhouette erkennbar");
		final ParagraphData pg41 = new ParagraphData("Ziel schemenhaft erkennbar");
		final ParagraphData pg51 = new ParagraphData("Ziel unsichtbar");
		final ParagraphData pg12 = new ParagraphData("");
		final ParagraphData pg22 = new ParagraphData("leichtes Blattwerk, Morgendunst");
		final ParagraphData pg32 = new ParagraphData("Nebel, Mondlicht");
		final ParagraphData pg42 = new ParagraphData("starker Nebel, Sternenlicht");
		final ParagraphData pg52 = new ParagraphData("dichter Rauch, völlige Dunkelheit");
		final ParagraphData pg13 = new ParagraphData("+/–0 auf FK");
		final ParagraphData pg23 = new ParagraphData("–2 auf FK");
		final ParagraphData pg33 = new ParagraphData("–4 auf FK");
		final ParagraphData pg43 = new ParagraphData("–6 auf FK");
		final ParagraphData pg53 = new ParagraphData("Glückstreffer (gewürfelte 1)");

		final List<IDataWriter> header = Arrays.asList(new ParagraphWriter("Sicht", Collections.emptySet()));
		final List<IDataWriter> row1 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg11)), new ParagraphWriter(Collections.singleton(pg12)), new ParagraphWriter(Collections.singleton(pg13)));
		final List<IDataWriter> row2 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg21)), new ParagraphWriter(Collections.singleton(pg22)), new ParagraphWriter(Collections.singleton(pg23)));
		final List<IDataWriter> row3 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg31)), new ParagraphWriter(Collections.singleton(pg32)), new ParagraphWriter(Collections.singleton(pg33)));
		final List<IDataWriter> row4 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg41)), new ParagraphWriter(Collections.singleton(pg42)), new ParagraphWriter(Collections.singleton(pg43)));
		final List<IDataWriter> row5 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg51)), new ParagraphWriter(Collections.singleton(pg52)), new ParagraphWriter(Collections.singleton(pg53)));

		final List<List<IDataWriter>> cellData = Arrays.asList(header, row1, row2, row3, row4, row5);
		final TableData data = new TableData(cellData, new int[] { 2, 3, 2 });
		final TableWriter table = new TableWriter(data);
		getWriters().add(table);
	}

	private void addPferd() {
		final ParagraphData pg11 = new ParagraphData("Tier steht");
		final ParagraphData pg21 = new ParagraphData("Tier im Schritt");
		final ParagraphData pg31 = new ParagraphData("Tier im Trab");
		final ParagraphData pg41 = new ParagraphData("Tier im Galopp");
		final ParagraphData pg12 = new ParagraphData("+/–0 auf FK");
		final ParagraphData pg22 = new ParagraphData("–4 auf FK");
		final ParagraphData pg32 = new ParagraphData("Glückstreffer (gewürfelte 1)");
		final ParagraphData pg42 = new ParagraphData("–8 auf FK");

		final List<IDataWriter> header = Arrays.asList(new ParagraphWriter("Pferd", Collections.emptySet()));
		final List<IDataWriter> row1 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg11)), new ParagraphWriter(Collections.singleton(pg12)));
		final List<IDataWriter> row2 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg21)), new ParagraphWriter(Collections.singleton(pg22)));
		final List<IDataWriter> row3 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg31)), new ParagraphWriter(Collections.singleton(pg32)));
		final List<IDataWriter> row4 = Arrays.asList(new ParagraphWriter(Collections.singleton(pg41)), new ParagraphWriter(Collections.singleton(pg42)));

		final List<List<IDataWriter>> cellData = Arrays.asList(header, row1, row2, row3, row4);
		final TableData data = new TableData(cellData, new int[] { 1, 4 });
		final TableWriter table = new TableWriter(data);
		getWriters().add(table);
	}

	private void addZielen() {
		final ParagraphData pg1 = new ParagraphData("Jede zum Zielen genutzte Aktion erleichtert den folgenden Schuss um 2. Dieser Bonus kann bis zu einem Maximum von 4 erhöht werden. Zielen ist eine länger dauernde Handlung bis zum eigentlichen Angriff.");
		getWriters().add(new ParagraphWriter("Zielen", Collections.singleton(pg1)));
	}

	private void addKampfgetuemmel() {
		final ParagraphData pg1 = new ParagraphData("Schüsse ins Kampfgetümmel stellen immer eine besondere Herausforderung dar. Wenn ein Fernkämpfer auf Gegner in direkten Kampfhandlungen schießt (sprich: das Ziel und andere Kämpfer sich zueinander in Angriffsdistanz befinden), dann erleidet der Schütze eine Erschwernis von 2 auf seine Probe auf Fernkampf. Misslingt die Probe, wird niemand getroffen.");
		getWriters().add(new ParagraphWriter("Kampfgetümmel", Collections.singleton(pg1)));
	}

}

package de.otpiccolo.dsa5.data.weapon;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Special subclass for all weapons read from Ulisses pages.
 *
 * @param <T>
 *            The type of data that will be read.
 */
public abstract class AWeaponReader<T> extends AUlissesReader<T> {

	private static Map<String, String> CATEGORY_PAGE;
	private static Map<String, Map<String, String>> WEAPON_PAGE;

	private final String categoryName;

	/**
	 * Constructor.
	 *
	 * @param categoryName
	 *            The name of the category the weapon belongs to. Example:
	 *            <code>Schwerter</code>
	 */
	protected AWeaponReader(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Get the relative page name that belongs to the given weapon name within
	 * the category this reader represents. The given page can then be forwarded
	 * to {@link #loadDocument(String)} to load the actual page for the weapon.
	 *
	 * @param weaponName
	 *            The name of the weapon to look up.
	 * @return The relative page of the given weapon name. May be
	 *         <code>null</code>, if no page exists for the given weapon name.
	 * @see #loadDocument(String)
	 */
	protected String getPage(final String weaponName) {
		if (CATEGORY_PAGE == null) {
			CATEGORY_PAGE = loadCategories();
			WEAPON_PAGE = new HashMap<>();
		}

		final Map<String, String> weaponPages = getWeaponPages();
		return weaponPages.get(weaponName);
	}

	/**
	 * Gets additional data on the weapon page, that is not part of the table.
	 *
	 * @param root
	 *            The root element to look for the data. Usually the HTML
	 *            {@link Document}.
	 * @param name
	 *            The name of the data to retrieve, like displayed on the page.
	 *            For example, to get the 'Waffenvorteil' entry, use
	 *            <code>getAdditionalData(root, "Waffenvorteil")</code>.
	 * @return The data. If the given name is not present,
	 *         <code>"&lt;Not Found&gt;"</code> is returned.
	 */
	protected String getAdditionalData(final Element root, final String name) {
		final String key = name + ":";
		// XPath looks for all div elements, that contain a class attribute that
		// contains 'ce_text' among other values, and then selects all child
		// elements that are either 'div' or 'p'.
		for (final Element element : root.selectXpath("//div[contains(@class,'ce_text')]/*[self::div or self::p]")) {
			final String text = element.text().strip();
			if (text.startsWith(key)) {
				return text.substring(key.length()).stripLeading();
			}
		}
		return "<Not Found>";
	}

	private Map<String, String> getWeaponPages() {
		return WEAPON_PAGE.computeIfAbsent(categoryName, this::loadWeaponCategory);
	}

	private Map<String, String> loadCategories() {
		getLog().info("Initializing Weapon Categories.");
		Document doc;
		try {
			doc = loadDocument("/RS_Waffen.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of weapon categories. Error message: " + e.getMessage(), e);
			return Collections.emptyMap();
		}

		final Map<String, String> map = new HashMap<>();
		for (final Element element : doc.selectXpath("//a[@class='ulSubMenu']")) {
			map.put(element.attr("title"), "/" + element.attr("href"));
		}
		return map;
	}

	private Map<String, String> loadWeaponCategory(final String category) {
		getLog().info("Initializing Weapon Category: {}", category);
		Document doc;
		try {
			doc = loadDocument(CATEGORY_PAGE.get(category));
		} catch (final IOException e) {
			getLog().error("Error reading overview page of weapon category \"" + category + "\". Error message: " + e.getMessage(), e);
			return Collections.emptyMap();
		}

		final Map<String, String> map = new HashMap<>();
		for (final Element element : doc.selectXpath("//a[@class='ulSubMenu']")) {
			map.put(element.attr("title"), "/" + element.attr("href"));
		}
		return map;
	}

}

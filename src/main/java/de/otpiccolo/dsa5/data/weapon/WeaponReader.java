package de.otpiccolo.dsa5.data.weapon;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Special subclass for all weapons read from Ulisses pages.
 */
public class WeaponReader extends AUlissesReader<WeaponData> {

	private static record Weapon(String name, String category) {

	}

	private static Map<String, String> CATEGORY_PAGE;
	private static Map<Weapon, String> WEAPON_PAGE;

	@Override
	public WeaponData readData(final String name) {
		final Weapon weapon = toWeaponType(name);
		if (weapon == null) {
			getLog().warn("Could not determine weapon type for given name: {}", name);
			return new WeaponData(name, null, null, null);
		}

		return readWeaponData(weapon);
	}

	private Weapon toWeaponType(final String name) {
		final int index = name.indexOf(':');
		if (index >= 0) {
			return new Weapon(name.substring(index + 1), name.substring(0, index));
		}
		return null;
	}

	private WeaponData readWeaponData(final Weapon weapon) {
		getLog().info("Getting data for weapon \"{}\".", weapon.name);
		String upside;
		String downside;

		final String page = getPage(weapon);
		if (page == null) {
			return new WeaponData(weapon.name, "Weapon not found: " + weapon.name + " (" + weapon.category + ")", null, null);
		}

		try {

			final Document doc = loadDocument(page);
			upside = getAdditionalData(doc, "Waffenvorteil");
			downside = getAdditionalData(doc, "Waffennachteil");
		} catch (final IOException e) {
			getLog().error("Error reading weapon \"" + weapon.name + "\". Error message: " + e.getMessage(), e);
			upside = e.getMessage();
			downside = null;
		}
		return new WeaponData(weapon.name, weapon.category, upside, downside);
	}

	private String getAdditionalData(final Element root, final String name) {
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

	private String getPage(final Weapon weapon) {
		if (CATEGORY_PAGE == null) {
			CATEGORY_PAGE = new HashMap<>();
			WEAPON_PAGE = new HashMap<>();
			loadCategories();
		}

		return getWeaponPage(weapon);
	}

	private String getWeaponPage(final Weapon weapon) {
		final String weaponPage = WEAPON_PAGE.get(weapon);
		if (weaponPage != null) {
			return weaponPage;
		}

		loadWeaponCategory(weapon.category);
		return WEAPON_PAGE.get(weapon);
	}

	private void loadCategories() {
		getLog().info("Initializing Weapon Categories.");
		Document doc;
		try {
			doc = loadDocument("/RS_Waffen.html");
		} catch (final IOException e) {
			getLog().error("Error reading overview page of weapon categories. Error message: " + e.getMessage(), e);
			return;
		}

		for (final Element element : doc.selectXpath("//a[@class='ulSubMenu']")) {
			CATEGORY_PAGE.put(element.attr("title"), "/" + element.attr("href"));
		}
	}

	private void loadWeaponCategory(final String category) {
		getLog().info("Initializing Weapon Category: {}", category);
		Document doc;
		try {
			doc = loadDocument(CATEGORY_PAGE.get(category));
		} catch (final IOException e) {
			getLog().error("Error reading overview page of weapon category \"" + category + "\". Error message: " + e.getMessage(), e);
			return;
		}

		for (final Element element : doc.selectXpath("//a[@class='ulSubMenu']")) {
			WEAPON_PAGE.put(new Weapon(element.attr("title"), category), "/" + element.attr("href"));
		}
	}

}

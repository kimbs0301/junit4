package com.example.hamcrest;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.describedAs;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.core.IsSame;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author gimbyeongsu
 *
 */
public class CoreMatchersTest {

	@Test
	public void test_hamcrest_core_allof() {
		String microBrew = "Lake Louie Brewery Company";

		assertThat(microBrew, allOf(startsWith("Lake"), containsString("Brew")));
	}

	@Test
	public void test_hamcrest_core_anyOf() {
		String microBrew = "Grumpy Troll Brewery";

		assertThat(microBrew,
				anyOf(startsWith("brew"), containsString("Troll")));
	}

	@Test
	public void test_hamcrest_core_combinableMatcher() {
		String isLite = "Miller Lite";

		assertThat(isLite,
				both(containsString("Miller")).and(containsString("Lite")));
	}

	@Test
	public void test_hamcrest_core_describedAs() {
		BigDecimal myBigDecimal = new BigDecimal("0");

		assertThat(
				myBigDecimal,
				describedAs("a big decimal equal to %0", equalTo(myBigDecimal),
						myBigDecimal.toPlainString()));
	}

	@Test
	public void test_hamcrest_core_every() {
		List<Integer> ages = Lists.newArrayList(21, 25, 30, 18);
		assertThat(ages, everyItem(greaterThanOrEqualTo(18)));
	}

	@Test
	public void test_hamcrest_core_is() {
		String isLite = "Miller Brewing Company";

		assertEquals("Miller Brewing Company", isLite);
	}

	@Test
	public void test_hamcrest_core_isA() {
		Map<Integer, String> map = new HashMap<Integer, String>();

		assertThat(map, isA(Map.class));
	}

	@Test
	public void test_hamcrest_core_anything() {
		assertThat("", anything());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test_hamcrest_core_hasItems_matchers() {
		List<String> regionalBreweries = Lists.newArrayList("Capital Brewery",
				"City Brewing Company ", "Jacob Leinenkugel Brewing Company",
				"Lakefront Brewery", "New Glarus Brewing Company",
				"Stevens Point Brewery");

		assertThat(regionalBreweries,
				hasItems(containsString("Brew"), endsWith("y")));
	}

	@Test
	public void test_hamcrest_core_hasItems() {
		List<String> regionalBreweries = Lists.newArrayList("Capital Brewery",
				"City Brewing Company ", "Jacob Leinenkugel Brewing Company",
				"Lakefront Brewery, Inc.", "New Glarus Brewing Company",
				"Stevens Point Brewery");

		assertThat(
				regionalBreweries,
				hasItems("Capital Brewery", "City Brewing Company ",
						"Jacob Leinenkugel Brewing Company",
						"Lakefront Brewery, Inc.",
						"New Glarus Brewing Company", "Stevens Point Brewery"));
	}

	@Test
	public void test_hamcrest_core_isEqual() {
		String spottedCreator = "New Glarus Brewing Company";

		assertThat(spottedCreator, equalTo("New Glarus Brewing Company"));
	}

	@Test
	public void test_hamcrest_core_isInstanceOf() {
		Calendar cal = Calendar.getInstance();

		assertThat(cal, instanceOf(Calendar.class));
	}

	@Test
	public void test_hamcrest_core_is_notNullValue() {
		Set<String> daNull = new HashSet<String>();

		assertThat(daNull, notNullValue());
	}

	@Test
	public void test_hamcrest_core_is_nullValue() {
		Set<String> daNull = null;

		assertThat(daNull, nullValue());
	}

	@Test
	public void test_hamcrest_core_isSame_string() {
		String wiBrewery = "Capital Brewery";
		String wiRegionalBrewery = "Capital Brewery";

		assertThat(wiRegionalBrewery, IsSame.<String> sameInstance(wiBrewery));
	}

	@Test
	public void test_hamcrest_core_is_same_list() {
		List<String> someList = new ArrayList<String>();

		assertThat(someList, IsSame.<List<String>> sameInstance(someList));
	}

	@Test
	public void test_hamcrest_core_containsString() {
		String brewery = "Pabst Brewing Company";

		assertThat(brewery, containsString("Brew"));
	}

	@Test
	public void test_hamcrest_core_string_startsWith() {
		String highSchool = "Tarpon spring spongers";

		assertThat(highSchool, startsWith("Tarpon"));
	}

	@Test
	public void test_hamcrest_core_string_endsWith() {
		String baseBallTeam = "Milwaukee brewers";

		assertThat(baseBallTeam, endsWith("brewers"));
	}
}

package com.example.hamcrest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.hamcrest.text.IsEqualIgnoringWhiteSpace.equalToIgnoringWhiteSpace;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author gimbyeongsu
 *
 */
public class TextMatchersTest {

	@Test
	public void test_string_is_empty() {
		String favoriteCereal = "";
		assertThat(favoriteCereal, isEmptyOrNullString());
	}
	
	@Test
	public void test_string_is_empty_or_null() {
		String favoriteCereal = null;
		assertThat(favoriteCereal, isEmptyOrNullString());
	}

	@Test
	public void test_string_equal_to() {
		String favoriteCereal = "cinnamon life";
		assertThat(favoriteCereal, equalTo("cinnamon life"));
	}

	@Test
	public void test_string_equal_to_ignoring_case() {
		String favoriteCereal = "CINNAMON LIFE";
		assertThat(favoriteCereal, equalToIgnoringCase("cinnamon life"));
	}
	
	@Test
	public void test_string_equal_to_ignoring_whitespace() {
		String favoriteCereal = "CINNAMON LIFE          ";
		assertThat(favoriteCereal, equalToIgnoringWhiteSpace("cinnamon life"));
	}
	
	@Test
	public void test_string_contains() {
		String cereal = "mini wheats";
		assertThat(cereal, containsString("mini"));
	}
	
	@Test
	public void test_string_ends_with() {
		String cereal = "corn flakes";
		assertThat(cereal, endsWith("s"));
	}
	
	@Test
	public void test_string_starts_with() {
		String cereal = "honey smacks";
		assertThat(cereal, startsWith("honey"));
	}

	@Test
	public void test_string_has_order() {
		String cereal = "apple jacks";
		assertThat(cereal, stringContainsInOrder(Lists.newArrayList("apple", "jacks")));
	}
}

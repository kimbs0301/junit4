package com.example.junit;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author gimbyeongsu
 *
 */
public class MatchersTest {

	@Test
	public void test_string_contains_two_elements() {
		assertThat("B-double E double R U-N beer run",
				both(containsString("beer")).and(containsString("run")));
		assertThat("B-double E double R U-N beer run",
				both(containsString("beer")).and(containsString("run")).and(containsString("U-N")));
	}

	@Test
	public void test_string_contains() {
		assertThat("Meet the press", containsString("press"));
	}

	@Test
	public void test_string_either_or() {
		assertThat("the simpsons",
				either(containsString("the")).or(containsString("simpsons")));
	}

	@Test
	public void test_every_item_contains() {
		List<String> movies = Lists.newArrayList("the matrix",
				"the lord of the rings", "the lion king", "the dark knight");
		assertThat(movies, everyItem(containsString("the")));
	}

	@Test
	public void test_list_has_items() {
		List<String> websites = Lists.newArrayList("leveluplunch.com",
				"www.leveluplunch.com");

		assertThat(websites, hasItems("leveluplunch.com"));
	}
}

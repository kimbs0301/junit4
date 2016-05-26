package com.example.junit;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import junit.extensions.PA;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Person;
import com.example.util.TestUtils;

/**
 * @author gimbyeongsu
 * 
 */
public class PrivateTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PrivateTest.class);
	
	// http://egloos.zum.com/benelog/v/2685835
	// https://code.google.com/archive/p/privilegedaccessor/

	@Test
	public void test_private() throws Exception {
		final String nameBeforeChanging = "Ryu";
		final String nameAfterChanging = "Choo";

		// 보이지 않는 생성자를 호출할 수 있다.
		Person person = (Person) PA.instantiate(Person.class, nameBeforeChanging);

		assertThat(person.getName(), CoreMatchers.is(nameBeforeChanging));
		assertThat(person.getName(), CoreMatchers.is(not(nameAfterChanging)));

		// private 메소드 호출
		// person.changeName(nameAfterChanging);
		LOGGER.debug("{}", PA.getMethodSignatures(person));
		// PA.invokeMethod(person, "changeName(java.lang.String)", nameAfterChanging);
		PA.invokeMethod(person,
				TestUtils.getMethodSignature("changeName", PA.getMethodSignatures(person)),
				nameAfterChanging);

		assertThat(person.getName(), CoreMatchers.is(not(nameBeforeChanging)));
		assertThat(person.getName(), CoreMatchers.is(nameAfterChanging));
	}
}

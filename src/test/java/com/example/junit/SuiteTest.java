package com.example.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author gimbyeongsu
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ExceptionTest.class, IgnoreTest.class, SimpleTest.class })
public class SuiteTest {

}

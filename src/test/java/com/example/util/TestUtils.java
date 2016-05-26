package com.example.util;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @author gimbyeongsu
 * 
 */
public class TestUtils {

	public static String getMethodSignature(String methodName, Collection<String> methodSignatures) {
		Set<String> exclued = Sets.newHashSet("finalize()", "wait(long, int)", "wait(long)",
				"wait()", "equals(java.lang.Object)", "toString()", "hashCode()", "getClass()",
				"clone()", "registerNatives()", "notify()", "notifyAll()");
		for (String methodSignature : methodSignatures) {
			if (exclued.contains(methodSignature)) {
				continue;
			}
			if (methodSignature.startsWith(methodName)) {
				return methodSignature;
			}
		}
		return null;
	}
}

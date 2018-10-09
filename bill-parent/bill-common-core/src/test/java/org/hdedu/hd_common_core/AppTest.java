package org.hdedu.hd_common_core;

import java.util.List;
import java.util.Map;

import cn.yu2.baomihua.manage.enums.AppEnum;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {


	public static void main( String[] args ) {
		Map<Integer, String> map = AppEnum.getEnumMap(AppEnum.StudyTask.class);
		List list = AppEnum.getList(AppEnum.StudyTask.class);
		System.out.println(list.get(0));
	}


	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest( String testName ) {
		super(testName);
	}


	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}


	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_v04;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Chorinca-Notebook
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({demo_v04.FunctionTest.class, demo_v04.Demo_v04Test.class})
public class Demo_v04Suite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}

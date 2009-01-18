/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.minmax.fel.ibu.client.library;

import cz.minmax.fel.ibu.client.model.StringMatrix;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan Suchan
 */
public class MatrixOperatorTest
{
  public MatrixOperatorTest()
  {
  }

  @BeforeClass
  public static void setUpClass() throws Exception
  {
  }

  @AfterClass
  public static void tearDownClass() throws Exception
  {
  }

  @Before
  public void setUp()
  {
  }

  @After
  public void tearDown()
  {
  }

  /**
   * Test of getPossibleRectangleSizes method, of class MatrixOperator.
   */
  @Test
  public void getPossibleRectangleSizes()
  {
    System.out.println("getPossibleRectangleSizes");
    int length = 50;
    //int[] expResult = null;
    int[] result = MatrixOperator.getPossibleRectangleSizes(length);
    for (int i = 0; i < result.length; i++)
    {
      System.out.println(result[i]);
    }
    //assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    //fail("The test case is a prototype.");
  }
}

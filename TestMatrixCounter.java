import junit.framework.TestCase;
import org.junit.*;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestMatrixCounter extends TestCase {
  
  private MatrixCounter mc;
  
  @Before
  public void setUp(){
    //default setting is with the following matrix and distance threshold of 1
    mc = new MatrixCounter(new int[][]{{0, 0, 0, 0, 0},
                                       {0, 0, 1, 0, 0},
                                       {0, 0, 1, 0, 0}},1);
  }
  public void testBase() {
    assertTrue(mc.numAffected()==7);
  }
  
  public void testSetDist() {
    mc.setDist(2);
    assertTrue(mc.numAffected()==11);
  }
  
  public void testSetMatrix() {
    mc.setMatrix(new int[][]{{0, 0, 0},
                             {0, 1, 0},
                             {0, 0, 0}});
    assertTrue(mc.numAffected()==5);
  }
  
  public void testOverbounds() {
    mc.setDist(10);
    assertTrue(mc.numAffected()==11);
  }
  
  public void testEdge(){
    mc.setMatrix(new int[][]{{0, 1, 0},
                             {1, 0, 0},
                             {0, 0, 0}});
    assertTrue(mc.numAffected()==6);
  }
  
  public void testCorner(){
    mc.setDist(2);
    mc.setMatrix(new int[][]{{1, 0, 0},
                             {0, 0, 0},
                             {0, 0, 1}});
    assertTrue(mc.numAffected()==8);
  }
  
  public void testEmpty(){
    mc.setMatrix(new int[][]{{0, 0, 0},
                             {0, 0, 0},
                             {0, 0, 0}});
    assertTrue(mc.numAffected()==0);
  }
  
  public void testFull(){
    mc.setMatrix(new int[][]{{1, 1, 1},
                             {1, 1, 1},
                             {1, 1, 1}});
    assertTrue(mc.numAffected()==9);
  }
  
  public void testZeroDist(){
    mc.setDist(0);
    assertTrue(mc.numAffected()==2);
  }
}

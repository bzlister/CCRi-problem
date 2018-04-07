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
    assertTrue(mc.method1()==7);
    assertTrue(mc.method2()==7);
  }
  
  public void testSetDist() {
    mc.setDist(2);
    assertTrue(mc.method1()==13);
    assertTrue(mc.method2()==13);
  }
  
  public void testSetMatrix() {
    mc.setMatrix(new int[][]{{0, 0, 0},
                             {0, 1, 0},
                             {0, 0, 0}});
    assertTrue(mc.method1()==5);
    assertTrue(mc.method2()==5);
  }
  
  public void testOverbounds() {
    mc.setDist(10);
    assertTrue(mc.method1()==15);
    assertTrue(mc.method2()==15);
  }
  
  public void testEdge(){
    mc.setMatrix(new int[][]{{0, 1, 0},
                             {1, 0, 0},
                             {0, 0, 0}});
    assertTrue(mc.method1()==6);
    assertTrue(mc.method2()==6);
  }
  
  public void testCorner(){
    mc.setDist(2);
    mc.setMatrix(new int[][]{{1, 0, 0},
                             {0, 0, 0},
                             {0, 0, 1}});
    assertTrue(mc.method1()==9);
    assertTrue(mc.method2()==9);
  }
  
  public void testEmpty(){
    mc.setMatrix(new int[][]{{0, 0, 0},
                             {0, 0, 0},
                             {0, 0, 0}});
    assertTrue(mc.method1()==0);
    assertTrue(mc.method2()==0);
  }
  
  public void testFull(){
    mc.setMatrix(new int[][]{{1, 1, 1},
                             {1, 1, 1},
                             {1, 1, 1}});
    assertTrue(mc.method1()==9);
    assertTrue(mc.method2()==9);
  }
  
  public void testZeroDist(){
    mc.setDist(0);
    assertTrue(mc.method1()==2);
    assertTrue(mc.method2()==2);
  }
  
  public void testLarge(){
    mc.setMatrix(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}});
    assertTrue(mc.method1()==210);
    assertTrue(mc.method2()==210);
    mc.setDist(2);
    assertTrue(mc.method1()==229);
    assertTrue(mc.method2()==229);
  }
}

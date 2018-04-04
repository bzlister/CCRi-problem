import java.util.BitSet;

/*
 * Class for modeling the number of elements within a certain distance of a positive element in a matrix
 * Run by creating a MatrixCounter object with its constructor and calling numAffected
 */
public class MatrixCounter{

    private int[][] m;
    private int n;

    public MatrixCounter(int[][] m, int n){
        setMatrix(m);
        setDist(n);
    }

    public void setDist(int n){
        if (n < 0)
            throw new IllegalArgumentException("Error. Distance threshold must be at least 0");
        this.n = n;
    }

    public void setMatrix(int[][] m){
        if (m.length == 0)
            throw new IndexOutOfBoundsException("Error. Matrix needs to have at least one column and row");        
        this.m = m;
    }

    /*
    Visits every cell in the matrix, and adds a '+' pattern of affected elements to the BitSet, incrementing num for every
    element not already in the BitSet
    O(L*W*n)
    */
    public int numAffected(){
        BitSet used = new BitSet(m.length*m[0].length);//used in lieu of int[][] due to only needing binary values
        int num = 0;
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
                if (m[i][j] > 0){//positive element found
                    for (int v = Math.max(0, i-n); v < Math.min(m.length, i+n+1); v++){//vertical bar of '+' pattern
                        if (!used.get(v*m[0].length+j)){
                            num++;
                            used.set(v*m[0].length+j);
                        }
                    }
                    for (int h = Math.max(0, j-n); h < Math.min(m[0].length, j+n+1); h++){//horizontal bar of '+' pattern
                        if (!used.get(i*m[0].length+h)){
                            num++;
                            used.set(i*m[0].length+h);
                        }
                    }
                }
            }
        }
        return num;
    }
}
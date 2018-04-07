import java.util.ArrayList;

/*
 * Class for modeling the number of elements within a certain distance of a positive element in a matrix
 * Run by creating a MatrixCounter object with its constructor and calling method1 or method2
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
    The first of two solution approaches
    Goes through every element in m. If it is a positive element, increments num by makeAMove
    */
    public int method1(){
        int[][] used = new int[m.length][m[0].length];
        int num = 0;
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
                if (m[i][j] > 0){//positive element found
                    num+=makeAMove(i, j, used, n);
                }
            }
        }
        return num;
    }
    /*
    If a point has either never been visited, increment num
    Unless the point has been reached before in the same or fewer number of steps, record the steps taken and recursively makeAMove N, W, S, E
    I think this method is better if there are few positive numbers in m
    */
    public int makeAMove(int i, int j, int[][] used, int steps){
        int num = 0;
        if ((i >= 0) && (i < m.length) && (j >= 0) && (j < m[0].length)){//if within matrix bounds
            if ((used[i][j] == 0) || (n-steps+1 < used[i][j])){//if either unvisited or visited in fewer steps
                if (used[i][j] == 0)
                    num++;
                used[i][j] = n-steps+1;//record steps taken
                if (steps > 0){
                    for (int t = 0; t < 4; t++){
                        num+=makeAMove(i-Math.abs(t-2)+1, j+Math.abs(t-1)-1, used, steps-1);//recursive step in 4 cardinal directions
                    }
                }
            }
        }
        return num;
    }

    /*
    The second of two solution approaches
    Goes through matrix, filling out an arraylist with the 1D position of every positive element
    Goes through matrix a second time, calling inRange on every element and incrementing num if true
    I think this method is better if there many positive numbers in m
    */
    public int method2(){
        ArrayList<Integer> pos = new ArrayList<Integer>();
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
                if (m[i][j] > 0)
                    pos.add(i*m[0].length+j);
            }
        }
        int num = 0;
        for (int a = 0; a < m.length; a++){
            for (int b = 0; b < m[0].length; b++){

                if ((m[a][b] > 0) || ((pos.size() > 0) && (inRange(a, b, pos)))){
                    num++;
                }
            }
        }
        return num;
    }
    /*
    For a point (i, j), finds the closest positive element (I, J)
    If |i - I| + |j - J| <= n, stops and returns true
    Else, look to the next smallest positive element until none left
    */
    public boolean inRange(int i, int j, ArrayList<Integer> pos){
        int guess= (int)(pos.size()*(i*m[0].length+j)/((double)m.length*m[0].length));
        boolean ir = false;
        if (pos.size() > 0){
            do{
                int dist = Math.abs(i - pos.get(guess)/m[0].length) + Math.abs(j - pos.get(guess)%m[0].length);
                if (dist <= n)
                    ir = true;
                guess--;
            } while ((guess >= 0) && (!ir));
        }
        return ir;
    }
}
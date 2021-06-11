package gaussseidel;


import static java.lang.Math.abs;
import java.util.Scanner;

/**
 *
 * @author ruba8
 */
public class GaussSeidel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        while (true) {  
            System.out.println("Enter => s to start"
                    + "\nEnter => e to end");
            String s = input.next();
            if (s.equals("s")) {
                int i, j = 0;
        //enter data 
        System.out.print("Enter number of Equation : ");
        int num = input.nextInt();
        double[][] A = new double[num][num];
        double[] b = new double[num];
        double[] x = new double[num];
        System.out.println("Enter the elemnts of the matrix :");
        for (i = 0; i < A.length; i++) {
            for (j = 0; j < A.length; j++) {
                A[i][j] = input.nextDouble();
            }
        }
        System.out.println("Enter the solution b matrix to be : ");
        for (i = 0; i < b.length; i++) {
            b[i] = input.nextDouble();
        }
        
                System.out.println("Enter the initail values of x array : ");
        for (i = 0; i < x.length; i++) {
            x[i] = input.nextDouble();
        }
        //end enter data 

        printBefore(A, b);
        checkSwap(A, b);
        printAfter(A, b, num);

        int k = 0,u=1;
        while (true) {
            double newX = equations(A[k], x, b, k);
            double err = calculateError(x, k, newX);
            if (err <= (0.0001)) {
                break;
            } else {
                if(k==0){
                    System.out.println("round ("+u+")");
                    u++;
                }
                x[k] = newX;
                System.out.println("x[" + (k + 1) + "] = " + x[k]);
                k = (k + 1) % x.length; 
            }
            
        }
        System.out.println("\nThe final results :");
        for (int w = 0; w < x.length; w++) {  
            System.out.println("Final x[" + (w + 1) + "] = " + x[w]);
        }
            }
            else {
                System.exit(0);
            }
        }
        
        
    }

    public static double calculateError(double[] x, int k, double newX) {
        double error = Math.abs((newX - x[k]));
        return error;
    }

    public static double equations(double[] A, double[] x, double[] b, int k) {
        double sum = 0;
        for (int i = 0; i < A.length; i++) {
            if ((i) != (k)) {
                sum += A[i] * x[i];
            }

        }
        return (b[k] - sum) / A[k];
    }

    public static void printBefore(double[][] A, double[] b) {
        //print data 
        System.out.println("************************");
        System.out.println("The matrix you entered  :");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                System.out.print(A[i][j] + "  ");
            }
            System.out.println("");
        }
        System.out.println("The array of solution :");
        for (int i = 0; i < b.length; i++) {

            System.out.println(b[i] + "  ");
        }
        System.out.println("");
    }

    private static int checkmax(double[] A) { 
        double ch = abs(A[0]), sum = 0;
        int max = 0;
        for (int i = 1; i < A.length; i++) {
            if (abs(A[i]) > (ch)) {
                ch = abs(A[i]);
                max = i;
            }

        }
        for (int k = 0; k < A.length; k++) {
            if (k != max) {
                sum += abs(A[k]);
            }
        }
        if (sum > ch) {
            return -1;
        }

        return max;
    }

    public static void checkSwap(double[][] A, double[] b) {
        int i = 0;
        while (i < A.length) {
            int correctRow = checkmax(A[i]);
            if (correctRow == -1) {
                System.out.println("can't solve this equation by gauss seidel ...");
                System.exit(0);
            } else if (correctRow == i) {
                i++;
            } else {
                swapRows(A, b, i, correctRow);
            }

        }

    }

    private static void swapRows(double[][] matrix, double[] b, int s, int correct) {
        for (int i = 0; i < matrix.length; i++) {
            // Swap two numbers
            double temp = matrix[s][i];
            matrix[s][i] = matrix[correct][i];
            matrix[correct][i] = temp;

            double temp2 = b[s];
            b[s] = b[correct];
            b[correct] = temp2;

        }
    }

    public static void printAfter(double[][] A, double[] b, int num) {
        //print////////
        System.out.println("******************************************************");
        System.out.println("\n\nthe number of equations is : " + num);
        printBefore(A, b);
    }

}

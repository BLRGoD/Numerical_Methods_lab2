import Jama.Matrix;

import java.util.Scanner;

public class Program {

    public static double f1(double[] xyz) {
        return Math.pow(xyz[0], 2) - 3.7 * Math.pow(xyz[1], 2)  - 9.2 + 9.702;
    }
    public static double f2(double[] xyz) {
        return -2.1 * xyz[0] * xyz[1] + 0.5 * Math.pow(xyz[2], 2)  - Math.pow(xyz[0], 2) - 0.287;
    }
    public static double f3(double[] xyz) {
        return 0.33 * Math.pow(xyz[1],3) + xyz[2] * Math.pow(xyz[0],2)  - 6.4 + 6.37612;
    }

    public static Matrix calcF(double[] xyz) {
        return new Matrix(new double[][] { { - f1(xyz) },
                { - f2(xyz) } ,
                { - f3(xyz) } } );
    }

    public static Matrix calcJakobiMatrix(double[] xyz) {
        return new Matrix( new double[][] { { 2 * xyz[0], - 7.4 * xyz[1], 0 },
                { - 2.1 * xyz[1] - 2 * xyz[0], - 2.1 * xyz[0], xyz[2] },
                { 2 * xyz[0] * xyz[2], 0.99 * Math.pow(xyz[1], 2), Math.pow(xyz[0], 2) } } );
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        double[] xyz = new double[3];
        System.out.println("Начальные значения:");
        System.out.print("x = ");
        xyz[0] = keyboard.nextDouble();
        System.out.print("y = ");
        xyz[1] = keyboard.nextDouble();
        System.out.print("z = ");
        xyz[2] = keyboard.nextDouble();
        System.out.print("Точность (eps): ");
        double e = keyboard.nextDouble();
        double dx = 1, dy = 1, dz = 1;
        int i = 1;

        while(Math.max(Math.max(Math.abs(dx), Math.abs(dy)), Math.abs(dz)) > e) {

            double[][] temp = calcJakobiMatrix(xyz).inverse().times(calcF(xyz)).getArray();
            dx = temp[0][0]; dy = temp[1][0]; dz = temp[2][0];

            xyz[0] += dx;
            xyz[1] += dy;
            xyz[2] += dz;
            System.out.println("итерация: " + i + ",  x = " + xyz[0] + ",  y = " + xyz[1] + ",  z = " + xyz[2]);
            i++;
        }
        System.out.println();
        System.out.println("Результат:");
        System.out.println("x = " + xyz[0]);
        System.out.println("y = " + xyz[1]);
        System.out.println("z = " + xyz[2]);
        System.out.println("f1(x, y, z) = " + f1(xyz));
        System.out.println("f2(x, y, z) = " + f2(xyz));
        System.out.println("f3(x, y, z) = " + f3(xyz));
    }
}



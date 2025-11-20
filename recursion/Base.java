package recursion;

public class Base {
    public static void main(String[] args) {
        int[] array = new int[]{4, -1, -2, 5, 3, -6, 2, 4, -3, 2, 3, -2, 3};
        printNaturalNumber(50);
        int sum =printSumOfNaturalNumber(5);

        System.out.println("|||||----------------------------------------------------------------------------------------------------|||||");

        System.out.println(sum);

        System.out.println("|||||----------------------------------------------------------------------------------------------------|||||");

    }

    public static void printNaturalNumber(int N) {
        if (N == 0) {
            System.out.println("N is zero");
            return;
        }
        printNaturalNumber(N - 1);
        System.out.println(N);
    }

    public static int printSumOfNaturalNumber(int N){
        if (N == 0) {
            return 0;
        }
        return printSumOfNaturalNumber(N - 1) + N;
    }
}

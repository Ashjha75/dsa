package recursion;

public class Base {

	public static void main(String[] args) {
		int[] array = new int[] { 4, -1, -2, 5, 3, -6, 2, 4, -3, 2, 3, -2, 3 };
		printNaturalNumber(50);
		int sum = printSumOfNaturalNumber(5);
		int pow = findPowerOfNum(5, 6);
		int paths = countPathInMatrics(3, 4);

		System.out.println(
			"|||||----------------------------------------------------------------------------------------------------|||||"
		);

		System.out.println(sum);
		System.out.println(pow);
		System.out.println(paths);

		System.out.println(
			"|||||----------------------------------------------------------------------------------------------------|||||"
		);
	}

	public static void printNaturalNumber(int N) {
		if (N == 0) {
			System.out.println("N is zero");
			return;
		}
		printNaturalNumber(N - 1);
		System.out.println(N);
	}

	public static int printSumOfNaturalNumber(int N) {
		if (N == 0) {
			return 0;
		}
		return printSumOfNaturalNumber(N - 1) + N;
	}

	public static int findPowerOfNum(int num, int pow) {
		if (pow == 0) {
			//            throw new IllegalArgumentException("Power must be greater than 0");
			return 1;
		}
		if (num == 0) {
			return 0;
		}
		return num * findPowerOfNum(num, pow - 1);
	}

	public static int countPathInMatrics(int num1, int num2) {
		if (num1 == 1 || num2 == 1) {
			return 1;
		}
		int rightPathSum = countPathInMatrics(num1, num2 - 1);
		int downPathSum = countPathInMatrics(num1 - 1, num2);

		return downPathSum + rightPathSum;
	}
}

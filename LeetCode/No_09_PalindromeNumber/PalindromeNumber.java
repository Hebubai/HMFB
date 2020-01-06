package No_09_PalindromeNumber;

public class PalindromeNumber {
	public static boolean isPalindrome(int x) {
		if (x < 0 || x % 10 == 0 && x != 0) {
			return false;
		}
		int a = 1;
		int index = x;
		while (index >= 10) {
			a *= 10;
			index /= 10;
		}
		while (x > 0) {
			if (x % 10 == x / a) {
				x = (x % a) / 10;
				a /= 100;
			} else {
				return false;
			}
		}
		return true;
	}
}

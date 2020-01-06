package No_07_ReverseInteger;

public class ReverseInteger {
	public static int reverse(int x) {
		long Number = 0;
		while (x != 0) {
			Number = Number * 10 + x % 10;
			x = x / 10;
		}
		if (Number > Integer.MAX_VALUE || Number < Integer.MIN_VALUE) {
			return 0;
		} else {
			return (int) Number;
		}
	}
}
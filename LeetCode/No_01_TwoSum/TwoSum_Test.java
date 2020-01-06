package No_01_TwoSum;

public class TwoSum_Test {
	public static void main(String[] args) {
		int[] nums = { 3, 3 };
		int target = 6;
		int[] Sum = TwoSum.twoSum(nums, target);
		for (int i : Sum) {
			System.out.println(Sum[i]);
		}
	}
}
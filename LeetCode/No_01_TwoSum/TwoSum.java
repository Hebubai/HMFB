package No_01_TwoSum;

import java.util.*;
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]),i};
            }else{
                map.put(target-nums[i], i);
            }
        }
//         HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//         for (int i = 0; i < nums.length; i++) {
//             map.put(nums[i], i);
//         }
//         int a;
//         for (int i = 0; i < nums.length; i++) {
//             a = target - nums[i];
//             if (map.containsKey(a) && map.get(a) != nums[i]) {
//                 return new int[] { i, map.get(a) };
//             }
//         }
        return null;
    }
}
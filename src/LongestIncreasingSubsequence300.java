import java.util.Arrays;

/**
 * Description:利用二分查找，维护一个升序数组，新数在其中会覆盖恰好比他大的数，为以后铺垫
 *
 * Solution:
 *
 * @author peter
 * @date 2019/6/9.
 */
public class LongestIncreasingSubsequence300 {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}

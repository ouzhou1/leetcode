/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/6/9.
 */
public class NumberIncreasingSubsequence673 {

    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] len = new int[n];
        int[] cnt = new int[n];

        for (int i = 0; i < n; i++) {
            cnt[i] = len[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (len[i] == len[j] + 1) {
                        cnt[i] += cnt[j];
                    }
                }
            }
        }

        return cnt[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 5, 5, 5, 5};
        System.out.println(findNumberOfLIS(nums));
    }
}

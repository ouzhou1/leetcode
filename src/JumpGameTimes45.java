/**
 * Description:
 *
 * @author: peter
 * @date: 2019/4/7.
 */
public class JumpGameTimes45 {
    private static int jump(int[] nums) {
        int curEnd = 0, fastestEnd = 0, jumps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            fastestEnd = Math.max(fastestEnd, i + nums[i]);
            if (curEnd == i) {
                jumps++;
                curEnd = fastestEnd;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));
    }
}

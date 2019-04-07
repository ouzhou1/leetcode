/**
 * Description:
 *
 * @author: peter
 * @date: 2019/4/7.
 */
public class JumpGame55 {
    private static boolean canJump(int[] nums) {
        int steps = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (steps + nums[i] == 0 && i < nums.length - 1) {
                return false;
            }
            steps = Math.max(steps, nums[i]);
            steps--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canJump(new int[] {2,3,1,1,4}));
    }
}

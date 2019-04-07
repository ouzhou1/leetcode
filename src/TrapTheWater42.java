/**
 * Description:
 *
 * @author: peter
 * @date: 2019/4/7.
 */
public class TrapTheWater42 {
    private static int trap(int[] heights) {
        int water = 0, minHeight = 0, left = 0, right = heights.length - 1;
        while (left < right) {
            if (heights[left] <= minHeight) {
                water += minHeight - heights[left++];
            } else if (heights[right] <= minHeight) {
                water += minHeight - heights[right--];
            } else {
                minHeight = Math.min(heights[left], heights[right]);
            }
        }
        return water;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}

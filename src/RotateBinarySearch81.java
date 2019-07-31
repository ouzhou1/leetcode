/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/7/23.
 */
public class RotateBinarySearch81 {

    private static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int start = 0, len = nums.length;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                start = i + 1;
            }
        }

        int end = start + len, mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid % len] == target) {
                return mid % len;
            } else if (nums[mid % len] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 7, 9, 11, 0, 1, 2};
        System.out.println(RotateBinarySearch81.search(nums, 7));
        System.out.println(RotateBinarySearch81.search(nums, 1));
    }
}

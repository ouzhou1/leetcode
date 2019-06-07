/**
 * Description: Input: [2, 6, 4, 8, 10, 9, 15] Output: 5 Explanation: You need to sort [6, 4, 8, 10,
 * 9] in ascending order to make the whole array sorted in ascending order.
 * 问题关键是从起点开始找最大的，max一直都会更新才会是递增的 同样，从终点开始找最小的，min一直都会更新才会是递减的
 *
 * @author peter
 * @date 2019/6/7.
 */
public class ShortestUnsortedSubarray581 {

    private static int findUnsortedSubarray(int[] array) {
        // 最终答案所用指针
        int start = 2, end = 1;
        int min = array[array.length - 1], max = array[0];
        for (int i = 1; i < array.length; i++) {
            // 寻找最右端递增的部分，用end标记
            max = Math.max(max, array[i]);
            if (array[i] < max) {
                end = i;
            }
            // 寻找最左端递增的部分，用start标记
            min = Math.min(min, array[array.length - 1 - i]);
            if (array[array.length - 1 - i] > min) {
                start = array.length - 1 - i;
            }
        }
        return end - start + 1;
    }

    public static void main(String[] args) {
        int[] list = new int[]{1, 2, 3, 4};
        System.out.println(findUnsortedSubarray(list));
    }
}

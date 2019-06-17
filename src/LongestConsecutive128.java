import java.util.HashMap;

/**
 * Description: Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Solution: Using hash map to record element length, and update boundary
 *
 * @author peter
 * @date 2019/6/17.
 */
public class LongestConsecutive128 {
    public static int longestConsecutive(int[] array) {
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>(16);
        for (int element : array) {
            if (!map.containsKey(element)) {
                int left = map.getOrDefault(element - 1, 0);
                int right = map.getOrDefault(element + 1, 0);
                int sum = left + right + 1;
                result = Math.max(result, sum);
                map.put(element, result);
                // 更新边界
                map.put(element + right, sum);
                map.put(element - left, sum);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(array));
    }
}

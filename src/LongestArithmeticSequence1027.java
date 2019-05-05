import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author: peter
 * @date: 2019/5/5.
 */
public class LongestArithmeticSequence1027 {

    /**
     * 1. 存储结构：二维map，差值作为key, 元素组作为value存入map，元素组记录最后的元素和对应长度
     * 2. 两次循环，计算出公差，每次都和最终结果比较一下
     * @param a
     * @return
     */
    public static int longestArithSeqLength(int[] a) {
        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>(16);
        int res = 2;
        for (int i = 0; i < a.length; i++) {
           for (int j = i + 1; j < a.length; j++) {
               Map<Integer, Integer> map = dp.computeIfAbsent(a[j] - a[i], k -> new HashMap<>());
               map.put(j, map.getOrDefault(i, 1) + 1);
               res = Math.max(res, map.get(j));
           }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] input = {3, 6, 6, 12, 9};
        System.out.println(LongestArithmeticSequence1027.longestArithSeqLength(input));
    }
}

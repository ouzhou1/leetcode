import java.util.Arrays;

/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/7/23.
 */
public class SumOfEvenNumber985 {

    private static int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int base = 0, len = A.length;
        int[] result = new int[len];
        for (int aA : A) {
            if (aA % 2 == 0) {
                base += aA;
            }
        }
        for (int i = 0; i < len; i++) {
            int[] query = queries[i];
            int element = A[query[1]];
            if (element % 2 == 0) {
                if (query[0] % 2 == 0) {
                    base += query[0];
                } else {
                    base -= element;
                }
            } else {
                if (Math.abs(query[0] % 2) == 1) {
                    base += (query[0] + element);
                }
            }
            result[i] = base;
            A[query[1]] += query[0];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 3, -3, 9, -6, 8, -5};
        int[][] queries = {{-5, 1}, {10, 2}, {-6, 3}, {3, 2}, {9, 5}, {7, 5}, {8, 0}};
        System.out.println(Arrays.toString(sumEvenAfterQueries(arr, queries)));
    }
}

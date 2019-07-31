import java.util.Arrays;

/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/7/31.
 */
public class HeapSort {

    private static void adjust(int[] input, int parent, int length) {
        int temp = input[parent];
        int child = 2 * parent + 1;
        while (child < length) {
            if (child + 1 < length && input[child] > input[child + 1]) {
                child++;
            }

            if (temp <= input[child]) {
                break;
            }
            input[parent] = input[child];
            parent = child;
            child = 2 * child + 1;
        }
        input[parent] = temp;
    }

    private static void sort(int[] input) {
        int n = input.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            adjust(input, i, n);
        }

        for (int i = 0; i < n; i++) {
            int temp = input[n - i - 1];
            input[n - i - 1] = input[0];
            input[0] = temp;
            adjust(input, 0, n - i - 1);
        }
    }

    public static void main(String[] args) {
        int[] input = {2, 7, 8, 5, 6, 10, 9};
        sort(input);
        System.out.println(Arrays.toString(input));
    }
}

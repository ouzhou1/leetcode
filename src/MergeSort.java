import java.util.Arrays;

/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/7/31.
 */
public class MergeSort {

    private static void merge(int[] input, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            if (input[i] < input[j]) {
                temp[k++] = input[i++];
            } else {
                temp[k++] = input[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = input[i++];
        }

        while (j <= r) {
            temp[k++] = input[j++];
        }

        for (int m = l, n = 0; m <= r; m++, n++) {
            input[m] = temp[n];
        }

    }

    private static void sort(int[] input, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        sort(input, l, mid);
        sort(input, mid + 1, r);
        merge(input, l, mid, r);
    }

    public static void main(String[] args) {
        int[] input = {4, 10, 9, 5, 8, 1};
        sort(input, 0, input.length - 1);
        System.out.println(Arrays.toString(input));
    }
}

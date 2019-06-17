import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/6/8.
 */
public class SpiralMatrix54 {

    private static List<Integer> spiralOrder(int[][] matrix) {
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        List<Integer> list = new ArrayList<>();
        while (top <= bottom && left <= right) {
            int i = left;
            while (i <= right) {
                list.add(matrix[top][i++]);
            }
            if (top == bottom) {
                return list;
            }
            top++;

            i = top;
            while (i <= bottom) {
                list.add(matrix[i++][right]);
            }
            if (left == right) {
                return list;
            }
            right--;

            i = right;
            while (i >= left) {
                list.add(matrix[bottom][i--]);
            }
            if (top == bottom) {
                return list;
            }
            bottom--;

            i = bottom;
            while (i >= top) {
                list.add(matrix[i--][left]);
            }
            if (left == right) {
                return list;
            }
            left++;
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.println(spiralOrder(matrix));
    }
}

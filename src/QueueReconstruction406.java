import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: 一个二维数组记录了一些身高各异的人和在他们身前人的个数，根据这些条件来正确的排队
 *
 * Solution: 此题关键是排序，先让高的排前面，这样就可以完全确定排的位置
 *
 * @author peter
 * @date 2019/6/8.
 */
public class QueueReconstruction406 {

    private static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] != o2[0] ? -o1[0] + o2[0] : o1[1] - o2[1]);
        List<int[]> res = new ArrayList<>();
        for (int[] cur : people) {
            res.add(cur[1], cur);
        }
        return res.toArray(new int[people.length][]);
    }

    public static void main(String[] args) {
        int[][] people = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }

}

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: peter
 * @date: 2019/4/8.
 */
public class SourcePathToTarget797 {
    private static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, graph, new ArrayList<>(), result);
        return result;
    }

    private static void helper(int index, int[][] graph, List<Integer> list, List<List<Integer>> result) {
        list.add(index);
        if (index == graph.length - 1) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int element : graph[index]) {
            helper(element, graph, list, result);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}}));
    }
}

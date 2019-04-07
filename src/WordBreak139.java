import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: peter
 * @date: 2019/4/7.
 */
public class WordBreak139 {

    private static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("pen");
        list.add("applep");
        list.add("enapple");
        System.out.println(wordBreak("applepenapple", list));
        System.out.println(wordBreak("appleppen", list));
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/6/20.
 */
public class FindAnagram438 {

    private static List<Integer> findAnagrams(String string, String pattern) {
        int len = string.length(), pLen = pattern.length();
        int start = 0, end = 0;
        List<Integer> result = new ArrayList<>();
        int count = pLen;
        int[] hash = new int[26];
        for (int i = 0; i < pLen; i++) {
            hash[pattern.charAt(i) - 'a']++;
        }
        while (end < len) {
            if (hash[string.charAt(end++) - 'a']-- >= 1) {
                count--;
            }
            if (count == 0) {
                result.add(start);
            }
            if (end - start == pLen) {
                if (hash[string.charAt(start++) - 'a']++ >= 0) {
                    count++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("abab", "ab"));
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: peter
 * @date: 2019/4/13.
 */
public class MajorityElement29 {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int candidateA = nums[0];
        int candidateB = nums[0];
        int countA = 0;
        int countB = 0;
        for (int num : nums) {
            if (num == candidateA) {
                countA++;
                continue;
            }
            if (num == candidateB) {
                countB++;
                continue;
            }
            if (countA == 0) {
                candidateA = num;
                countA++;
                continue;
            }
            if (countB == 0) {
                candidateB = num;
                countB++;
                continue;
            }
            countA--;
            countB--;
        }

        countA = 0;
        countB = 0;
        for (int num : nums) {
            if (num == candidateA) {
                countA++;
            } else if (num == candidateB) {
                countB++;
            }
        }
        List<Integer> resultList = new ArrayList<>();
        if (countA > nums.length / 3) {
            resultList.add(candidateA);
        }
        if (countB > nums.length / 3) {
            resultList.add(candidateB);
        }
        return resultList;
    }
}

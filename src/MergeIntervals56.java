import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 *
 * @author: peter
 * @date: 2019/4/7.
 */
public class MergeIntervals56 {

    private static final Integer MIN_SIZE = 2;
    private static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < MIN_SIZE) {
            return intervals;
        }
        intervals.sort(Comparator.comparingInt(Interval::getStart));
        List<Interval> result = new LinkedList<>();
        int start = intervals.get(0).getStart(), end = intervals.get(0).getEnd();
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).getStart() <= end) {
                end = Math.max(intervals.get(i).getEnd(), end);
            } else {
                result.add(new Interval(start, end));
                start = intervals.get(i).getStart();
                end = intervals.get(i).getEnd();
            }
        }
        result.add(new Interval(start, end));
        return result;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(2, 6));
        input.add(new Interval(8, 10));
        input.add(new Interval(15, 18));
        merge(input).forEach(e -> System.out.println(e.toString()));
    }
}


class Interval {
    private int start;
    private int end;

    Interval(int s, int e) {
        start = s;
        end = e;
    }

    int getStart() {
        return start;
    }

    int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

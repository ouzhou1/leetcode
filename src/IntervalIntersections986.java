import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: peter
 * @date: 2019/4/9.
 */
public class IntervalIntersections986 {
    private static Interval[] intervalIntersection(Interval[] arrA, Interval[] arrB) {
        int lengthA = arrA.length, lengthB = arrB.length;
        int indexA = 0, indexB = 0;
        List<Interval> result = new ArrayList<>();
        while (indexA < lengthA && indexB < lengthB) {
            Interval a = arrA[indexA], b = arrB[indexB];
            if (a.getStart() <= b.getStart() && b.getStart() <= a.getEnd()) {
                result.add(new Interval(b.getStart(), Math.min(a.getEnd(), b.getEnd())));
            } else if (b.getStart() <= a.getStart() && a.getStart() <= b.getEnd()) {
                result.add(new Interval(a.getStart(), Math.min(a.getEnd(), b.getEnd())));
            }
            if (a.getEnd() <= b.getEnd()) {
                indexA++;
            } else {
                indexB++;
            }
        }
        return result.toArray(new Interval[0]);
    }

    public static void main(String[] args) {
        Interval[] intervalsA = new Interval[4];
        Interval[] intervalsB = new Interval[4];
        intervalsA[0] = new Interval(0, 2);
        intervalsA[1] = new Interval(5, 10);
        intervalsA[2] = new Interval(13, 23);
        intervalsA[3] = new Interval(24, 25);

        intervalsB[0] = new Interval(1, 5);
        intervalsB[1] = new Interval(8, 12);
        intervalsB[2] = new Interval(15, 24);
        intervalsB[3] = new Interval(25, 26);
        for (Interval interval : intervalIntersection(intervalsA, intervalsB)) {
            System.out.println(interval.toString());
        }
    }
}

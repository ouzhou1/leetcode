/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/7/23.
 */
public class MedianOfTwoSortedArray4 {

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        if (len % 2 == 0) {
            double left = findKthHelper(nums1, 0, nums2, 0, len / 2);
            double right = findKthHelper(nums1, 0, nums2, 0, len / 2 + 1);
            return (left + right) / 2;
        } else {
            return findKthHelper(nums1, 0, nums2, 0, len / 2 + 1);
        }
    }

    private static double findKthHelper(int[] A, int aStart, int[] B, int bStart, int k) {
        if (aStart >= A.length) {
            return B[bStart + k - 1];
        }
        if (bStart >= B.length) {
            return A[aStart + k - 1];
        }
        if (k == 1) {
            return Math.min(A[aStart], B[bStart]);
        }
        int aMid = aStart + k / 2 - 1;
        int bMid = bStart + k / 2 - 1;
        int aVal = aMid >= A.length ? Integer.MAX_VALUE : A[aMid];
        int bVal = bMid >= B.length ? Integer.MAX_VALUE : B[bMid];
        if (aVal <= bVal) {
            return findKthHelper(A, aMid + 1, B, bStart, k - k / 2);
        } else {
            return findKthHelper(A, aStart, B, bMid + 1, k - k / 2);
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1};
        int[] arr2 = {1, 3, 4, 6, 7, 9, 10};
        System.out.println(findMedianSortedArrays(arr1, arr2));
    }
}

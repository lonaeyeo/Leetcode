package ArrayAndSort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class KNearestPoints {

    @Test
    public void test() {
        int[] dd = new int[2];
        int[][] a = {{3, 3}, {5, -1}, {2, 4}, {2, 2}};

        int[][] KPoints = KNearestPoints.kClosest(a, 3);

        for (int[] point : KPoints) {
            System.out.println(point[0] + ", " + point[1]);
        }
    }

    public static int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                return (point1[0] * point1[0] + point1[1] * point1[1]) - (point2[0] * point2[0] + point2[1] * point2[1]);
            }
        });
        return Arrays.copyOfRange(points, 0, K);
    }

}

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class KNearestPoints {

    @Test
    public void test() {
//        ArrayList<Integer> al = new ArrayList<>();
        System.out.println(Math.pow(4, 2));
        System.out.println(Math.sqrt(4));

        int[][] a = new int[6][2];


    }

    public int[][] kClosest(int[][] points, int K) {
        double[][] distance = new double[K][2];
        Arrays.fill(distance, Integer.MAX_VALUE);
//        double[] sort = new double[6];
        double[][] KPoints = new double[K][2];
        for (int i = 0; i < points.length; ++i) {
            double ans = Math.sqrt(Math.pow(points[i][0], 2) + Math.pow(points[i][1], 2));
            double temp1 = 0;
            double temp2 = i;
            int index = 0;
            while (index < K && distance[index][0] > temp) {
                index++;
            }

            while(index < K) {
                temp1 = distance[index][0];
                temp2 = distance[index][1];
                distance
            }
        }

        Arrays.sort(sort);
        for (int i = 0; i < K; ++i) {

        }
        return null;
    }
}

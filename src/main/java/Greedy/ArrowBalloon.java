package Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class ArrowBalloon {
    public int findMinArrowShots(int[][] points) {
        // 先排序，再贪心，看最左气球的最右边界容纳射程
        if (points.length == 0) {
            return 0;
        }

        Arrays.sort(points, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] < o2[1])
                    return -1;
                else if (o1[1] > o2[1])
                    return 1;
                else return 0;
            }
        });

        int ans = 1;
        int pos = points[0][1];

        // 查看当前气球的最右边界能接受的范围，
        // 如果新气球最左 > 当前气球的最右，那么pos要改变，并且射箭次数+1；
        for (int[] point: points) {
            if (point[0] > pos) {
                pos = point[1];
                ++ans;
            }
        }

        return ans;
    }
}

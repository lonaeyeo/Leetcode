package ArrayAndSort;

import org.junit.Test;

import java.util.*;

public class ArrayOperation {

    @Test
    public void test() {
        ArrayOperation arrayOperation = new ArrayOperation();
        int[] a = new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] b = new int[]{2, 1, 4, 3, 9, 6};

        a = arrayOperation.relativeSortArray(a, b);
        for (int t : a) {
            System.out.println(t);
        }
    }

    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new TreeMap<>();

        for (int a1num : arr1) {
            int num = map.getOrDefault(a1num, 0) + 1;
            map.put(a1num, num);
        }

        int index = 0;
        for (int a2num : arr2) {
            for (int i = 0; i < map.get(a2num); ++i) {
                arr1[index] = a2num;
                ++index;
            }
            map.remove(a2num);
        }

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Integer key = (Integer) entry.getKey();
            Integer numOfKey = (Integer) entry.getValue();
            for (int i = 0; i < numOfKey; ++i) {
                arr1[index] = key;
                ++index;
            }
        }

        return arr1;
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        // int[]数组不能用Arrays.sort(, Comparator...)
        // 必须要Integer[]数组
        // 所以这里用一个ArrayList
        List<Integer> list = new ArrayList<>();
        for(int num : arr1) list.add(num);
        for (int i = 0; i < arr2.length; i++) map.put(arr2[i], i);
        Collections.sort(list, (x, y) -> {
            if(map.containsKey(x) || map.containsKey(y)) return map.getOrDefault(x, 1001) - map.getOrDefault(y, 1001);
            return x - y;
        });
        for(int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }
}

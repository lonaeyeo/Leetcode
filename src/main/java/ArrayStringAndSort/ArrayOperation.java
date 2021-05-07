package ArrayStringAndSort;

import TreeAndNode.ListNode;
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
        for (int num : arr1) list.add(num);
        for (int i = 0; i < arr2.length; i++) map.put(arr2[i], i);
        Collections.sort(list, (x, y) -> {
            if (map.containsKey(x) || map.containsKey(y)) return map.getOrDefault(x, 1001) - map.getOrDefault(y, 1001);
            return x - y;
        });
        for (int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }

    @Test
    public void test1() {
        String s = "ass";
        String b = "ssa";
        System.out.println(ArrayOperation.isAnagram(s, b));
    }

    public static boolean isAnagram(String s, String t) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        if (s.length() != t.length())
            return false;

        for (int i = 0; i < s.length(); i++) {
            int flag = s.charAt(i) - 'a';
            count1[flag]++;
            flag = t.charAt(i) - 'a';
            count2[flag]++;
        }

        for (int j = 0; j < 26; j++) {
            if (count1[j] != count2[j])
                return false;
        }

        return true;
    }

    @Test
    public void test2() {
        System.out.println(ArrayOperation.sortString("leetcode"));

    }

    public static String sortString(String s) {
        // 用26位数组来装
        int[] nums = new int[26];

        for (int i = 0; i < s.length(); ++i) {
            ++nums[s.charAt(i) - 'a'];
        }

        StringBuffer ans = new StringBuffer();

        while (ans.length() < s.length()) {
            for (int i = 0; i < 26; ++i) {
                if (nums[i] != 0) {
                    --nums[i];
                    ans.append((char) (i + 'a'));
                }
            }
            for (int i = 25; i >= 0; --i) {
                if (nums[i] != 0) {
                    --nums[i];
                    ans.append((char) (i + 'a'));
                }
            }
        }
        return ans.toString();
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<>();
        Map<Integer, Integer> countCD = new HashMap<>();
        int ans = 0;

        for (int a : A)
            for (int b : B) {
                countAB.put(a + b, 1 + countAB.getOrDefault(a + b, 0));
            }

        for (int c : C)
            for (int d : D) {
                if (countAB.containsKey(-c - d)) {
                    ans += countAB.get(-c - d);
                }
            }
        return ans;
    }

    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i > 1; --i) {
            if (A[i] < A[i - 1] + A[i - 2])
                return A[i] + A[i - 1] + A[i - 2];
        }
        return 0;
    }

    // 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> currRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    currRow.add(1);
                else
                    currRow.add(results.get(i - 1).get(j - 1) + results.get(i - 1).get(j));
            }
            results.add(currRow);
        }
        return results;
    }

    // 剪绳子
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3;
        int b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        else if (b == 1) return (int) Math.pow(3, a - 1) * 4;
        else return (int) Math.pow(3, a) * 2;
    }

    /**
     * 最小路径
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][1] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }

        return dp[m][n];
    }

    @Test
    public void test3() {
        System.out.println(178 & 1);
        System.out.println(-1 >>> 1);
        ArrayOperation a = new ArrayOperation();
//        System.out.println(a.minPathSum(new int[][]{{1, 2}, {1, 1}}));
        int b = -1;
        System.out.println(Integer.toBinaryString(b));
        System.out.println(a.reverseBits(b));
    }


    /**
     * num中1111最大
     */
    public int reverseBits(int num) {
        int n = num;
        if (n == 0) return 1;
        if (n == -1) return 32;

        int curr = 0;
        int res = 0;
        int pre = 0;

        while (n != 0) {
            if ((n & 1) == 1) {
                curr++;
            } else {
                // 从一个方向开始，只需考虑前面一个0
                // pre可能是一个0以前的连续1串，也可能是不是
                res = Math.max(res, curr + pre + 1);
                // 第一次遇0时，pre>0，再次遇0时，pre=0
                pre = curr;
                curr = 0;
            }
            // ">>"有符号右移，">>>"无符号右移
            n = n >>> 1;
        }
        res = Math.max(res, curr + pre + 1);
        return res;
    }


    /**
     * 最小K个数
     * 大顶堆的方法
     * 时间复杂度：O(nlogk)，其中 n 是数组 arr 的长度。由于大根堆实时维护前 k 小值，
     * 所以插入删除都是 O(logk) 的时间复杂度，最坏情况下数组里 n 个数都会插入，因此一共需要 O(nlogk) 的时间复杂度。
     * <p>
     * 空间复杂度：O(k)，因为大根堆里最多 k 个数。
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];

        if (k < 1)
            return res;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            // 堆顶数最大
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < queue.peek()) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int[] nums = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    /**
     * 颜色分类，此处是三类0，1，2
     * 方法一：单指针
     */
    public void sortColors(int[] nums) {
        int pointer = 0;
        for (int i = 0; i < nums.length; i++) {
            // 先分类0
            if (nums[i] == 0) {
                int temp = nums[pointer];
                nums[pointer] = nums[i];
                nums[i] = temp;
                pointer++;
            }
        }

        // 再分类1
        for (int i = pointer; i < nums.length; i++) {
            if (nums[i] == 1) {
                int temp = nums[pointer];
                nums[pointer] = nums[i];
                nums[i] = temp;
                pointer++;
            }
        }
    }

    // 方法二：双指针
    public void sortColors1(int[] nums) {
        int zero = 0;
        int index = 0;
        int two = nums.length - 1;

        while (index <= two) {
            if (nums[index] == 0) {
                int temp = nums[zero];
                nums[zero] = nums[index];
                nums[index] = temp;
                zero++;
                index++;
            } else if (nums[index] == 1) {
                index++;
            } else {
                int temp = nums[two];
                nums[two] = nums[index];
                nums[index] = temp;
                two--;
            }
        }
    }


    /**
     * 264. 丑数
     * 小顶堆
     */
    public int nthUglyNumber(int n) {
        int[] factors = new int[]{2, 3, 5};
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        seen.add(1L);
        queue.offer(1L);

        long ugly = 0;

        for (int i = 0; i < n; i++) {
            ugly = queue.poll();
            for (int j = 0; j < factors.length; j++) {
                Long temp = ugly * factors[j];
                if (seen.add(temp)) {
                    queue.offer(temp);
                }
            }
        }

        return Math.toIntExact(ugly);
    }

    // dp
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int a = 1, b = 1, c = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(Math.min(dp[a] * 2, dp[b] * 3), dp[c] * 5);
            // 重复都会被剔除掉
            if (dp[i] == dp[a] * 2) a++;
            if (dp[i] == dp[b] * 3) b++;
            if (dp[i] == dp[c] * 5) c++;
        }
        return dp[n];
    }


    /**
     * 剑指 Offer 42. 连续子数组的最大和
     */
    public int maxSubArray(int[] nums) {
        // 包含[i]的子数组最大和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            res = Math.max(dp[i], res);
        }
        return res;
    }

    public int maxSubArray1(int[] nums) {
        // dp降维复杂度
        int max = nums[0];
        int res = max;

        for (int i = 1; i < nums.length; i++) {
            max = nums[i] + (max > 0 ? max : 0);
            res = Math.max(res, max);
        }
        return res;
    }


    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     */
    StringBuilder res;
    int count = 0, n;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public String printNumbers(int n) {
        this.n = n;
        res = new StringBuilder(); // 数字字符串集
        num = new char[n]; // 定义长度为 n 的字符列表
        dfs(0); // 开启全排列递归
        res.deleteCharAt(res.length() - 1); // 删除最后多余的逗号
        return res.toString(); // 转化为字符串并返回
    }

    void dfs(int x) {
        if (x == n) { // 终止条件：已固定完所有位
            String tmp = String.valueOf(num);
            int i;
            for (i = 0; i < tmp.length(); ) {
                if (tmp.charAt(i) == '0')
                    break;
                else
                    i++;
            }
            String curr;
            if (i == tmp.length())
                curr = "0";
            else
                curr = tmp.substring(i, tmp.length());
            res.append(curr + ","); // 拼接 num 并添加至 res 尾部，使用逗号隔开
            return;
        }
        for (char i : loop) { // 遍历 ‘0‘ - ’9‘
            num[x] = i; // 固定第 x 位为 i
            dfs(x + 1); // 开启固定第 x + 1 位
        }
    }


    /**
     * 剑指 Offer 15. 二进制中1的个数
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res = res + (n & 1);
            // 无符号右移一位
            n = n >>> 1;
        }
        return res;
    }

    // 没操作(n & n-1)一下，就少一个1
    public int hammingWeight2(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode root = new ListNode(0);
        ListNode curr = root;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 != null) curr.next = l1;
        else if (l2 != null) curr.next = l2;
        return root.next;
    }

    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        Iterator it = map.entrySet().iterator();
        int max = 0;
        int res = -1;
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
            if (entry.getValue() > max) {
                max = entry.getValue();
                res = entry.getKey();
            }
        }

        return res;
    }


    /**
     * 剑指 Offer 45. 把数组排成最小的数
     */
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        QSortForMinN(strs, 0, strs.length - 1);
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    public void QSortForMinN(String[] nums, int left, int right) {
        int l = left;
        int r = right;
        // 退出条件
        if (l >= r) return;
        String curr = nums[l];

        while (l < r) {
            // 从后开始
            while (l < r && (curr + nums[r]).compareTo(nums[r] + curr) <= 0) r--;
            // 如果curr小于nums[l]，那么l++，直到出现nums[l]大于curr。例如3+30=330 > 30+3=303，那么循环继续
            while (l < r && (curr + nums[l]).compareTo(nums[l] + curr) >= 0) l++;
            String tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
        }
        // 别忘记最后把当前partition的对比值进行交换，因为交换过后，l-1就是新左空间，l+1就是新右空间
        nums[left] = nums[l];
        nums[l] = curr;

        QSortForMinN(nums, left, l - 1);
        QSortForMinN(nums, l + 1, right);
    }

    Random random = new Random();
    // 快排的key也要random，这年头就是这样的
    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int l = left;
        int r = right;
        int keyIndex = random.nextInt(right - left + 1) + left;
        int flag = nums[keyIndex];
        swap(nums, l, keyIndex);

        while (l < r) {
            while (l < r && nums[r] >= flag) r--;
            while (l < r && nums[l] <= flag) l++;
            swap(nums, l, r);
        }
        swap(nums, l, left);

        quickSort(nums, left, l - 1);
        quickSort(nums, l + 1, right);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public String minNumber2(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    @Test
    public void test4() {
        ArrayOperation a = new ArrayOperation();
        System.out.println(a.minNumber(new int[]{3, 30, 34, 5, 9}));
//        System.out.println(a.nthUglyNumber2(10));
    }
}

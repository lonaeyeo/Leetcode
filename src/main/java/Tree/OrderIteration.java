package Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderIteration {

    @Test
    public void test () {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(root == null)
            return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // 遍历当层节点，同时将下一层节点加入队列
        // 在循环开始获取当前层的节点数量
        while (!queue.isEmpty()) {
            int currLevelLen = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 1; i <= currLevelLen; ++i) {
                TreeNode currNode = queue.poll();
                level.add(currNode.val);
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
            ans.add(level);
        }

        return ans;
    }
}

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Integer[] arr = {1, 2, 3, 4, 5};
        TreeNode root = buildTree(arr, 0);
        Main m = new Main();
        int res = m.diameterOfBinaryTree(root);
        System.out.println(res);
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

    }

    /*Binary Tree Inorder Traversal
    https://leetcode.com/problems/binary-tree-inorder-traversal/description/?envType=problem-list-v2&envId=binary-tree
    Example 1:

    Input: root = [1,null,2,3]

    Output: [1,3,2] */

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root,list);
        System.out.println(list);
       return list;

    }

    static void inorder(TreeNode root,List<Integer> list) {
        if(root == null) {
            return ;
        }
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);

    }

    static TreeNode buildTree(Integer[] arr, int i) {
        if (i >= arr.length || arr[i] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[i]);

        root.left = buildTree(arr, 2 * i + 1);
        root.right = buildTree(arr, 2 * i + 2);

        return root;
    }

    /*Binary Tree Level Order Traversal
    https://leetcode.com/problems/binary-tree-level-order-traversal/description/?utm_source=chatgpt.com
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]

     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if(root == null) {
            return res;
        }
        queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> list = new ArrayList<>();
                for(int i = 0;i<size;i++) {

                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                    list.add(node.val);

            }
                res.add(list);
        }
        System.out.println(res);

return res;
    }

    /*Maximum Depth of Binary Tree
    https://leetcode.com/problems/maximum-depth-of-binary-tree/description/?utm_source=chatgpt.com
    Input: root = [3,9,20,null,null,15,7]
    Output: 3 */
    public int maxDepth(TreeNode root) {

        if(root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return  1 + Math.max(left,right);

    }

    /*Diameter of Binary Tree
    https://leetcode.com/problems/diameter-of-binary-tree/description/?utm_source=chatgpt.com
    Input: root = [1,2,3,4,5]
    Output: 3
    Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3]. */
    int dia = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return dia;
    }

    private int height(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        dia = Math.max(dia,left+right);

        return 1 + Math.max(left,right);
    }
    /* Lowest Common Ancestor of a Binary Tree
    https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/?utm_source=chatgpt.com
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
    Output: 3
    Explanation: The LCA of nodes 5 and 1 is 3. */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
     if(root == null) {
         return root;
     }

     if(p == root || q == root) {
        return root;
     }
     TreeNode left = lowestCommonAncestor(root.left,p,q);
     TreeNode right = lowestCommonAncestor(root.right,p,q);

     if(left == null) {
         return right;
     } else  if(right == null) {
         return left;
     }

      return root;
    }

    /*Validate Binary Search Tree
    https://leetcode.com/problems/validate-binary-search-tree/description/?utm_source=chatgpt.com
    Input: root = [2,1,3]
    Output: true */
    public boolean isValidBST(TreeNode root) {

        return validate(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private boolean validate(TreeNode root,int min,int max) {

        if(root == null) {
            return true;
        }

        if(root.val<=min || root.val>=max) {
            return false;
        }
        return validate(root.left,min,root.val) && validate(root.right,root.val,max);
    }
/*Symmetric Tree
    https://leetcode.com/problems/symmetric-tree/description/?utm_source=chatgpt.com
    Input: root = [1,2,2,3,4,4,3]
    Output: true */

    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return false;
        }
      return iSMirror(root.left,root.right);
    }

    private boolean iSMirror(TreeNode n1,TreeNode n2) {
        if(n1 == null && n2 == null) {
            return true;
        }
        if(n1 == null || n2 == null) {
            return false;
        }
        if(n1.val!= n2.val) {
            return false;
        }
        return iSMirror(n1.left,n2.right) && iSMirror(n1.right,n2.left);
    }

    /*Path Sum
    https://leetcode.com/problems/path-sum/description/?utm_source=chatgpt.com
    Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
    Output: true
    Explanation: The root-to-leaf path with the target sum is shown. */

    public boolean hasPathSum(TreeNode root, int targetSum) {

        if(root == null) {
            return false;
        }
        targetSum = targetSum - root.val;
        if(root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        return hasPathSum(root.left,targetSum) || hasPathSum(root.right,targetSum);

    }

}
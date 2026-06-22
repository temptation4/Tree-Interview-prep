import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*Serialize/Deserialize tree
    https://leetcode.com/problems/serialize-and-deserialize-binary-tree/?utm_source=chatgpt.com
    Input: root = [1,2,3,null,null,4,5]
    Output: [1,2,3,null,null,4,5] */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root,sb);
            return sb.toString();
        }

        private void serializeHelper(TreeNode root ,StringBuilder sb) {
            if(root == null) {
                sb.append("null,");
                return;
            }
            int value = root.val;
            sb.append(value).append(",");
            serializeHelper(root.left,sb);
            serializeHelper(root.right,sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));

              return deserializehelper(queue);
        }

        private TreeNode deserializehelper( Queue<String> queue) {
         String val = queue.poll();
         if(val.equals("null")) {
             return null;
         }
         TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = deserializehelper(queue);
            node.right = deserializehelper(queue);
            return node;
        }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Codec codec = new Codec();

        String serialized = codec.serialize(root);
        System.out.println("Serialized: " + serialized);

        TreeNode deserializedRoot = codec.deserialize(serialized);
        printInorder(deserializedRoot);
    }

    static void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }

        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }
    }

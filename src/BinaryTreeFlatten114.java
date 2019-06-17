/**
 * Description:For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * Solution:
 *
 * @author peter
 * @date 2019/6/17.
 */
public class BinaryTreeFlatten114 {

    public void flatten(TreeNode root) {
        helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            TreeNode right = helper(root.right);
            root.right = helper(root.left);
            root.left = null;
            TreeNode tail = root.right;
            while (tail.right != null) {
                tail = tail.right;
            }
            tail.right = right;
        } else if (root.right != null) {
            root.right = helper(root.right);
        }
        return root;
    }
}

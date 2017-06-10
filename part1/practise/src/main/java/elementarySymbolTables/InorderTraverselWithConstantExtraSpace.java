/**
 * @file   InorderTraverselWithConstantExtraSpace.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Thu Jun  8 15:20:20 2017
 *
 * @brief
 Design an algorithm to perform an inorder traversal of a binary search tree using only a constant amount of extra space.
 *
 *
 */

// travelsal use rank


// Hint: you may modify the BST during the traversal provided you restore it upon completion.


private void inorderMorris(TreeNode root, ArrayList<Integer> values) {
    TreeNode cur = root;

    while (cur != null) {
        if (cur.left != null) {
            TreeNode pre = cur.left;
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            if (pre.right == null) { // set right to successor
                pre.right = cur;
                cur = cur.left;
            } else { // visit and revert the change
                pre.right = null;
                values.add(cur.val);
                cur = cur.right;
            }
        } else { // visit and move to successor
            values.add(cur.val);
            cur = cur.right;
        }
    }
}

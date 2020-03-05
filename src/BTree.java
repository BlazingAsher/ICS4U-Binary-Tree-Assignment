/*
 * File: BTree.java
 * Author: David Hui
 * Description: Allows a user to add to, delete from, find the height of, find the number of leaves within, display, check ancestry, check depth of, and check the balanced of a Binary Search Tree
 */
class BTree{
    private BNode root; // root of the tree

    // constants
    public static final int IN = 997;
    public static final int PRE = 998;
    public static final int POST = 999;

    public BTree(){
        root = null;
    }

    /**
     * Gets the root of the BTree
     * @return the root of the BTree
     */
    public BNode getRoot(){return root;}

    /**
     * Adds an integer to the BTree
     * @param n the integer to add to the BTree
     */
    public void add(int n){
        BNode tmp = new BNode(n);
        if(root==null){ // If there is no root, set the node as root
            root = tmp;
        }
        else{
            add(root, tmp);
        }
    }

    /**
     * Adds a node to a branch of the BTree
     * @param branch the branch to add to node to
     * @param tmp the node to add
     */
    private void add(BNode branch, BNode tmp){
        // null pointer check
        if(tmp == null || branch == null){
            return;
        }
        if(tmp.getVal() > branch.getVal()){ // Add to the right of the node
            if(branch.getRight()==null){ // If there's an opening, add the node here
                branch.setRight(tmp);
                tmp.setParent(branch);
            }
            else{ // Keep going down
                add(branch.getRight(),tmp);
            }
        }
        else if(tmp.getVal() < branch.getVal()){ // Add to the left of the node
            if(branch.getLeft()==null){ // If there's an opening, add the node here
                branch.setLeft(tmp);
                tmp.setParent(branch);
            }
            else{ // Keep going down
                add(branch.getLeft(), tmp);
            }
        }
    }

    /**
     * Adds a BTree to the BTree
     * @param c the BTree to add
     */
    public void add(BTree c){
        if(root == null){ // If the current tree is empty, just set the given one as root
            root = c.getRoot();
        }
        else{ // Dump the tree into the current one
            addAllNodes(c.getRoot());
        }
    }

    /**
     * Adds the given branch and its descendants to the BTree
     * @param branch the branch to add to the BTree
     */
    private void addAllNodes(BNode branch){
        // null pointer check
        if(branch != null){
            add(branch.getVal());
            addAllNodes(branch.getLeft());
            addAllNodes(branch.getRight());
        }
    }

    /**
     * Replaces a given node with another
     * @param branch the node to replace
     * @param newChild the node to replace branch with
     */
    private void replaceChild(BNode branch, BNode newChild){
        // null pointer check
        if(branch == null){
            return;
        }

        // get the parent of the branch
        BNode parent = branch.getParent();

        if(parent == null){ // if the branch is the root, just set it to the newChild
            root = newChild;
        }
        else{
            if(branch.getVal() > parent.getVal()){ // n is on right branch of parent
                parent.setRight(newChild);
            }
            else { // n is on left branch of parent
                parent.setLeft(newChild);
            }
        }

        // update the parent pointer of the child
        if(newChild != null){
            newChild.setParent(parent);
        }

    }

    /**
     * Deletes a branch from the BTree
     * @param branch the branch to delete
     */
    private void delete(BNode branch){
        // null pointer check
        if(branch != null){

            if(branch.getLeft() == null && branch.getRight() == null){ // It's a leaf, just delete it
                replaceChild(branch, null);
            }
            else if(branch.getLeft() != null && branch.getRight() != null){ // Two branches
                // Locate the in-order successor of the node
                BNode s = null;
                BNode c = branch.getRight();

                while(c != null){
                    s = c;
                    c = c.getLeft();
                }

                // replace the node with its right child
                replaceChild(s, s.getRight());

                // set the value of the node to delete to the value of the in-order successor
                branch.setVal(s.getVal());
            }
            else { // One branch
                // shift the branch up by one
                if(branch.getLeft() != null){
                    replaceChild(branch, branch.getLeft());
                }
                else {
                    replaceChild(branch, branch.getRight());
                }
            }

        }
    }

    /**
     * Deletes a value from the tree
     * @param n the value to delete
     */
    public void delete(int n){
        // find the branch and delete it
        BNode loc = find(root, n);
        delete(loc);
    }

    /**
     * Gets the depth of a node
     * @param n the node's value
     * @return the depth of the node
     */
    public int depth(int n){
        return depth(n, root, 1);
    }

    /**
     * Gets the depth of a node
     * @param n the node's value
     * @param o the current node
     * @param d the depth counter
     * @return the depth of the node with value n from o
     */
    public int depth(int n, BNode o, int d){
        if(o == null){
            //System.out.println(d);

            return -1;
        }
        if(n == o.getVal()){
            return d;
        }
        else if(n > o.getVal()){
            //System.out.println("right");
            //System.out.println(o.getVal());
            return depth(n, o.getRight(), d+1);
        }
        else {
            //System.out.println("left");

            return depth(n, o.getLeft(), d+1);
        }
    }

    /**
     * Gets the number of leaves in the BTree
     * @return the number of leaves in the BTree
     */
    public int countLeaves(){return countLeaves(root);}

    /**
     * Gets the number of leaves in the branch
     * @param branch the branch
     * @return the number of leaves in the branch
     */
    public int countLeaves(BNode branch) {
        if(branch == null){ // there are no leaves if there is no branch
            return 0;
        }
        // left and right pointers are null, it's a leaf
        if(branch.getLeft() == null && branch.getRight() == null){
            return 1;
        }

        return countLeaves(branch.getLeft()) + countLeaves(branch.getRight());
    }

    /**
     * Gets the height of the BTree
     * @return the height of the BTree
     */
    public int height(){return height(root, 1);}

    /**
     * Gets the height of the branch
     * @param branch the branch
     * @param h the height counter
     * @return the height of the branch
     */
    public int height(BNode branch, int h){
        if(branch == null){ // empty branch
            return h-1;
        }

        // can no longer go down further
        if(branch.getLeft() == null && branch.getRight() == null){
            return h;
        }

        return Math.max(height(branch.getLeft(), h+1), height(branch.getRight(), h+1));
    }

    /**
     * Gets the minimum height (height of the first node without two children) of the BTree
     * @return the minimum height of the BTree
     */
    public int minHeight(){return minHeight(root, 1);}

    /**
     * Gets the minimum height (height of the first node without two children) of the branch
     * @param branch the branch
     * @param h the height counter
     * @return the minimum height of the BTree
     */
    public int minHeight(BNode branch, int h){
        if(branch == null){ // empty branch
            return h - 1;
        }
        if(branch.getLeft() == null && branch.getRight() == null){ // can no longer do down further
            return h;
        }

        return Math.min(minHeight(branch.getLeft(), h+1), minHeight(branch.getRight(), h+1));
    }

    /**
     * Finds the BNode with value n
     * @param branch the branch
     * @param n the value that is being searched for
     * @return the BNode with value n
     */
    public BNode find(BNode branch, int n){
        if(branch == null || branch.getVal() == n){ // if the branch is empty or we found it
            return branch;
        }
        else if(branch.getVal() > n){ // value of branch is larger, go left
            return find(branch.getLeft(), n);
        }
        else { // value of branch is smaller, go right
            return find(branch.getRight(), n);
        }
    }

    /**
     * Returns whether the node with value f is an ancestor of the node with value s
     * @param f the value of the node that is the ancestor
     * @param s the value of the node that is the descendent
     * @return whether the node with value f is an ancestor of the node with value s
     */
    public boolean isAncestor(int f, int s){
        // nodes are not their own ancestors
        if(f == s){
            return false;
        }

        // find the location of the ancestor
        BNode ancestor = find(root, f);

        // whether we can find a node with value s under the ancestor
        return find(ancestor, s) != null;
    }

    /**
     * Returns whether the BTree is balanced
     * @return whether the BTree is balanced
     */
    public boolean isBalanced() {
        // subtract the two heights and see if they are less than or equal to one
        int maxD = height();
        int minD = minHeight();

        // tree is balanced if the difference is 1 or less
        return maxD - minD <= 1;
    }

    /**
     * Returns the representation of the BTree (in-order)
     * @return the representation of the BTree (in-order)
     */
    public String display(){
        return display(BTree.IN);
    }

    /**
     * Returns the representation of the BTree in the specified traversal method
     * @param mode the traversal method
     * @return the representation of the BTree
     */
    public String display(int mode){
        // get the representation from the root
        String ans = display(mode, root);
        if(ans != ""){
            ans = ans.substring(0, ans.length()-2);
        }
        return "{"+ans+"}";
    }

    /**
     * Returns the representation of a branch in the specified traversal method
     * @param mode the traversal method
     * @param branch the branch
     * @return the representation of the branch
     */
    public String display(int mode, BNode branch){
        // null pointer check
        if(branch != null){
            if(mode == BTree.IN){ // in order traversal
                return display(mode, branch.getLeft()) +
                        branch.getVal() + ", " +
                        display(mode, branch.getRight());
            }
            else if(mode == BTree.PRE){ // pre order traversal
                return branch.getVal() + ", " +
                        display(mode, branch.getLeft()) +
                        display(mode, branch.getRight());
            }
            else if(mode == BTree.POST){ // post order traversal
                return display(mode, branch.getLeft()) +
                        display(mode, branch.getRight())  +
                        branch.getVal() + ", ";
            }
        }
        return "";
    }

    @Override
    public String toString(){
        return display();
    }
}
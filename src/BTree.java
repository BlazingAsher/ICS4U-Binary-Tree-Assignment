class BTree{
    private BNode root;
    public static final int IN = 997;
    public static final int PRE = 998;
    public static final int POST = 999;

    public BTree(){
        root = null;
    }

    public BNode getRoot(){return root;}

    public void add(int n){
        BNode tmp = new BNode(n);
        if(root==null){
            root = tmp;
        }
        else{
            add(root, tmp);
        }
    }

    private void add(BNode branch, BNode tmp){
        if(tmp == null || branch == null){
            return;
        }
        if(tmp.getVal() > branch.getVal()){
            if(branch.getRight()==null){
                branch.setRight(tmp);
                tmp.setParent(branch);
            }
            else{
                add(branch.getRight(),tmp);
            }
        }
        else if(tmp.getVal() < branch.getVal()){
            if(branch.getLeft()==null){
                branch.setLeft(tmp);
                tmp.setParent(branch);
            }
            else{
                add(branch.getLeft(), tmp);
            }
        }
    }

    public void add(BTree c){
        if(root == null){
            root = c.getRoot();
        }
        else{
            addAllNodes(c.getRoot());
        }
    }

    private void addAllNodes(BNode branch){
        if(branch != null){
            add(branch.getVal());
            addAllNodes(branch.getLeft());
            addAllNodes(branch.getRight());
        }
    }

    private void replaceChild(BNode branch, BNode newChild){
        if(branch == null){
            return;
        }
        BNode parent = branch.getParent();

        if(parent == null){
            root = newChild;
        }
        else{
            if(branch.getVal() > parent.getVal()){ // n is on right branch
                parent.setRight(newChild);
            }
            else {
                parent.setLeft(newChild);
            }
        }

    }

    private void delete(BNode branch){
        if(branch != null){

            if(branch.getLeft() == null && branch.getRight() == null){ // It's a leaf
                replaceChild(branch, null);
            }
            else if(branch.getLeft() != null && branch.getRight() !=  null){ // Two branches
                BNode s = null;
                BNode c = branch.getRight();

                while(c != null){
                    s = c;
                    c = c.getLeft();
                }

                replaceChild(s, null);

                branch.setVal(s.getVal());
            }
            else { // One branch
                if(branch.getLeft() != null){
                    replaceChild(branch, branch.getLeft());
                }
                else {
                    replaceChild(branch, branch.getRight());
                }
            }

        }
    }

    public void delete(int n){
        BNode loc = find(root, n);
        delete(loc);
    }

    public int depth(int n){
        return depth(n, root, 0);
    }

    public int depth(int n, BNode o, int d){
        if(o == null){
            System.out.println(d);

            return -1;
        }
        if(n == o.getVal()){
            return d;
        }
        else if(n > o.getVal()){
            System.out.println("right");
            System.out.println(o.getVal());
            return depth(n, o.getRight(), d+1);
        }
        else {
            System.out.println("left");

            return depth(n, o.getLeft(), d+1);
        }
    }

    public int countLeaves(){return countLeaves(root);}

    public int countLeaves(BNode branch) {
        if(branch == null){
            return 0;
        }
        if(branch.getLeft() == null && branch.getRight() == null){
            return 1;
        }

        return countLeaves(branch.getLeft()) + countLeaves(branch.getRight());
    }

    public int height(){return height(root, 1);}

    public int height(BNode branch, int h){
        if(branch == null){
            return 0;
        }
        if(branch.getLeft() == null && branch.getRight() == null){
            return h;
        }

        return Math.max(height(branch.getLeft(), h+1), height(branch.getRight(), h+1));
    }

    public int minHeight(){return minHeight(root, 1);}

    public int minHeight(BNode branch, int h){
        if(branch == null){
            return h - 1;
        }
        if(branch.getLeft() == null && branch.getRight() == null){
            return h;
        }

        return Math.min(minHeight(branch.getLeft(), h+1), minHeight(branch.getRight(), h+1));
    }

    public BNode find(BNode branch, int n){
        if(branch == null || branch.getVal() == n){
            return branch;
        }
        else if(branch.getVal() > n){
            return find(branch.getLeft(), n);
        }
        else {
            return find(branch.getRight(), n);
        }
    }

    public boolean isAncestor(int f, int s){
        BNode ancestor = find(root, f);
        System.out.println(ancestor.getVal());
        return find(ancestor, s) != null;
    }

    public boolean isBalanced() {
        int maxD = height();
        int minD = minHeight();

        return maxD - minD <= 1;
    }

    public String display(){
        return display(BTree.IN);
    }

    public String display(int mode){
        String ans = display(mode, root);
        if(ans != ""){
            ans = ans.substring(0, ans.length()-2);
        }
        return "{"+ans+"}";
    }

    public String display(int mode, BNode branch){
        if(branch != null){
            if(mode == BTree.IN){
                return display(mode, branch.getLeft()) +
                        branch.getVal() + ", " +
                        display(mode, branch.getRight());
            }
            else if(mode == BTree.PRE){
                return branch.getVal() + ", " +
                        display(mode, branch.getLeft()) +
                        display(mode, branch.getRight());
            }
            else if(mode == BTree.POST){
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
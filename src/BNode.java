class BNode{
    private int val;
    private BNode left,right,parent;

    public BNode(int n){
        val = n;
        left = right = parent = null;
    }

    public int getVal(){return val;}
    public BNode getLeft(){return left;}
    public BNode getRight(){return right;}
    public BNode getParent(){return parent;}

    public void setRight(BNode r){right=r;}
    public void setLeft(BNode l){left=l;}
    public void setParent(BNode p){parent=p;}
    public void setVal(int v){val=v;}
}
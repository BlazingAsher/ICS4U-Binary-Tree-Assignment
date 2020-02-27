
public class BTest{

    public static void main(String args[]){

		BTree oak = new BTree();
		oak.add(45);
		oak.add(5);
		oak.add(23);
		oak.add(164);
		oak.add(73);
		oak.add(15);
		oak.add(48);
		System.out.println(oak);
		System.out.println(oak.depth(5));

        
    }
}

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
	
	public void add(BNode branch, BNode tmp){
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
				return branch.getVal() + 
					display(mode, branch.getLeft()) + "," + 
				  	display(mode, branch.getRight());
			}
			else if(mode == BTree.POST){
				return display(mode, branch.getLeft()) + 
				   display(mode, branch.getRight()) + "," +
				   branch.getVal();
			}
		}
		return "";
	}
	
	@Override
	public String toString(){
		return display();
	}
}


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
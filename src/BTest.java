public class BTest{

    public static void maian(String args[]){

		BTree oak = new BTree();
		oak.add(45);
		oak.add(41);
//		oak.add(5);
//		oak.add(23);
//		oak.add(164);
//		oak.add(73);
//		oak.add(15);
//		oak.add(48);
////		oak.add(49);
//		oak.add(167);
//		oak.add(165);
//		oak.add(166);


//		oak.add(44);
//		oak.add(46);
		System.out.println(oak.height());
		System.out.println(oak.depth(41));
		System.out.println(oak.isAncestor(45,45));
		oak.delete(45);
		oak.delete(41);
		System.out.println(oak);
//		System.out.println(oak);
//		oak.delete(164);
//		System.out.println(oak);
//		System.out.println(oak.display(BTree.IN));
//		System.out.println(oak.display(BTree.PRE));
//		System.out.println(oak.display(BTree.POST));
//		System.out.println(oak.countLeaves());
//		System.out.println(oak.height());
//		System.out.println(oak.minHeight());
//		System.out.println(oak.isAncestor(73, 48));
//		System.out.println(oak.isBalanced());
////		System.out.println(oak.depth(5));
//		for(int i=0;i<10000;i++){
//			oak.add(i);
//		}
//
//		System.out.println();

        
    }
	public static void main (String[]args){
		BTree maple=new BTree();
		maple.add(45);
		maple.add(23);
		maple.add(15);
		maple.add(27);
		maple.add(39);
		maple.add(35);
		maple.add(37);
		maple.add(164);
		maple.add(73);
		maple.add(48);
		maple.add(170);

		BTree spruce=new BTree();
		spruce.add(69);
		spruce.add(420);
		spruce.add(69);
		spruce.add(50);
		spruce.add(45);
		spruce.add(58);

		System.out.println(maple.depth(164));
		System.out.println(maple.depth(102));
		System.out.println(spruce.depth(69));
		System.out.println(spruce.depth(45));

		System.out.println(maple.countLeaves());
		System.out.println(spruce.countLeaves());

		System.out.println(maple.height());
		System.out.println(spruce.height());

		System.out.println(maple.isAncestor(23,37));
		System.out.println(maple.isAncestor(103, 37));
		System.out.println(maple.isAncestor(23,103));
		System.out.println(maple.isAncestor(12,93));
		System.out.println(spruce.isAncestor(69,69));

		System.out.println(maple.isBalanced());
		maple.delete(27);
		System.out.println(maple.isBalanced());

		System.out.println(spruce.isBalanced());
		spruce.delete(69);
		System.out.println(spruce.isBalanced());
		spruce.add(69);
		System.out.println(spruce.isBalanced());

		System.out.println(maple.display());
		System.out.println(spruce.display());
		spruce.add(maple);
		System.out.println(spruce.display());
	}
}
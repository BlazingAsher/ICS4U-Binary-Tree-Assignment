public class BTest{

    public static void main(String args[]){

		BTree oak = new BTree();
		oak.add(45);
//		oak.add(5);
//		oak.add(23);
//		oak.add(164);
//		oak.add(73);
//		oak.add(15);
//		oak.add(48);
//		oak.add(167);


		oak.add(44);
//		oak.add(46);

		System.out.println(oak);
		oak.delete(45);
		System.out.println(oak);
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
}
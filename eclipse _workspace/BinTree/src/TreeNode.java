/**
 * TreeNode class, members are Comparable type
 * @author zhouj
 *
 */
public class  TreeNode {
	public Comparable value;
	public int count; //count to track duplicated element
	public TreeNode left; //left subtree
	public TreeNode right; //right subtree
	/**
	 * Constructor
	 * @param element
	 */
	public TreeNode(Comparable element) {
		this.value = element;	
		this.count=1;
		this.left = null;
		this.right = null;
	}
}
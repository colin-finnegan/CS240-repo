package pas.huffman;

/**
 * HuffInternalNode class.
 */
public class HuffInternalNode extends HuffBaseNode {

  private HuffBaseNode left;
  private HuffBaseNode right;

  /**
   * HuffInternalNode class.
   *
   * @param left left child
   * @param right right child
   * @param weight weight of node
   */
  public HuffInternalNode(HuffBaseNode left, HuffBaseNode right, int weight) {
    super(weight);
    this.left = left;
    this.right = right;
  }

  public HuffBaseNode getLeft() {
    return left;
  }

  public HuffBaseNode getRight() {
    return right;
  }

}

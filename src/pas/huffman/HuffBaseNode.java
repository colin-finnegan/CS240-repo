package pas.huffman;

/**
 * HuffBaseNode abstract class.
 */
public abstract class HuffBaseNode implements Comparable<HuffBaseNode> {

  private int weight;

  public HuffBaseNode(int weight) {
    this.weight = weight;
  }

  public int getWeight() {
    return weight;
  }

  @Override
  public int compareTo(HuffBaseNode other) {
    if (getWeight() < other.getWeight()) {
      return -1;
    } else if (getWeight() == other.getWeight()) {
      if (this instanceof HuffLeafNode && other instanceof HuffLeafNode) {
        return Byte.compare(((HuffLeafNode) this).getSymbol(), ((HuffLeafNode) other).getSymbol());
      }
      return 0;
    } else {
      return 1;
    }

  }

}

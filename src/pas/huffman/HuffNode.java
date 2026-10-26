package pas.huffman;

/**
 * HuffNode Class.
 */
public class HuffNode implements Comparable<HuffNode> {
  Byte value;
  int frequency;
  HuffNode left;
  HuffNode right;

  HuffNode(Byte value, int frequency) {
    this.value = value;
    this.frequency = frequency;
  }

  HuffNode(Byte value, int frequency, HuffNode left, HuffNode right) {
    this.value = value;
    this.frequency = frequency;
    this.left = left;
    this.right = right;
  }

  @Override
  public int compareTo(HuffNode other) {
    return Integer.compare(this.frequency, other.frequency);
  }
}

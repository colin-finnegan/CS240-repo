package pas.huffman;

/**
 * HuffLeafNode class.
 */
public class HuffLeafNode extends HuffBaseNode {

  private byte symbol;

  public HuffLeafNode(byte symbol, int weight) {
    super(weight);
    this.symbol = symbol;
  }

  public byte getSymbol() {
    return symbol;
  }

}

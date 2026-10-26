package pas.huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * HuffTree class.
 */
public class HuffTree {

  private HuffBaseNode root;
  private HashMap<Byte, Integer> frequencies;
  private HashMap<Byte, BitSequence> codes; // Maps from bytes to their Huffman codes.

  /**
   * Creates HuffTree object from file parameter.
   *
   * @param inFile file to read from
   * @throws IOException if file cant be read
   */
  public HuffTree(File inFile) throws IOException {
    frequencies = new HashMap<>();
    try (FileInputStream fis = new FileInputStream(inFile)) {
      int b;
      while ((b = fis.read()) != -1) {
        byte byteValue = (byte) b;
        frequencies.put(byteValue, frequencies.getOrDefault(byteValue, 0) + 1);
      }
    }
    this.root = buildTree(frequencies);
    this.codes = new HashMap<>();
  }

  /**
   * Creates HuffTree from HashMap.
   *
   *
   * @param frequencies hashmap
   */
  public HuffTree(HashMap<Byte, Integer> frequencies) {
    this.frequencies = frequencies;
    this.root = buildTree(frequencies);
    this.codes = new HashMap<>();
  }

  public HashMap<Byte, Integer> getFrequencies() {
    return new HashMap<>(frequencies);
  }

  /**
   * Encodes file into BitSequence.
   *
   * @param inFile file to encode
   * @return BitSequence
   * @throws IOException If File cant be read
   * @throws FileNotFoundException If file cant be found
   */
  public BitSequence encode(File inFile) throws IOException, FileNotFoundException {
    BitSequence encodedData = new BitSequence();
    try (FileInputStream fis = new FileInputStream(inFile)) {
      int b;
      while ((b = fis.read()) != -1) {
        byte byteValue = (byte) b;
        BitSequence code = codes.get(byteValue); // Get the Huffman code as a BitSequence
        // Append each bit of the BitSequence to encodedData
        for (int i = 0; i < code.toString().length(); i++) {
          char bit = code.toString().charAt(i);
          encodedData.appendBit(bit == '0' ? 0 : 1); // Append 0 or 1 based on the bit
        }
      }
    }
    return encodedData;
  }

  /**
   * Decodes BitSequence into original file.
   *
   * @param encoding BitSequence to decode
   * @param outFile file to write to
   * @throws IOException if file cant be written to
   * @throws FileNotFoundException if file cant be found
   */
  public void decode(BitSequence encoding, File outFile) throws IOException, FileNotFoundException {
    try (FileOutputStream fos = new FileOutputStream(outFile)) {
      HuffBaseNode current = root;
      String bits = encoding.toString(); // Assuming BitSequence has a toString() method
      for (int i = 0; i < bits.length(); i++) {
        if (bits.charAt(i) == '0') {
          current = ((HuffInternalNode) current).getLeft();
        } else {
          current = ((HuffInternalNode) current).getRight();
        }

        if (current instanceof HuffLeafNode) {
          byte symbol = ((HuffLeafNode) current).getSymbol();
          fos.write(symbol);
          current = root; // Reset to the root of the tree
        }
      }
    }
  }

  /**
   * Generates huffman codes recursively.
   *
   * @param node current node
   * @param code huffman code being constructed
   */
  public void generateEncodingMap(HuffBaseNode node, BitSequence code) {
    if (node instanceof HuffLeafNode) {
      HuffLeafNode leaf = (HuffLeafNode) node;
      codes.put(leaf.getSymbol(), code); // Assign the BitSequence code to the leaf symbol
    } else if (node instanceof HuffInternalNode) {
      HuffInternalNode internal = (HuffInternalNode) node;

      // Append "0" and "1" to the BitSequence, creating new BitSequences
      BitSequence leftCode = new BitSequence(code); // Copy the current code
      leftCode.appendBit(0);  // Left child gets "0"
      generateEncodingMap(internal.getLeft(), leftCode);  // Recurse on left

      BitSequence rightCode = new BitSequence(code); // Copy the current code
      rightCode.appendBit(1);  // Right child gets "1"
      generateEncodingMap(internal.getRight(), rightCode);  // Recurse on right
    }
  }

  private HuffBaseNode buildTree(HashMap<Byte, Integer> frequencies) {
    HuffBaseNode tmp1 = null;
    HuffBaseNode tmp2 = null;
    HuffBaseNode tmp3 = null;

    PriorityQueue<HuffBaseNode> pq = new PriorityQueue<>();

    for (Byte symbol : frequencies.keySet()) {
      pq.add(new HuffLeafNode(symbol, frequencies.get(symbol)));
    }

    while (pq.size() > 1) { // While two items left
      tmp1 = pq.poll();
      tmp2 = pq.poll();
      tmp3 = new HuffInternalNode(tmp1, tmp2, tmp1.getWeight() + tmp2.getWeight());
      pq.add(tmp3); // Return new tree to heap
    }
    return tmp3; // Return the tree
  }

  public HuffBaseNode getRoot() {
    return root;
  }

}

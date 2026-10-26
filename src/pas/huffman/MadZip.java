package pas.huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


/**
 * MadZip class.
 *
 */
public class MadZip {

  /**
   * Compresses file using Huffman codind and writes data to a file.
   *
   * @param source file to compress
   * @param target file to write to
   * @throws IOException if there are any errors compressing or writing
   */
  public static void zip(File source, File target) throws IOException {
    HuffTree huffTree = new HuffTree(source);
    HashMap<Byte, Integer> frequencies = huffTree.getFrequencies();
    huffTree.generateEncodingMap(huffTree.getRoot(), new BitSequence());
    BitSequence encodedData = huffTree.encode(source);
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(target))) {
      oos.writeObject(frequencies);
      oos.writeObject(encodedData.toString());
    }
  }

  /**
   * Decompresses a file thats been compressed using Huffman coding and writes to a file.
   *
   * @param source file with compressed data
   * @param target file to send decompressed data
   * @throws IOException if theres error reading or writing to file
   * @throws ClassNotFoundException if class cant be found
   */
  public static void unzip(File source, File target) throws IOException, ClassNotFoundException {
    HashMap<Byte, Integer> frequencies;
    BitSequence encodedData;
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(source))) {
      frequencies = (HashMap<Byte, Integer>) ois.readObject();
      encodedData = (BitSequence) ois.readObject();
    }
    HuffTree huffTree = new HuffTree(frequencies);
    BitSequence encodedBits = new BitSequence(encodedData);
    huffTree.decode(encodedBits, target);
  }
}

package labs.recursion;

import java.util.LinkedList;
import java.util.List;

public class Box<E> {
  String colorname;
  List<Box<E>> boxes;

  public Box(String colorname, List<Box<E>> boxes) {
    this.colorname = colorname;
    this.boxes = boxes;
  }

  public LinkedList<E> search(String color) {
    LinkedList<E> listy = new LinkedList<>();
    if (boxes.contains(color)) {
      return listy;
    }
    for (Box b : boxes) {
      if (b.colorname.equals(color)) {
        listy.add(0, color);
      }
    }
    return null;
  }

  public static void main(String[] args) {
    Box pink = new Box("pink", List.of());
    Box orange = new Box("orange", List.of());
    Box blue = new Box("blue", List.of());
    Box mauve = new Box("mauve", List.of());
    Box cyan = new Box("cyan", List.of());
    Box purple = new Box("purple", List.of(mauve, cyan));
    Box yellow = new Box("yellow", List.of(pink, orange, blue));
    Box red = new Box("red", List.of(yellow, purple));

    System.out.println(red.search("orange"));
  }

}

package labs.expression;

import java.util.Scanner;

/**
 * Class for parsing strings that encode mathematical expressions in prefix
 * format.
 *
 * @author ??
 * @version ??
 *
 */
public class PrefixParser {

  /**
   * This method takes a string encoding a prefix expression and returns the
   * corresponding expression tree.
   *
   * For example, the string "* + 4.0 1.0 2.0", will result in the tree:
   *
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   */
  public static ExpressionNode parseExpression(String expression) {

    // THE CODE BELOW JUST ILLUSTRATES HOW YOU CAN CHECK THE TOKEN TYPES. YOUR
    // IMPLEMENATION SHOULD BE RECURSIVE.

    Scanner tokens = new Scanner(expression);

    while (tokens.hasNext()) {
      if (tokens.hasNextDouble()) {
        double operand = tokens.nextDouble();
        System.out.println("The token " + operand + " is an operand!");
      } else {
        Operator op = Operator.parseOperator(tokens.next());
        System.out.println("The token " + op.opString() + " is an operator!");
      }
    }
    tokens.close();
    return null;
  }




}

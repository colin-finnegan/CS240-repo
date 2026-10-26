package labs.expression;

/**
 * Simple demo of expression trees.
 *
 * @author CS 240 Instructors
 * @version 3/202024
 */
public class ExpressionDriver {

  /**
   * Execute the test methods.
   */
  public static void main(String[] args) {
    testExpressionTree();
    testPrefixParser();
  }

  /**
   * Build a simple expression tree and test its operations.
   */
  public static void testExpressionTree() {
    // Build the expression ((7.0 * 2.0) - (8.0 / 2.0))

    OperandNode seven = new OperandNode(7.0);
    OperandNode two = new OperandNode(2.0);
    OperandNode eight = new OperandNode(8.0);

    OperatorNode left = new OperatorNode(Operator.MULTIPLY, seven, two);
    OperatorNode right = new OperatorNode(Operator.DIVIDE, eight, two);

    ExpressionNode tree = new OperatorNode(Operator.SUBTRACT, left, right);

    System.out.println("Expression Evaluates To: " + tree.evaluate());
    System.out.println("Infix Representation:    " + tree.infixString());
    System.out.println("Prefix Representation:   " + tree.prefixString());
    System.out.println("Postfix Representation:  " + tree.postfixString());
  }

  /**
   * Test the prefix parser on a sample input.
   */
  public static void testPrefixParser() {
    ExpressionNode tree = PrefixParser.parseExpression("- * 7.0 2.0 / 8.0 2.0");

    System.out.println("Expression Evaluates To: " + tree.evaluate());
    System.out.println("Infix Representation:    " + tree.infixString());
    System.out.println("Prefix Representation:   " + tree.prefixString());
    System.out.println("Postfix Representation:  " + tree.postfixString());
  }

}

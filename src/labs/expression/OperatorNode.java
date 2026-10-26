package labs.expression;

/**
 * Operator nodes are the internal nodes of a binary expression tree.
 *
 * @author ???
 * @version ???
 */
public class OperatorNode extends ExpressionNode {

  private final Operator op;
  private final ExpressionNode left;
  private final ExpressionNode right;


  public OperatorNode(Operator op, ExpressionNode left, ExpressionNode right) {
    this.op = op;
    this.left = left;
    this.right = right;
  }

  public ExpressionNode left() {
    return left;
  }

  public ExpressionNode right() {
    return right;
  }

  /**
   * Evaluate the expression rooted at this node and return the result.
   */
  @Override
  public double evaluate() {

    // Evaluate the expression rooted at this subtree. You will need a switch or conditional
    // statement based on the value of op.
    switch (op) {
      case ADD:
        return left.evaluate() + right.evaluate();
      case SUBTRACT:
        return left.evaluate() - right.evaluate();
      case MULTIPLY:
        return left.evaluate() * right.evaluate();
      case DIVIDE:
        return left.evaluate() / right.evaluate();
      default:
        return 0.0;
    }

  }

  @Override
  public String postfixString() {
    return left.postfixString() + " " + right.postfixString() + " " + op.opString();
  }

  @Override
  public String prefixString() {
    return op.opString() + " " + left.prefixString() + " " + right.prefixString();
  }

  @Override
  public String infixString() {
    return "(" + left.infixString() + " " + op.opString() + right.infixString() + ")";
  }


}

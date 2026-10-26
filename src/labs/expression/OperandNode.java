package labs.expression;

/**
 * Operand nodes form the leaves of expression trees. They store doubles.
 *
 * @author Nathan Sprague
 * @version 3/2024
 *
 */
public class OperandNode extends ExpressionNode {

  private double number;

  public OperandNode(double number) {
    this.number = number;
  }

  @Override
  public double evaluate() {
    return number;
  }

  @Override
  public String toString() {
    return "" + number;
  }

  @Override
  public String postfixString() {
    return toString();
  }

  @Override
  public String prefixString() {
    return toString();
  }

  @Override
  public String infixString() {
    return toString();
  }

}

package labs.expression;

/**
 * Abstract base class for nodes in a binary tree representing an arithmetic
 * expression.
 *
 * @author CS 240 Instructors
 * @version 3/2024
 */
public abstract class ExpressionNode {


  // Abstract methods below.

  /**
   * Return the value of the expression tree rooted at this node.
   */
  abstract public double evaluate();

  /**
   * Return a string containing the postfix representation of the expression tree
   * rooted at this node.
   *
   * For example, the following tree:
   *
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   *
   * would evaluate to "4.0 1.0 + 2.0 *"
   */

  public abstract String postfixString();

  /**
   * Return a string containing the postfix representation of the expression tree
   * rooted at this node.
   *
   * For example, the following tree:
   *
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   *
   *
   * would evaluate to "* + 4.0 1.0 2.0"
   */
  public abstract String prefixString();

  /**
   * Return a string containing the postfix representation of the expression tree
   * rooted at this node.
   *
   * For example, the following tree:
   *
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   *
   * would evaluate to ((4.0 + 1.0) * 2.0)"
   */
  public abstract String infixString();


}

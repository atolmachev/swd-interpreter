import java.util.Map;

public class SumExpression implements Expression {
  private final Expression left;
  private final Expression right;

  public SumExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public int evaluate(Map<String, Integer> context) {
    return left.evaluate(context) + right.evaluate(context);
  }

  @Override
  public <T> T accept(Visitor<T> visitor) {
    visitor.onExpression(this);
    left.accept(visitor);
    right.accept(visitor);
    return visitor.get();
  }
}

import java.util.Map;

public class AssignmentStatement implements Statement {
  public final String var;
  public final Expression expression;

  public AssignmentStatement(String var, Expression expression) {
    this.var = var;
    this.expression = expression;
  }

  @Override
  public Map<String, Integer> evaluate(Map<String, Integer> context) {
    context.put(var, expression.evaluate(context));
    return context;
  }

  @Override
  public <T> T accept(Visitor<T> visitor) {
    visitor.onStatement(this);
    expression.accept(visitor);
    return visitor.get();
  }
}

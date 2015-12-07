import java.util.Map;

public class AssignmentStatement implements Statement {
  String var;
  Expression expression;

  public AssignmentStatement(String var, Expression expression) {
    this.var = var;
    this.expression = expression;
  }

  @Override
  public Map<String, Integer> evaluate(Map<String, Integer> context) {
    context.put(var, expression.evaluate(context));
    return context;
  }
}

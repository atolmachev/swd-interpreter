import java.util.Map;

public class VariableExpression implements Expression {

  private final String name;

  private VariableExpression(String name) {
    this.name = name;
  }

  public static VariableExpression of(String s) {
    if (!s.matches("\\w+")) {
      throw new ParseException("Invalid variable name: " + s);
    }
    return new VariableExpression(s);
  }

  @Override
  public int evaluate() {
    return 0;
  }

  @Override
  public int evaluate(Map<String, Integer> context) {
    return context.get(name);
  }
}

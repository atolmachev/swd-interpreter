import java.util.Map;

import static java.lang.String.format;

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
  public int evaluate(Map<String, Integer> context) {
    Integer integer = context.get(name);
    if (integer == null) throw new ExecutionException(format("Variable %s not found", name));
    return integer;
  }
}

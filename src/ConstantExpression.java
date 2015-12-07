import java.util.Map;

public class ConstantExpression implements Expression {
  private final int value;

  public ConstantExpression(int value) {
    this.value = value;
  }

  @Override
  public int evaluate(Map<String, Integer> context) {
    return value;
  }

  public static ConstantExpression of(String s) {
    try {
      return new ConstantExpression(Integer.valueOf(s));
    } catch (NumberFormatException e) {
      throw new ParseException("Wrong number format for: " + s, e);
    }
  }
}

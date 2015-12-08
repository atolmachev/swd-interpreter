import java.util.Map;

import static java.util.Collections.emptyMap;

public interface Expression {

  default int evaluate() {
    return evaluate(emptyMap());
  }

  int evaluate(Map<String, Integer> context);

  default <T> T accept(Visitor<T> visitor) {
    visitor.onExpression(this);
    return visitor.get();
  }
}

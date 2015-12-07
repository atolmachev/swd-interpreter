import java.util.Map;

public interface Expression {

  int evaluate();

  default int evaluate(Map<String, Integer> environment) {
     return evaluate();
  }
}

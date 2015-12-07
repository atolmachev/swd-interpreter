import java.util.HashMap;
import java.util.Map;

public interface Statement {
  Map<String, Integer> evaluate(Map<String, Integer> context);

  default Map<String, Integer> evaluate() {
    return evaluate(new HashMap<>());
  }
}

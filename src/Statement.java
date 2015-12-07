import java.util.Map;

public interface Statement {
  Map<String, Integer> evaluate(Map<String, Integer> context);
}

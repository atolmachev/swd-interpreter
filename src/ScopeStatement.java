import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScopeStatement implements Statement {
  List<Statement> statements;

  public ScopeStatement(List<Statement> statements) {
    this.statements = statements;
  }

  @Override
  public Map<String, Integer> evaluate(Map<String, Integer> context) {
    HashMap<String, Integer> scopeContext = new HashMap<>(context);
    for (Statement statement : statements) {
      statement.evaluate(scopeContext);
    }
    return scopeContext;
  }

  @Override
  public <T> T accept(Visitor<T> visitor) {
    visitor.onStatement(this);
    for (Statement statement : statements) {
      statement.accept(visitor);
    }
    return visitor.get();
  }
}

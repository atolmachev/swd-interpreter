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
}

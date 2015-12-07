import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;

public class StatementTest {
  Map<String, Integer> context = new HashMap<>();

  @Test
  public void testAssignment() throws Exception {
    Statement statement = Parser.parse("var x = 0");
    statement.evaluate(context);
    assertEquals(singletonMap("x", 0), context);
  }

  @Test
  public void testScopedAssignment() throws Exception {
    Statement statement = Parser.parse(makeLines(
        "var x = 0",
        "  var x = 10"
    ));
    assertEquals(singletonMap("x", 0), statement.evaluate(context));
  }

  @Test
  public void testScopedAssignmentWithChange() throws Exception {
    Statement statement = Parser.parse(makeLines(
        "var x = 0",
        "  var x = 10",
        "var x = 5"
    ));
    assertEquals(singletonMap("x", 5), statement.evaluate(context));
  }

  private String makeLines(String... s) {
    return String.join(System.lineSeparator(), s);
  }

}

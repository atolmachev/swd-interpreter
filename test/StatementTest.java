import org.junit.Test;

import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;

public class StatementTest {

  @Test
  public void testAssignment() throws Exception {
    Statement statement = Parser.parse("var x = 0");
    assertEquals(singletonMap("x", 0), statement.evaluate());
  }

  @Test
  public void testScopedAssignment() throws Exception {
    Statement statement = Parser.parse(makeLines(
        "var x = 0",
        "  var x = 10"
    ));
    assertEquals(singletonMap("x", 0), statement.evaluate());
  }

  @Test
  public void testScopedAssignmentWithChange() throws Exception {
    Statement statement = Parser.parse(makeLines(
        "var x = 0",
        "  var x = 10",
        "var x = 5"
    ));
    assertEquals(singletonMap("x", 5), statement.evaluate());
  }

  @Test(expected = ExecutionException.class)
  public void testVariableNotFound() throws Exception {
    Parser.parse(makeLines(
        "var x = 0",
        "  var a = 10",
        "var x = a + 5"
    )).evaluate();
  }

  @Test
  public void innerScopeSeesVarsFromOuterScope() throws Exception {
    Parser.parse(makeLines(
        "var x = 0",
        "  var l = x + 10"
    )).evaluate();
  }

  private String makeLines(String... s) {
    return String.join(System.lineSeparator(), s);
  }

}

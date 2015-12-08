import org.junit.Test;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class VisitorsTest {
  @Test
  public void testCollectingAssignments() throws Exception {
    Statement statement = Parser.parse(makeLines(
        "var x = 0",
        "  var x = 10",
        "var x = 5"
    ));

    assertEquals(3, statement.accept(Visitors.countingAssignments()));
  }

  @Test
  public void testListingVariables() throws Exception {
    Statement statement = Parser.parse(makeLines(
        "var x = 0",
        "  var a = 10",
        "var z = a + 5"
    ));

    assertEquals(new HashSet<>(asList("z", "a", "x")), statement.accept(Visitors.listingVariables()));
  }

  @Test
  public void testCountingAddOperators() throws Exception {
    Expression expression = Parser.parseExpression("10 + z + 54 + ((6 + 7) + 9)");
    assertEquals(5, expression.accept(Visitors.countingAddOperators()));
  }

  private String makeLines(String... s) {
    return String.join(System.lineSeparator(), s);
  }
}

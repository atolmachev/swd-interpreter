import org.junit.Test;

import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;

public class ExpressionTest {
  @Test
  public void testEvalOtherConst() throws Exception {
    Expression expression = Parser.parseExpression("4");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testScopedInteger() throws Exception {
    Expression expression = Parser.parseExpression("(42)");
    int actual = expression.evaluate();
    assertEquals(42, actual);
  }

  @Test(expected = ParseException.class)
  public void testInvalidParenthesis() throws Exception {
    Parser.parseExpression("((");
  }

  @Test
  public void testSum() throws Exception {
    Expression expression = Parser.parseExpression("2+2");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testSpacedExpression() throws Exception {
    Expression expression = Parser.parseExpression("(2 + 2)");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testSumWithParenthesis() throws Exception {
    Expression expression = Parser.parseExpression("2+(2 + 2)");
    int actual = expression.evaluate();
    assertEquals(6, actual);
  }

  @Test
  public void testComplicatedExpression() throws Exception {
    Expression expression = Parser.parseExpression("((2)+(2 + 2))");
    int actual = expression.evaluate();
    assertEquals(6, actual);
  }

  @Test
  public void testVariableExpression() throws Exception {
    Parser.parseExpression("foo");
  }

  @Test
  public void testEvaluateInEnvironment() throws Exception {
    Expression expression = Parser.parseExpression("(x + 5)");
    assertEquals(97, expression.evaluate(singletonMap("x", 92)));
  }
}

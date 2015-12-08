import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Parser {
  static Pattern SCOPED_PATTERN = Pattern.compile("(\\s*)(.+)");
  static Pattern ASSIGNMENT_PATTERN = Pattern.compile("var\\s+(\\w+)\\s*=([^=]+)");

  public static Statement parse(String program) {
    String[] split = program.split(System.lineSeparator());
    if (split.length == 1) {
      String line = split[0];
      Matcher assignmentMatcher = ASSIGNMENT_PATTERN.matcher(line);
      if (assignmentMatcher.matches()) {
        return new AssignmentStatement(assignmentMatcher.replaceFirst("$1"), parseExpression(assignmentMatcher.replaceFirst("$2")));
      }
    }
    int minOffset = Arrays.stream(split).map(Parser::offsetLength).min(Integer::compare).get();
    if (minOffset == 0) {
      List<String> scope = new ArrayList<>();
      List<Statement> statements = new ArrayList<>();
      for (String line : split) {
        if (offsetLength(line) > 0) scope.add(line);
        else {
          if (!scope.isEmpty()) {
            statements.add(parse(String.join(System.lineSeparator(), scope)));
            scope.clear();
          }
          statements.add(parse(line));
        }
      }
      if (!scope.isEmpty()) statements.add(parse(String.join(System.lineSeparator(), scope)));
      return new ScopeStatement(statements);
    } else {
      Statement statement = parse(Arrays.stream(split).map(s -> s.substring(minOffset)).collect(Collectors.joining(System.lineSeparator())));
      return new ScopeStatement(asList(statement));
    }
  }

  private static Integer offsetLength(String ln) {

    return SCOPED_PATTERN.matcher(ln).replaceFirst("$1").length();
  }

  public static Expression parseExpression(String s) {
    s = s.trim();
    int outerPlus = findOuterPlus(s);
    if (outerPlus > 0) {
      return new SumExpression(
          parseExpression(s.substring(0, outerPlus)),
          parseExpression(s.substring(outerPlus + 1)));
    }

    if (s.startsWith("(") && s.endsWith(")")) {
      return parseExpression(s.substring(1, s.length() - 1));
    }

    if (Character.isDigit(s.charAt(0))) {
      return ConstantExpression.of(s);
    }

    return VariableExpression.of(s);
  }

  private static int findOuterPlus(String s) {
    int scopeCounter = 0;
    for (int i = 0; i < s.length(); i++) {
      switch (s.charAt(i)) {
        case ')':
          scopeCounter += 1;
          break;
        case '(':
          scopeCounter -= 1;
          break;
        case '+':
          if (scopeCounter == 0) {
            return i;
          }
      }
    }
    return -1;
  }
}

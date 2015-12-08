import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;

public class Visitors {
  static public Visitor countingAssignments() {
    return CollectingVisitor.statementVisitor(0, s -> s instanceof AssignmentStatement ? 1 : 0, Integer::sum);
  }

  static public Visitor listingVariables() {
    return CollectingVisitor.statementVisitor(emptySet(),
        s -> s instanceof AssignmentStatement ? Collections.singleton(((AssignmentStatement) s).var) : emptySet(),
        Visitors::setUnion);
  }

  static public Visitor countingAddOperators() {
    return CollectingVisitor.expressionVisitor(0, e -> e instanceof SumExpression ? 1 : 0, Integer::sum);
  }


  private static Set<String> setUnion(Set<String> set1, Set<String> set2) {
    HashSet<String> set = new HashSet<>();
    set.addAll(set1);
    set.addAll(set2);
    return set;
  }
}

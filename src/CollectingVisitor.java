import java.util.function.BiFunction;
import java.util.function.Function;

class CollectingVisitor<T> implements Visitor<T> {
  Function<Statement, T> statementMapper;
  Function<Expression, T> expressionMapper;
  BiFunction<T, T, T> combiner;
  T result;

  public CollectingVisitor(T zero, Function<Statement, T> statementMapper, Function<Expression, T> expressionMapper, BiFunction<T, T, T> combiner) {
    this.statementMapper = statementMapper;
    this.expressionMapper = expressionMapper;
    this.combiner = combiner;
    this.result = zero;
  }

  static <T> CollectingVisitor<T> statementVisitor(T zero, Function<Statement, T> statementMapper, BiFunction<T, T, T> combiner) {
    return new CollectingVisitor<>(zero, statementMapper, null, combiner);
  }

  static <T> CollectingVisitor<T> expressionVisitor(T zero, Function<Expression, T> expressionMapper, BiFunction<T, T, T> combiner) {
    return new CollectingVisitor<>(zero, null, expressionMapper, combiner);
  }

  @Override
  public void onStatement(Statement statement) {
    if (statementMapper != null) {
      T value = statementMapper.apply(statement);
      if (value != null) result = combiner.apply(result, value);
    }
  }

  @Override
  public void onExpression(Expression expression) {
    if (expressionMapper != null) {
      T value = expressionMapper.apply(expression);
      if (value != null) result = combiner.apply(result, value);
    }
  }

  @Override
  public T get() {
    return result;
  }
}

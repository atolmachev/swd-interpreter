public interface Visitor<T> {
  void onStatement(Statement statement);
  void onExpression(Expression expression);
  T get();
}

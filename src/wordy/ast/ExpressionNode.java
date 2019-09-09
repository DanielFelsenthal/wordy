package wordy.ast;

import wordy.interpreter.EvaluationContext;

public abstract class ExpressionNode extends ASTNode {
    public abstract double evaluate(EvaluationContext context);
}

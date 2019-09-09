package wordy.ast;

import wordy.interpreter.EvaluationContext;

public abstract class StatementNode extends ASTNode {
    public abstract void run(EvaluationContext context);
}

package wordy.ast;

import java.util.Collections;
import java.util.Map;

import wordy.interpreter.EvaluationContext;
import wordy.interpreter.LoopExited;

public final class LoopExitNode extends StatementNode {
    public LoopExitNode() {
    }

    @Override
    public Map<String, ASTNode> getChildren() {
        return Collections.emptyMap();
    }

    @Override
    public void run(EvaluationContext context) {
        context.trace(this);
        throw new LoopExited();
    }

    @Override
    public boolean equals(Object o) {
        return this == o
            || o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "LoopExitNode";
    }
}

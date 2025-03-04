package wordy.ast;

import wordy.interpreter.EvaluationContext;
import wordy.interpreter.LoopExited;

import java.util.Map;
import java.util.Objects;

public class LoopNode extends StatementNode {
    public final StatementNode body;

    public LoopNode(StatementNode body) {
        this.body = body;
    }

    @Override
    public Map<String, ASTNode> getChildren() {
        return Map.of("body", body);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        LoopNode loopNode = (LoopNode) o;
        return body.equals(loopNode.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }

    @Override
    public String toString() {
        return "LoopNode{body=" + body + '}';
    }

    @Override
    public void run(EvaluationContext context) {
        try {
            while (true) {
                this.body.run(context);
            }
        }
        catch (LoopExited le) {
            return;
        }
    }
}

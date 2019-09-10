package wordy.ast;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import wordy.interpreter.EvaluationContext;

public class VariableNode extends ExpressionNode {
    public final String name;

    public VariableNode(String name) {
        this.name = name;
    }

    @Override
    public Map<String, ASTNode> getChildren() {
        return Collections.emptyMap();
    }

    @Override
    public double evaluate(EvaluationContext context) {
        context.trace(this);
        return context.get(name);
    }

    @Override
    public void compile(PrintWriter out) {
        out.print("context.");
        out.print(name);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        VariableNode that = (VariableNode) o;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "VariableNode" + describeAttributes();
    }

    @Override
    protected String describeAttributes() {
        return "(name=\"" + name + "\")";
    }
}

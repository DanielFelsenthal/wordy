package wordy.ast;

import wordy.interpreter.EvaluationContext;

import java.util.Map;
import java.util.Objects;

import static wordy.ast.Utils.orderedMap;

public class BinaryExpressionNode extends ExpressionNode {
    public enum Operator {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION, EXPONENTIATION;
    }

    public final Operator operator;
    public final ExpressionNode lhs, rhs;

    public BinaryExpressionNode(Operator operator, ExpressionNode lhs, ExpressionNode rhs) {
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Map<String, ASTNode> getChildren() {
        return orderedMap(
            "lhs", lhs,
            "rhs", rhs);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        BinaryExpressionNode that = (BinaryExpressionNode) o;
        return this.operator == that.operator
            && this.lhs.equals(that.lhs)
            && this.rhs.equals(that.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, lhs, rhs);
    }

    @Override
    public String toString() {
        return "BinaryExpressionNode{"
            + "operator=" + operator
            + ", lhs=" + lhs
            + ", rhs=" + rhs
            + '}';
    }

    @Override
    protected String describeAttributes() {
        return "(operator=" + operator + ')';
    }

    @Override
    public double evaluate(EvaluationContext context) {
        double returned;
        switch (this.operator) {
            case EXPONENTIATION:
                returned=Math.pow(this.lhs.evaluate(context),this.rhs.evaluate(context));
                break;
            case ADDITION:
                returned=this.lhs.evaluate(context)+this.rhs.evaluate(context);
                break;
            case SUBTRACTION:
                returned=this.lhs.evaluate(context)-this.rhs.evaluate(context);
                break;
            case DIVISION:
                returned=this.lhs.evaluate(context)/this.rhs.evaluate(context);
                break;
            case MULTIPLICATION:
                returned=this.lhs.evaluate(context)*this.rhs.evaluate(context);
                break;
            default:
                throw new UnsupportedOperationException("Not a binary Operator");
        }
        return returned;
    }
}

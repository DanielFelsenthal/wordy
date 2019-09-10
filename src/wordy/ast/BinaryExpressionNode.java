package wordy.ast;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

import wordy.interpreter.EvaluationContext;

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
    public double evaluate(EvaluationContext context) {
        context.trace(this);
        double leftValue = lhs.evaluate(context);
        double rightValue = rhs.evaluate(context);
        switch(operator) {
            case ADDITION:
                return leftValue + rightValue;
            case SUBTRACTION:
                return leftValue - rightValue;
            case MULTIPLICATION:
                return leftValue * rightValue;
            case DIVISION:
                return leftValue / rightValue;
            case EXPONENTIATION:
                return Math.pow(leftValue, rightValue);
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void compile(PrintWriter out) {
        if(operator == Operator.EXPONENTIATION) {
            out.print("Math.pow(");
            lhs.compile(out);
            out.print(",");
            rhs.compile(out);
            out.print(")");
            return;
        }

        out.print("(");
        lhs.compile(out);
        switch(operator) {
            case ADDITION:
                out.print("+");
                break;
            case SUBTRACTION:
                out.print("-");
                break;
            case MULTIPLICATION:
                out.print("*");
                break;
            case DIVISION:
                out.print("/");
                break;
            default:
                throw new UnsupportedOperationException();
        }
        rhs.compile(out);
        out.print(")");
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
}

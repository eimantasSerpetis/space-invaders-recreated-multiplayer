package server;

import java.util.ArrayList;
import java.util.List;

class MulExpression implements Expression {
    private List<Expression> expressionList;

    public MulExpression() {
        expressionList = new ArrayList<>();
    }

    public void addExpression(Expression expression){
        expressionList.add(expression);
    }

    public int interpret(ExpressionContext context) {
        int totalMul = 1;
        for (Expression expr :
                expressionList) {
            totalMul *= expr.interpret(context);
        }
        return totalMul;
    }

}

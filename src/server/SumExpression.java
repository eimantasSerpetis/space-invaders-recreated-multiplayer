package server;

import java.util.ArrayList;
import java.util.List;

class SumExpression implements Expression {
    private List<Expression> expressionList;

    public SumExpression() {
        expressionList = new ArrayList<>();
    }


    public void addExpression(Expression expression) {
        expressionList.add(expression);
    }

    public int interpret(ExpressionContext context) {
        int totalCount = 0;
        for (Expression expr :
                expressionList) {
            totalCount += expr.interpret(context);
        }
        return totalCount;
    }


}

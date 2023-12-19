package server;

import common.EntityType;

class EntityCountExpression implements Expression {
    private EntityType entityType;

    public EntityCountExpression(EntityType entityType) {
        this.entityType = entityType;
    }
    public int interpret(ExpressionContext context) {
        int count = context.getCount(entityType);
        return count;
    }
}

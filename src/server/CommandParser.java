package server;

import common.EntityType;

class CommandParser {
    public Expression parseCommand(String command) {
        String[] parts = command.split("\\s+");
        if(parts.length == 0)
            return null;
        return switch (parts[0]){
            case "sum" -> parseSumCommand(parts);
            case "mul" -> parseMulCommand(parts);
            default -> createCountExpression(parts[0]);
        };
    }


    private Expression parseSumCommand(String[] parts) {
        SumExpression sumExpression = new SumExpression();
        for (int i = 1; i < parts.length; i++) {
            EntityCountExpression expression = createCountExpression(parts[i]);
            if(expression != null)
                sumExpression.addExpression(expression);
        }
        return sumExpression;
    }
    private Expression parseMulCommand(String[] parts) {
        MulExpression mulExpression = new MulExpression();
        for (int i = 1; i < parts.length; i++) {
            EntityCountExpression expression = createCountExpression(parts[i]);
            if(expression != null)
                mulExpression.addExpression(expression);
        }
        return mulExpression;
    }

    private EntityCountExpression createCountExpression(String command){
        return switch(command) {
            case "enemyCount" -> new EntityCountExpression(EntityType.ENEMY);
            case "bulletCount" -> new EntityCountExpression(EntityType.BULLET);
            case "shieldCount" -> new EntityCountExpression(EntityType.SHIELD);
            case "playerCount" -> new EntityCountExpression(EntityType.PLAYER);
            default -> null;
        };
    }

}

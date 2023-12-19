package server;

import common.EntityType;

public class ExpressionContext {
    private GameState gameState;
    public ExpressionContext(GameState gameState){
        this.gameState = gameState;
    }
    public int getCount(EntityType type){
        return switch (type) {
            case ENEMY -> gameState.getEnemyEntities().size();
            case BULLET -> gameState.getBulletEntities().size();
            case SHIELD -> gameState.getShieldFragmentEntities().size();
            case PLAYER -> gameState.getPlayerEntities().size();
        };
    }
}

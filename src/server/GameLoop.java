package server;

import java.util.Stack;

import common.Configuration;

public class GameLoop implements Runnable{
    private static GameLoop instance = null;
    private long lastFrameTime;
    private final GameState state;
    private final double timeStep;
    private volatile boolean gameRunning;
    private Stack<Memento> mementoStack;

    private GameLoop(){
        lastFrameTime = System.currentTimeMillis();
        timeStep = 1000.0 / Configuration.REFRESH_RATE;
        state = new GameState();
        gameRunning = false;
        mementoStack = new Stack<Memento>();
    }

    public static synchronized GameLoop getInstance() {
        if(instance == null){
            instance = new GameLoop();
        }
        return instance;
    }

    private void loop(){
        state.initializeGame();
        while(true){
            if(!gameRunning){
                continue;
            }
            long currentTime = System.currentTimeMillis();
            long deltaTime = currentTime - lastFrameTime;
            if (deltaTime < timeStep) {
                continue;
            }
            state.updateBullets();
            //this should be called according to a timer or smth
            //state.updateEnemies(MoveDirection.LEFT);
            state.checkBulletCollisions();
            lastFrameTime = currentTime;
        }
    }

    public GameState getState() {
        return state;
    }

    public void saveState(){
        mementoStack.push(state.saveToMemento());
    }

    public void restoreState(){
        if(!mementoStack.isEmpty()){
            state.restoreFromMemento(mementoStack.pop());
        }
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    @Override
    public void run() {
        loop();
    }
}

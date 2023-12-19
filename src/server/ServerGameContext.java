package server;

import common.Configuration;
import common.GameContext;
import common.packets.Packet;
import common.packets.ToClient.MessagePacket;
import common.packets.ToServer.*;
import common.packets.builders.MessagePacketBuilder;

// Many of these are left blank because server should never receive these packets from clients
public class ServerGameContext implements GameContext {
    private Server server;
    private GameState gameState;
    private ExpressionContext expressionContext;
    private CommandParser parser;
    public ServerGameContext(Server server, GameState gameState){
        this.server = server;
        this.gameState = gameState;
        expressionContext = new ExpressionContext(gameState);
        parser = new CommandParser();
    }
    @Override
    public void processPacket(Packet packet) {
        switch (packet.getPacketType()){
            case MOVE:
                processMovePacket((MovePacket) packet);
                break;
            case SHOOT:
                processShotPacket((ShootPacket) packet);
                break;
            case MESSAGE:
                processMessagePacket((MessagePacket) packet);
                break;
            case STATE_SAVE:
                processStateSavePacket((StateSavePacket) packet);
                break;
            case STATE_RESTORE:
                processStateRestorePacket((StateRestorePacket) packet);
                break;
        }
    }
    private void processMovePacket(MovePacket packet) {
        gameState.movePlayer(packet.getSenderId(), packet.getMoveDirection());
    }

    private void processShotPacket(ShootPacket packet) {
        gameState.shootFromPlayer(packet.getSenderId());
    }

    private void processStateSavePacket(StateSavePacket packet){
        GameLoop gameLoop = GameLoop.getInstance();
        gameLoop.saveState();
    }

    private void processStateRestorePacket(StateRestorePacket packet){
        GameLoop gameLoop = GameLoop.getInstance();
        gameLoop.restoreState();
    }

    private void processMessagePacket(MessagePacket message){
        if(message.getMessage().length() == 0)
            return;
        ServerPlayer player = server.getPlayer(message.getSenderId());
        if(message.getMessage().charAt(0) == '/'){
            Expression expression = parser.parseCommand(message.getMessage().substring(1));
            if(expression == null){
                server.sendPacketToPlayer(new MessagePacketBuilder()
                        .setSenderId(Configuration.SERVER_ID)
                        .setMessage("Invalid command")
                        .getResult(), player.getId());
                return;
            }
            int result = expression.interpret(expressionContext);
            server.sendPacketToPlayer(new MessagePacketBuilder().setSenderId(Configuration.SERVER_ID).setMessage("Result: " + result).getResult(), player.getId());
            return;
        }
        if(player != null){
            server.broadcastPacket(new MessagePacketBuilder()
                    .setSenderId(message.getSenderId())
                    .setMessage("<" + player.getPlayerName() + "> " + message.getMessage())
                    .getResult());
        }
    }
}

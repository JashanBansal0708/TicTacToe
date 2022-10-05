import java.util.*;

public class Player{
    private final String id;
    private final String name;
    private final State move;
    
    public Player(String name, State move){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.move = move;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public State getMove(){
        return move;
    }
}
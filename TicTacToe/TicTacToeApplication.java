import java.util.*;

public class TicTacToeApplication{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Player> playerList = new ArrayList<>();
        System.out.println("Enter the name of players");
        for(int i = 0; i < 2; i++){
            String input = sc.nextLine();
            String[] s = input.split(" ");
            playerList.add(new Player(s[1], State.valueOf(s[0])));
        }
        Board board = new Board();
        GameService gameService = new GameService(board, playerList);
        gameService.startGame();
        
    }
}
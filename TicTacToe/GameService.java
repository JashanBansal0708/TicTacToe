import java.util.*;

public class GameService{
    private final Board board;
    private final Queue<Player> players;
    
    public GameService(Board board, List<Player> playerList){
        this.board = board;
        this.players = new LinkedList<>();
        players.addAll(playerList);
    }
    
    public void startGame(){
        Scanner sc = new Scanner(System.in);
        while(!board.isBoardFilled()){
            board.print();
            Player player = players.peek();
            System.out.println("Player " + player.getName() + ", It's your turn");
            String input = sc.nextLine();
            String[] s = input.split(" ");
            int i = Integer.parseInt(s[0]);
            int j = Integer.parseInt(s[1]);
            Cell cell = new Cell(i-1, j-1);
            if(board.isCellValid(cell)) {
                players.poll();
                board.fillCell(cell, player.getMove());
                boolean playerWon = checkIfPlayerWon(player.getMove());
                if(playerWon) {
                	board.print();
                    System.out.println(player.getName() + " won the game");
                    break;
                }
                else {
                    players.offer(player);
                }
            }
            else {
                System.out.println("Invalid Move");
            }
        }
        if(board.isBoardFilled()) {
            System.out.println("Game Over");
        }
    }
    
    private boolean checkIfPlayerWon(State move) {
        int size = board.getSize();
        boolean  isMajorDiagonalFilled = true, isMinorDiagonalFilled = true;
        for(int i=0; i<size; i++) {
            boolean isRowFilled = true, isColFilled = true;
            for(int j=0; j<size; j++) {
                if(!board.checkCellState(new Cell(i, j), move)) {
                    isRowFilled = false;
                }
                if(!board.checkCellState(new Cell(j, i), move)) {
                    isColFilled = false;
                }
            }
            if(isRowFilled || isColFilled) {
                return true;
            }
            if(!board.checkCellState(new Cell(i, i), move)) {
                isMajorDiagonalFilled = false;
            }
            if(!board.checkCellState(new Cell(i, size - 1 - i), move)) {
                isMinorDiagonalFilled = false;
            }
        }
        return isMajorDiagonalFilled || isMinorDiagonalFilled;
    }
}
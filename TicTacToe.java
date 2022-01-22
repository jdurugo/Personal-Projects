import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Integer> playerPos = new ArrayList<Integer>();
    static ArrayList<Integer> computerPos = new ArrayList<Integer>();
    public static void main(String[] args){
        char[][] board= {{' ', '|', ' ', '|',' '}, {'-', '+', '-', '+','-'},{' ', '|', ' ', '|',' '},{'-', '+', '-', '+','-'},{' ', '|', ' ', '|',' '}};
        printgameBoard(board);

        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.print("Choose a space (1-9): ");
            int playerspace = sc.nextInt();
            while(playerPos.contains(playerspace) || computerPos.contains(playerspace)){
                System.out.println("Space taken, try again.");
            }
    
            spaces(board, playerspace, "player");
            String result = Winner();
            if(result.length() > 0){
                printgameBoard(board);
                System.out.println(result);
                break;
            } 
            Random rand = new Random();
            int cpuspace = rand.nextInt(9)+1;
            while(playerPos.contains(cpuspace) || computerPos.contains(cpuspace)){
                cpuspace = rand.nextInt(9)+1;
            }
            spaces(board, cpuspace, "computer");
            printgameBoard(board);
            result = Winner();
            if(result.length() > 0){
                printgameBoard(board);
                System.out.println(result);
                break;
            }
        }
    }
    public static void printgameBoard(char[][] board){
        for(char[] row : board){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void spaces(char[][] board, int space, String user){
        char symbol = ' ';
        if(user.equals("player")){
            symbol = 'X'; 
            playerPos.add(space);
        }
        else if(user.equals("computer")){
            symbol = 'O';
            computerPos.add(space);
        }
        
        switch(space){
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    public static String Winner(){
        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List middleCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List rightDiagnol = Arrays.asList(1,5,9);
        List leftDiagnol = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(middleCol);
        winning.add(rightCol);
        winning.add(rightDiagnol);
        winning.add(leftDiagnol);

        for(List l : winning){
            if(playerPos.containsAll(l)){
                return "You Win!";
            }
            else if(computerPos.containsAll(l)){
                return "You Lose!"; 
            }
            else if(playerPos.size() + computerPos.size() == 9){
                return "Its a Tie!";
            }
        }
        return "";
    }
}

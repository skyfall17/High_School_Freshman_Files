//Sky Myers Period 5 1/21/16
import java.util.Scanner;
public class Mancala {
	private static final int BOARD_SIZE = 14;
	private static final int START_SEEDS = 4;
	
	private int[] board;
	
	public Mancala(){						//0 444444 | 444444 0
											//0 123456  	<----
		board = new int[BOARD_SIZE];		//0 444444		computer
		board[0] = 0;						//  444444 0	player
		board[13] = 0;						//789 10 11 12 13
		for(int i = 1; i < BOARD_SIZE - 1; i++){//			---->
			board[i] = START_SEEDS;
		}
	}
	public boolean move(int k){ 
		int hand = board[k];
		board[k] = 0;
		int start = k;
		if(start >= 1 && start < BOARD_SIZE / 2){
			k--;
			while(hand > 0){
				if(k == 1){
					board[1]++;
					k = 0;
				}else {
					if(k == 0){
						board[0]++;
						k = 7;
					}else{
						if(k == BOARD_SIZE - 2){
							board[12]++;
							k = 6;
						}else{
							if(k == 6){
								board[6]++;
								k = 5;
							}else{
								if(k >= 7 && k < BOARD_SIZE - 2){
									board[k]++;
									k++;
								}else{
									if(k > 1 && k < BOARD_SIZE / 2 - 1){
										board[k]++;
										k--;
									}
								}
							}
						}
					}
				}
				hand--;
			}
			if(k == 7){
				return true;
			}else{
				if((k >= 0) && (k < BOARD_SIZE / 2 - 1) && (board[k + 1] - 1 == 0) && (k + 7) != 0){
					if(board[k + 7] > 0){
						board[0] += board[k + 1] + board[k + 7];
					}
					board[k + 1] = 0;
					board[k + 7] = 0;
				}
				return false;
			}
		}else{
			k++;
			while(hand > 0){
				if(k == 12){
					board[k]++;
					k++;
				}else{
					if(k == 13){
						board[k]++;
						k = 6;
					}else{
						if(k == 6){
							board[k]++;
							k--;
						}else{
							if(k > 1 && k < BOARD_SIZE / 2 - 1){
								board[k]++;
								k--;
							}else{
								if(k == 1){
									board[k]++;
									k = 7;
								}else{
									if(k >= BOARD_SIZE / 2 && k < BOARD_SIZE - 2){
										board[k]++;
										k++;
									}
								}
							}
						}
					}
				}
				hand--;
			}
			if(k == 6){
				return true;
			}else{
				if((k >= (BOARD_SIZE / 2 + 1)) && (k < BOARD_SIZE) && (board[k - 1] - 1 == 0) && (k - 7) != 0){//player
					if(board[k - 7] > 0){
						board[13] += board[k - 1] + board[k - 7];
					}
					board[k - 1] = 0;
					board[k - 7] = 0;
				}
				return false;
			}
		}
	}
	public void drawBoard(){
		System.out.println("CPU's Positions...");
		for(int c = 0; c < BOARD_SIZE / 2; c++){
			System.out.print(c + "\t");
		}
		System.out.println();
		System.out.println("Board...");
		System.out.println();
		int count = 0;
		System.out.print("    |");
		for(int i = 1; i < BOARD_SIZE / 2; i++){
			System.out.print("\t" + board[i]);
			count++;
		}
		System.out.print("   |" + "\n" + board[0]);
		System.out.print("   |");
		for(int a = 0; a < count; a++){
			System.out.print("\t");
		}
		for(int b = 0; b < count / 4 - 1; b++){
			System.out.print("\t");
		}
		System.out.print("    |\t");
		System.out.print(board[BOARD_SIZE - 1] + "\n");
		System.out.print("    |");
		for(int i2 = (BOARD_SIZE / 2); i2 < BOARD_SIZE - 1; i2++){
			System.out.print("\t" + board[i2]);
		}
		System.out.print("   |");
		System.out.println();
		System.out.println("Your Positions...");
		System.out.print("\t");
		for(int c = 7; c < BOARD_SIZE; c++){
			System.out.print(c + "\t");
		}
		System.out.println();
	}
	public void runGame(){
		Scanner hardMode = new Scanner(System.in);
		System.out.println("Enter Difficulty: \"Easy\", \"Medium\", or \"Hard\"?");
		String s = hardMode.nextLine();
		int difficulty = 0;// 0 - easy, 1 - medium, 2 - hard
		if(s.equals("Easy")){
			difficulty = 0;
		}else if(s.equals("Medium")){
			difficulty = 1;
		}else{
			difficulty = 2;
		}
		int sum1 = -1;
		int sum2 = -1;
		while(sum1 != 0 && sum2 != 0){
			drawBoard();
			//ADD PART FOR COMPUTER'S TURN AFTER BELOW
			Scanner a = new Scanner(System.in);
			System.out.println("Enter a position...");
			int enter = a.nextInt();
			while(enter == 0 || (enter >= 1 && enter <= 6) || enter == 13 || board[enter] == 0){
				System.out.println("\n" + "Entered position: " + enter);
				System.out.println("Please enter a different position (from the range " + (BOARD_SIZE / 2) + " to " + (BOARD_SIZE - 1) + ", inclusive).");
				enter = a.nextInt();
			}
			while(move(enter)){/////////////
				drawBoard();
				Scanner kb = new Scanner(System.in);
				System.out.println("It's your turn again! Enter another valid position...");
				enter = kb.nextInt();
				while(enter == 0 || (enter >= 0 && enter <= 6) || enter == 13 || board[enter] == 0){
					System.out.println("\n" + "Entered position: " + enter);
					System.out.println("Please enter a different position (from the range " + (BOARD_SIZE / 2) + " to " + (BOARD_SIZE - 2) + ", inclusive).");
					enter = kb.nextInt();
				}
			}
			drawBoard();
			if(isDone()){
				sum1 = 0;
				sum2 = 0;
				for(int ab = 1; ab < BOARD_SIZE / 2; ab++){
					sum1 += board[ab];
				}
				for(int ac = BOARD_SIZE / 2; ac < BOARD_SIZE; ac++){
					sum2 += board[ac];
				}
				break;
			}
			System.out.println("It is the computer's turn...\nThinking...\n");
			try {
				 Thread.sleep(4000L);
			}catch (Exception e){
				
			}
			int computerPos = 0;
			if(difficulty == 0){
				computerPos = difficulty(0);
			}else if(difficulty == 1){
				computerPos = difficulty(1);
			}else{
				computerPos = difficulty(2);
			}
			System.out.println("\n" + "Computer's chosen position: " + computerPos);
			while(move(computerPos)){
				drawBoard();
				System.out.println("It is the computer's turn AGAIN!\nProcessing...\n");
				try {
					 Thread.sleep(3000L);
				}catch (Exception e){
					
				}
				computerPos = 0;
				if(difficulty == 0){
					computerPos = difficulty(0);
				}else if(difficulty == 1){
					computerPos = difficulty(1);
				}else{
					computerPos = difficulty(2);
				}
				System.out.println("\n" + "Computer's chosen position: " + computerPos);
				move(computerPos);
			}
			if(isDone()){
				sum1 = 0;
				sum2 = 0;
				for(int ab = 1; ab < BOARD_SIZE / 2; ab++){
					sum1 += board[ab];
				}
				for(int ac = BOARD_SIZE / 2; ac < BOARD_SIZE; ac++){
					sum2 += board[ac];
				}
				break;
			}
		}
		if(board[0] > board[13]){
			for(int i = 1; i < BOARD_SIZE / 2; i++){
				board[0] += board[i];
				board[i] = 0;
			}
			for(int i2 = BOARD_SIZE / 2; i2 < BOARD_SIZE - 2; i2++){
				board[13] += board[i2];
				board[i2] = 0;
			}
			drawBoard();
			System.out.println("COMPUTER WINS!\n" + board[0] + " - " + board[13]);
		}
		if(board[13] > board[0]){
			for(int i = 1; i < BOARD_SIZE / 2; i++){
				board[0] += board[i];
				board[i] = 0;
			}
			for(int i2 = BOARD_SIZE / 2; i2 < BOARD_SIZE - 2; i2++){
				board[13] += board[i2];
				board[i2] = 0;
			}
			drawBoard();
			System.out.println("PLAYER WINS!\n" + board[13] + " - " + board[0]);
		}
		if(board[0] == board[13]){
			System.out.println("TIE!\n" + board[0] + " - " + board[13]);
		}
	}
	
	public boolean isDone(){
		int sum1 = 0;
		for(int i = 1; i < BOARD_SIZE / 2; i++){
			sum1 += board[i];
		}
		int sum2 = 0;
		for(int i2 = BOARD_SIZE / 2; i2 < BOARD_SIZE - 1; i2++){
			sum2 += board[i2];
		}
		if(sum1 == 0 || sum2 == 0){
			return true;
		}else{
			return false;
		}
	}
	public int difficulty(int difficulty){
		int computerPos = 0;
		if(difficulty == 0){
			computerPos = (int)(Math.random() * (BOARD_SIZE / 2 - 1) + 1);
			while(computerPos == 0 || (computerPos >= BOARD_SIZE / 2 && computerPos < BOARD_SIZE) || computerPos == 13 || board[computerPos] == 0){
				computerPos = (int)(Math.random() * (BOARD_SIZE / 2 - 1) + 1);
			}
		}else if(difficulty == 1){
			for(int i = 1; i <= BOARD_SIZE / 2 - 1; i++){
				if(board[i] == i){
					computerPos = i;
				}
			}
			if(computerPos == 0){
				while(computerPos == 0 || (computerPos >= BOARD_SIZE / 2 && computerPos < BOARD_SIZE) || computerPos == 13 || board[computerPos] == 0){
					computerPos = (int)(Math.random() * (BOARD_SIZE / 2 - 1) + 1);
				}
			}
		}else{
			for(int i2 = 1; i2 <= BOARD_SIZE / 2 - 1; i2++){
				if(board[i2] == i2){
					computerPos = i2;
				}
			}
			if(computerPos == 0){
				for(int hard = 1; hard <= BOARD_SIZE / 2 - 1; hard++){
					if((board[hard] >= hard + 7) && (board[hard] <= hard + 13)){
						computerPos = hard;
					}
				}
			}
			if(computerPos == 0){
				while(computerPos == 0 || (computerPos >= BOARD_SIZE / 2 && computerPos < BOARD_SIZE) || computerPos == 13 || board[computerPos] == 0){
					computerPos = (int)(Math.random() * (BOARD_SIZE / 2 - 1) + 1);
				}
			}
		}
		return computerPos;
	}
}

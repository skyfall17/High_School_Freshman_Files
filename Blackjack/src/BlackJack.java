//Sky Myers Period 5 2/16/16
import java.util.Scanner;
import java.util.ArrayList;
public class BlackJack implements Game{
	private ArrayList<Card> player_cards;
	private ArrayList<Card> dealer_cards;
	private Deck deckUsed;
	public BlackJack(){
		String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		String[] suits = {"Clubs", "Diamonds", "Spades", "Hearts"};
		int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
		deckUsed = new Deck(ranks, suits, values);
		deckUsed.shuffle();
		player_cards = new ArrayList<Card>();
		dealer_cards = new ArrayList<Card>();
		playGame();
	}
	public void showCards(ArrayList<Card> cards){
		for(int i = 0; i < cards.size(); i++){
			System.out.println(cards.get(i));
		}
	}
	public void sortCards(ArrayList<Card> cards){
		int j = 0;
		for(int i = 0; i < cards.size(); i++){
			Card temp = new Card(cards.get(i).getRank(), cards.get(i).getSuit(), cards.get(i).getValue());
			j = i;
			while((j > 0) && cards.get(j - 1).getValue() < temp.getValue()){
				cards.set(j, cards.get(j - 1));
				j--;
			}
			cards.set(j, temp);
		}
	}
	public int totalCards(ArrayList<Card> cards){
		int sum = 0;
		for(int i = 0; i < cards.size(); i++){
			if(cards.get(i).getValue() == 1 || cards.get(i).getValue() == 11){
				if(sum + 11 <= 18){
					sum += 11;
				}else{
					sum += 1;
				}
			}else{
				sum += cards.get(i).getValue();
			}
		}
		return sum;
	}
	public void playerTurn(){
		showCards(player_cards);
		Scanner kb = new Scanner(System.in);
		System.out.println();
		String bob = "Yes";
		while(totalCards(player_cards) < 21 && bob.equals("Yes") && deckUsed.size() > 0){
			System.out.println("Do you want another card?\t(Input \"Yes\" or \"No\")");
			bob = kb.next();
			if(!(bob.equals("Yes") || bob.equals("No"))){
				while(!(bob.equals("Yes") || bob.equals("No"))){
					System.out.println("Were you listening?\nDo you want another card?\t(Input \"Yes\" or \"No\")");
					bob = kb.next();
				}
			}
			if(bob.equals("Yes") && deckUsed.size() > 0){
				Card deal = deckUsed.deal();
				if(deal.getRank().equals("Ace")){
					if(1 + totalCards(player_cards) <= 21){
						deal.setValue(1);
						player_cards.add(deal);
					}else if(11 + totalCards(player_cards) <= 21){
						deal.setValue(11);
						player_cards.add(deal);
					}else{
						player_cards.add(deal);
					}
					sortCards(player_cards);
					showCards(player_cards);
				}else{
					player_cards.add(deal);
					sortCards(player_cards);
					showCards(player_cards);
				}
			}
		}
		
	}
	public void dealerTurn(){
		while(totalCards(dealer_cards) < 17 && deckUsed.size() > 0){
			System.out.println("Dealer's turn...");
			try {
				 Thread.sleep(4000L);
			}catch (Exception e){
				
			}
			Card deal = deckUsed.deal();
			dealer_cards.add(deal);
			System.out.println();
			sortCards(dealer_cards);
		}
	}
	public void playGame(){
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter your first name...");
		String name = kb.next();
		String result = "Yes";
		System.out.println(goal());
		try {
			 Thread.sleep(1000L);
		}catch (Exception e){
			
		}
		setUp();
		try {
			 Thread.sleep(1000L);
		}catch (Exception e){
			
		}
		while(!(deckUsed.isEmpty()) && result.equals("Yes")){
			Card playerOne = deckUsed.deal();
			if(!deckUsed.isEmpty()){
				Card playerTwo = deckUsed.deal();
				if(!deckUsed.isEmpty()){
					Card dealerOne = deckUsed.deal();
					if(!deckUsed.isEmpty()){
						Card dealerTwo = deckUsed.deal();
						player_cards.add(playerOne);
						player_cards.add(playerTwo);
						dealer_cards.add(dealerOne);
						dealer_cards.add(dealerTwo);
						sortCards(player_cards);
						sortCards(dealer_cards);
						System.out.println();
						System.out.println("Dealer card...\n" + dealerTwo + "\n\nYour cards...\n");
						playerTurn();
						if(totalCards(player_cards) > 21){
							gameOver("Dealer");
						}else if(totalCards(player_cards) == 21){
							gameOver(name);
						}else{
							dealerTurn();
							gameOver(name);
						}
						sortCards(player_cards);
						sortCards(dealer_cards);
						System.out.println();
						System.out.println("Dealer's cards...");
						showCards(dealer_cards);
						System.out.println();
						System.out.println("Your cards...");
						showCards(player_cards);
						System.out.println("\nDo you want to play again?\t(Input \"Yes\" or \"No\")");
						result = kb.next();
						int size1 = player_cards.size();
						for(int i = 0; i < size1; i++){
							player_cards.remove(0);
						}
						int size2 = dealer_cards.size();
						for(int i = 0; i < size2; i++){
							dealer_cards.remove(0);
						}
						if(deckUsed.size() <= 3){
							System.out.println("Game over. Ran out of cards.");
							break;
						}
					}
				}
			}
		}
		
	}
	@Override
	public String goal() {
		return "BlackJack - get 21";
	}
	@Override
	public void setUp() {
		System.out.println("The player and the dealer are given 2 cards,\nand then the player chooses cards and same with the dealer.");
	}
	@Override
	public void gameOver(String winner) {
		if(totalCards(player_cards) == 21){
			System.out.println(winner + " wins! You won! You got exactly 21!");
		}
		if(totalCards(player_cards) > 21){
			System.out.println("The dealer won! You have way too many cards!");
		}
		if(totalCards(player_cards) < 21){
			if(totalCards(dealer_cards) == 21){
				System.out.println("The dealer won! You lose! The dealer got exactly 21!");
			}
			if(totalCards(dealer_cards) > 21){
				System.out.println(winner + " wins! You won! The dealer got over 21!");
			}
			if(totalCards(dealer_cards) < 21){
				if(totalCards(player_cards) > totalCards(dealer_cards)){
					System.out.println(winner + " wins! You won by " + (totalCards(player_cards) - totalCards(dealer_cards)) + "!");
				}else if(totalCards(player_cards) == totalCards(dealer_cards)){
					System.out.println("You tied, but in this game, the dealer wins whenever there is a tie! MUAHAHA!\nYou both got " + totalCards(player_cards) + "!");
				}else{
					System.out.println("The dealer won by " + (totalCards(dealer_cards) - totalCards(player_cards)) + "! You lose!");
				}
			}
		}
	}
	
}

//Sky Myers Period 5 2/14/16
public class Deck {
	private Card[] deck;
	private int size;
	public Deck(){
		deck = new Card[52];
		String[] suits = {"diamonds", "hearts", "spades", "clubs"};
		String[] rank = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		int count = 0;
		for(int a = 0; a < suits.length; a++){
			for(int i = 1; i <= rank.length; i++){
				Card temp = new Card(rank[i - 1], suits[a], i);
				deck[count] = temp;
				count++;
			}
		}
		size = 52;
		shuffle();
	}
	public Deck(String[] ranks, String[] suits, int[] values){
		deck = new Card[ranks.length * suits.length];
		int count = 0;
		for(int i = 0; i < suits.length; i++){
			for(int a = 0; a < ranks.length; a++){
				Card temp = new Card(ranks[a], suits[i], values[a]);
				deck[count] = temp;
				count++;
			}
		}
		size = deck.length;
		shuffle();
	}
	public void shuffle(){
		for(int i = 0; i < 500; i++){
			int r1 = (int)(Math.random() * size);
			int r2 = (int)(Math.random() * size);
			Card temp = deck[r1];
			deck[r1] = deck[r2];
			deck[r2] = temp;
		}
	}
	public void shuffleFullDeck(){
		size = deck.length;
		shuffle();
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		if(size == 0){
			return true;
		}else{
			return false;
		}
	}
	public String toString(){
		String result = "";
		for(int i = 0; i < size; i++){
			result += deck[i];
		}
		return result;
	}
	public void display(){
		for(int i = 0; i < deck.length; i++){
			System.out.print(deck[i] + "\n");
		}
	}
	public Card[] deal(int n){
		if(n > size){
			throw new IllegalArgumentException("There are too many cards.");
		}else{
			Card[] result = new Card[n];
			for(int i = 0; i < n; i++){
				result[i] = deck[size - i - 1];
			}
			size -= n;
			return result;
		}
	}
	public Card deal(){ // DO I TAKE OUT THE CARD USED, OR JUST LEAVE IT IN THE DECK?
		if(isEmpty()){
			return null;
		}else{
			size -=  1;
			return deck[size];
		}
	}
	public void showDiscardedCards(){
		for(int i = size; i < deck.length; i++){
			System.out.print(deck[i]);
		}
		System.out.println();
	}
}

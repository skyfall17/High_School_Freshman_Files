//Sky Myers Period 5 2/11/16
public class Card implements Comparable{
	private String rank;
	private String suit;
	private int value;
	public Card(String r, String s, int points){
		rank = r;
		suit = s;
		value = points;
	}
	public int getValue(){
		return value;
	}
	public String getSuit(){
		return suit;
	}
	public String getRank(){
		return rank;
	}
	public void setValue(int val){
		value = val;
	}
	public boolean equals(Object c){
		Card test = (Card) c;
		if(value == test.getValue()){
			return true;
		}else{
			return false;
		}
	}
	public boolean match(Card c){
		if(rank.equals(c.getRank())){
			return true;
		}else{
			return false;
		}
	}
	public int compareTo(Object o){
		Card c = (Card) o;
		if(value > c.getValue()){
			return value - c.getValue();
			
		}else if(value == c.getValue()){
			return 0;
		}else{
			return value - c.getValue();
		}
	}
	public String toString(){
		return rank + " of " + suit + " ";
	}
}

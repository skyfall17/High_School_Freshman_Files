//Sky Myers Period 5 2/11/16
public class CardTester {

	public static void main(String[] args) {
		Card a = new Card("3", "Diamonds", 3); // rank, suit, value
		Card b = new Card("Jack", "Hearts", 11);
		Card c = new Card("Jack", "Spades", 11); // equals, match, compareTo
		Card d = new Card("3", "Diamonds", 3);
		Card e = new Card("Ace", "Clubs", 1);
		System.out.println(a.getRank());
		System.out.println(e.getRank());
		System.out.println(a.getSuit());
		System.out.println(b.getValue());
		System.out.println(a.equals(b)); // not the same value
		System.out.println(b.equals(c)); // same value
		System.out.println(e.equals(d)); // not the same value
		System.out.println(a.compareTo(b)); // difference of 8
		System.out.println(b.compareTo(c)); // no difference
		System.out.println(d.compareTo(e)); // difference of 2
		System.out.println(c.compareTo(e)); // difference of 10
		System.out.println(a.match(b));		//different rank
		System.out.println(b.match(c)); 	//same rank
		System.out.println();
		System.out.println(a.toString());
		System.out.println(b);
	}
}
//Sky Myers Period 5 2/14/15
public class DeckTester {

	public static void main(String[] args) {
		String[] ranks = {"jack", "queen", "king"};
		String[] suits = {"blue", "red"};
		int[] pointValues = {11, 12, 13};
		Deck d = new Deck(ranks, suits, pointValues);
		Card[] deck2 = new Card[2];
		deck2[0] = new Card("jack", "blue", 11);
		deck2[1] = new Card("king", "red", 13);
		System.out.println();
		d.shuffle();
		System.out.println(d.toString());
		System.out.println();
		d.shuffleFullDeck();
		System.out.println(d.toString());
		System.out.println(d.size());
		Deck test = new Deck();
		System.out.println();
		test.shuffle();
		test.display();
		System.out.println();
		test.shuffleFullDeck();
		test.display();
		System.out.println(test.size());
		System.out.println(test.isEmpty());
		Card what = test.deal();
		System.out.println(what);
		System.out.println(test.size());
		Card[] testing = test.deal(5);
		System.out.println();
		for(int i = 0; i < testing.length; i++){
			System.out.print(testing[i] + " ");
		}
		System.out.println();
		System.out.println(test.size());
		test.showDiscardedCards();
		System.out.println();
		Card[] runningOut = test.deal(46);
		for(int i = 0; i < runningOut.length; i++){
			System.out.print(runningOut[i] + " ");
		}
		System.out.println();
		System.out.println(test.isEmpty());
		Card[] ttri = test.deal(100);
	}

}
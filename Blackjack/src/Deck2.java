
public class Deck2 {
	int size;
	Card[] deck;
	public Deck2()
	{		
		String[] suits ={"Hearts","Diamonds","Spades","Clubs"};
		String[] ranks={"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
		int[] values={2,3,4,5,6,7,8,9,10,11,12,13,1};
		size=suits.length*ranks.length;
		deck=new Card[size];
		int pos=0;
		for(int i=0;i<suits.length;i++)
			for(int k=0;k<ranks.length;k++)
			{
				deck[pos]=new Card(suits[i],ranks[k],values[k]);
				pos++;
			}	
		shuffle();
	}
	public Deck2(String[] ranks, String[] suits,int[] values)
	{
		size=suits.length*ranks.length;
		deck=new Card[size];
		int pos=0;
		for(int i=0;i<suits.length;i++)
			for(int k=0;k<ranks.length;k++)
			{
				deck[pos]=new Card(suits[i],ranks[k],values[k]);
				pos++;
			}
		shuffle();
	}
	public void shuffle()
	{
		for(int i=1;i<=500;i++)
		{
			int x=(int)(Math.random()*(size));
			int y=(int)(Math.random()*(size));
			Card temp=deck[x];
			deck[x]=deck[y];
			deck[y]=temp;
		}
	}
	public void shuffleFullDeck()
	{
		size=52;
		shuffle();
	}
	public void showDiscardedCards()
	{
		if(size==deck.length)
		{
			System.out.println("No cards have been discarded.");
			return;
		}
		for(int i=size;i<deck.length;i++)
			System.out.println(deck[i]);
	}
	public Card[] deal(int n)
	{
		if(size-n<0)
			throw new IllegalArgumentException("Not enough cards.");
		Card[] out=new Card[n];
		for(int i=0;i<n;i++)
			out[i]=deck[size-1-i];
		size=size-n;
		return out;
	}
	public Card deal()
	{
		if(isEmpty())
			return null;
		Card out=deck[size-1];
		size--;
		return out;	
	}
	public boolean isEmpty()
	{
		if(size==0)
			return true;
		return false;
	}
	public int size()
	{ return size;}
	public String toString()
	{
		String out="";
		for(int i=0;i<size;i++)
			out+=deck[i]+"\n";
		return out;
	}
}
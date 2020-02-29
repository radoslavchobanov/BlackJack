package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck extends Card{
	List <Card> deck = new ArrayList <Card> ();
	
	public Deck()
	{
		super();
		for(int i = 1 ; i <= 4 ; ++i)
		{
			for(int j = 2 ; j <= 14 ; ++j)
			{
				deck.add((new Card(j, new Suite(i))));
			}
		}
	}
	
	public Deck(List<Card>deck)
	{
		this.deck = deck;
	}
	
	public List<Card> getDeck()
	{
		return this.deck;
	}
	public void setDeck(List<Card> deck)
	{
		this.deck = deck;
	}
	public void addToDeck(Card card)
	{
		this.deck.add(card);
	}
	
	public void Print()
	{
		int counter = 0;
		for(int i = 1 ; i <= 4 ; ++i)
		{
			for(int j = 2 ; j <= 14 ; ++j)
			{
				deck.get(counter).PrintCard();
				counter++;
			}
		}
	}
	
	public Card drawCardFromDeck()
	{
		if(this.getDeck().isEmpty())
		{
			Deck deck = new Deck();
			deck.shuffleDeck();
			this.setDeck(deck.getDeck());
			return deck.getDeck().get(0);
		}
		else
		{
			return this.getDeck().remove(0);
		}
	}
	
	public void shuffleDeck()
	{
		Random random = new Random();
		
		for (int i = 0; i < this.getDeck().size(); i++) 
		{
		    int j = random.nextInt(i + 1);
		    
		    Card card = this.getDeck().get(i);
		    this.getDeck().set(i, this.getDeck().get(j));
		    this.getDeck().set(j, card);
		}
	}
}
package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand{
	int handValue;
	List <Card> hand;
	
	public Hand()
	{
		this.handValue = 0;
		this.hand = new ArrayList<Card>();
	}

	public int getHandValue()
	{
		return this.handValue;
	}
	public void setHandValue(int value)
	{
		this.handValue = value;
	}
	
	public List<Card> getHand()
	{
		return this.hand;
	}
	
	public void printHand()
	{
		for(int i=0; i<this.getHand().size(); ++i)
		{
			this.getHand().get(i).PrintCard();
			if(!(this.getHand().size() == i+1))
			{
				System.out.print(", ");
			}
		}
		System.out.println("");
	}
}

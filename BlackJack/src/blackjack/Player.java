package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
	int id;
	String type;
	Hand hand;
	
	boolean surrenderFlag = false;
	
	public Player() 
	{
		this.id = 0;
		this.type = "";
		this.hand = new Hand();
	}
	public Player(String type)
	{
		this.type = type;
	}
	
	public int getId()
	{
		return this.id;
	}
	public String getType()
	{
		return this.type;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	
	public int getHandValue()
	{
		return this.hand.getHandValue();
	}
	public void setHandValue(int value)
	{
		this.hand.setHandValue(value);
	}
	
	public Hand getHand()
	{
		return this.hand;
	}
	public void setHand(Hand hand)
	{
		this.hand = hand;
	}
	
	public boolean getSurrenderFlag() {
		return surrenderFlag;
	}
	public void setSurrenderFlag(boolean surrenderFlag) {
		this.surrenderFlag = surrenderFlag;
	}
	public String getName()
	{
		return this.getName();
	}

	public void showHand()
	{
		if(this.getType() == "Human")
		{
			System.out.print(getName() + " has " + getHandValue() + " --> ");
			this.getHand().printHand();
		}
		else if(this.getType() == "Dealer")
		{
			System.out.print("Dealer has " + getHandValue() + " --> ");
			this.getHand().printHand();
		}
	}
	
	public void Hit(Deck deck)
	{
		Card tmpCard = new Card();
		try 
		{
			tmpCard = deck.drawCardFromDeck();
		}
		catch(NullPointerException e)
		{
			return;
		}
		
		this.getHand().hand.add(tmpCard);
		if(tmpCard.getCardPoints() == 11)
		{
			if((this.getHandValue() + tmpCard.getValue()) > 21)
			{
				this.setHandValue(this.getHandValue() + 1);
			}
			else if((this.getHandValue() + tmpCard.getValue()) <= 21)
			{
				this.setHandValue(this.getHandValue() + 11);
			}
		}
		else if(tmpCard.getCardPoints() >= 10 && tmpCard.getCardPoints() != 11)
		{
			this.setHandValue(this.getHandValue() + 10);
		}
		else if(tmpCard.getCardPoints() < 10)
		{
			this.setHandValue(this.getHandValue() + tmpCard.getCardPoints());
		}
	}
	
	public void Split()
	{
		
		
		
		
//		Card card1 = this.getHandByIndex(0).getHand().get(0);
//		Card card2 = this.getHandByIndex(0).getHand().get(1);
//		
//		this.hands = new ArrayList<Hand>();
//		this.addHandToHands(new Hand(card1));
//		this.addHandToHands(new Hand(card2));
//		
//		this.getHandByIndex(0).printHand();
//		this.getHandByIndex(1).printHand();
	}
	public boolean isHandSoft()
	{
		int tmpHandValue = 0;
		Card ace = new Card();
		Hand playerHand = getHand();
		
		for(Card card : playerHand.getHand())
		{
			if(card.getValue() == 11)
			{
				ace = card;
				for(Card nestedCard : playerHand.getHand())
				{
					if(nestedCard != ace)
					{
						tmpHandValue += nestedCard.getValue();
					}
				}
				if(tmpHandValue + ace.getValue() <= 21)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	public boolean isHandHard()
	{
		if(!isHandSoft())
		{
			return true;
		}
		return false;
	}
	
	public void Double(Deck deck)
	{
		if(this.getHand().getHand().size() == 2)
		{
			Hit(deck);
		}
	}
	
	public boolean Stay()
	{
		return true;
	}
	
	public void Split(Hand hand)
	{
		
	}
	
	public boolean Surrender()
	{
		setSurrenderFlag(true);
		return true;
	}
}

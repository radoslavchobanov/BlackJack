package blackjack;

import java.util.Scanner;

public class Game extends GameInformation
{
	private Player dealer;
	private Player player;
	private Deck deck;
	
	public Game()
	{
	}
	
	public Player getDealer() {
		return dealer;
	}
	public void setDealer(Player dealer) {
		this.dealer = dealer;
	}
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public void roundSetup()
	{
//		Player human = new Human();
		Player bot = new Bot();
		
		setDealer(new Dealer());
		setPlayer(bot);
		setDeck(new Deck());
		
		getDeck().shuffleDeck();
	}
	
	public void playing()
	{
		roundSetup();

		while(true)
		{	
			playerAndDealerStartRoundHits();
			
			if(getPlayer().getType().compareTo("Human") == 0)
			{
				humanDecisionForPlayRound();
				finishRoundAfterHumanHitting();
			}
			
			else if(getPlayer().getType().compareTo("Bot") == 0)
			{
				if(getTotalRounds() == numberOfSimulations)
				{
					break;
				}
				botDecisionForPlayRound();
				finishRoundAfterBotHitting();
			}
			
			setTotalRounds(getTotalRounds() + 1);
			updateData();
			
			if(getPlayer().getType().compareTo("Human") == 0)
			{
				if(!isPlayerContinuePlaying()) // proverqva dali playera shte produljava da igrae
				{
					break;
				}
			}
			
			zanulqvane();
		}
	}
	private void humanDecisionForPlayRound()
	{
		while(true)
		{
			showFirstHands();
			if(isPlayerBusted() || hasBJ(getPlayer()))
			{
				break;
			}
			
			printHittingMenu();
			if(isPlayerStaying())
			{
				break;
			}
		}
	}
	private void botDecisionForPlayRound()
	{
		while(true)
		{
			if(isPlayerBusted() || hasBJ(getPlayer()))
			{
				break;
			}
			if(isBotStaying())
			{
				break;
			}
		}
	}
	private boolean isPlayerContinuePlaying()
	{
		if(getPlayer().getType().compareTo("Bot") == 0)
		{
			return true;
		}
		
		Scanner sc = new Scanner(System.in);
		String tmp = "";
		
		System.out.println("Do you want to play more? y/n");
		tmp = sc.nextLine();
		if(tmp.compareTo("n") == 0)
		{
			return false;
		}
		else if(tmp.compareTo("y") == 0)
		{
			return true;
		}
		else
		{
			return isPlayerContinuePlaying();
		}
	}
	private boolean isPlayerBusted()
	{
		if(getPlayer().getHandValue() > 21)
		{
			return true;
		}
		return false;
	}
	
	private void playerAndDealerStartRoundHits()
	{
		getPlayer().Hit(getDeck());
		getDealer().Hit(getDeck());
		getPlayer().Hit(getDeck());
		getDealer().Hit(getDeck());
	}
	private void showFirstHands()
	{
		System.out.println("");
		getPlayer().showHand();
		System.out.print("Dealer has " + getDealer().getHand().getHand().get(0).getCardPoints() + " --> ");
		getDealer().getHand().getHand().get(0).PrintCard();
		System.out.println("");
		System.out.println("");
	}
	private void showFullHands()
	{
		System.out.println("");
		getPlayer().showHand();
		getDealer().showHand();
		System.out.println("");
	}
	private void printHittingMenu()
	{
		int number = 0;
		
		System.out.println("Hit (" + ++number + ")");
		
		System.out.println("Stay (" + ++number + ")");
		
		if(canPlayerDouble())
		{
			System.out.println("Double (" + ++number + ")");
		}
		if(canPlayerSplit())
		{
			System.out.println("Split (" + ++number + ")");
		}
		if(canPlayerSurrender())
		{
			System.out.println("Surrender (" + ++number + ")");
		}
		
//		if(!canPlayerDouble() && !canPlayerSplit())
//		{
//			System.out.println("Hit (1)");
//			System.out.println("Stay (2)");
//		}
//		else if(canPlayerDouble() && !canPlayerSplit())
//		{
//			System.out.println("Hit (1)");
//			System.out.println("Double (2)");
//			System.out.println("Stay (3)");
//		}
//		else if(canPlayerSplit() && !canPlayerDouble())
//		{
//			System.out.println("Hit (1)");
//			System.out.println("Split (2)");
//			System.out.println("Stay (3)");
//		}
//		else if(canPlayerDouble() && canPlayerSplit())
//		{
//			System.out.println("Hit (1)");
//			System.out.println("Double (2)");
//			System.out.println("Stay (3)");
//			System.out.println("Split (4)");
//		}
	}
	private boolean isBotStaying()
	{
		int dealerFirstCardValue = this.getDealer().getHand().getHand().get(0).getCardPoints();
		int playerHandValue = this.getPlayer().getHandValue();
		
		
		
		if(getPlayer().isHandHard())
		{
			if(playerHandValue >= 4 && playerHandValue <= 8)
			{
				getPlayer().Hit(getDeck());
			}
			else if(playerHandValue == 9)
			{
				if(dealerFirstCardValue == 2)
				{
					getPlayer().Hit(getDeck());
				}
				else if(dealerFirstCardValue >= 3 && dealerFirstCardValue <= 6)
				{
					if(isDoubleAllowed)
					{
						getPlayer().Double(getDeck());
						return getPlayer().Stay();
					}
					else if(!isDoubleAllowed)
					{
						getPlayer().Hit(getDeck());
					}
				}
				else if(dealerFirstCardValue >= 7 && dealerFirstCardValue <= 11)
				{
					getPlayer().Hit(getDeck());
				}
			}
			else if(playerHandValue == 10)
			{
				if(dealerFirstCardValue >= 2 && dealerFirstCardValue <= 9)
				{
					if(isDoubleAllowed)
					{
						getPlayer().Double(getDeck());
						return getPlayer().Stay();
					}
					else if(!isDoubleAllowed)
					{
						getPlayer().Hit(getDeck());
					}
				}
				else if(dealerFirstCardValue >= 10 && dealerFirstCardValue <= 11)
				{
					getPlayer().Hit(getDeck());
				}
			}
			else if(playerHandValue == 11)
			{
				if(dealerFirstCardValue >= 2 && dealerFirstCardValue <= 10)
				{
					if(isDoubleAllowed)
					{
						getPlayer().Double(getDeck());
						return getPlayer().Stay();
					}
					else if(!isDoubleAllowed)
					{
						getPlayer().Hit(getDeck());
					}
				}
				else if(dealerFirstCardValue == 11)
				{
					getPlayer().Hit(getDeck());
				}
			}
			else if(playerHandValue == 12)
			{
				if(dealerFirstCardValue >= 2 && dealerFirstCardValue <= 3)
				{
					getPlayer().Hit(getDeck());
				}
				else if(dealerFirstCardValue >= 4 && dealerFirstCardValue <= 6)
				{
					return getPlayer().Stay();
				}
				else if(dealerFirstCardValue >= 7 && dealerFirstCardValue <= 11)
				{
					getPlayer().Hit(getDeck());
				}
			}
			else if(playerHandValue >= 13 && playerHandValue <= 14)
			{
				if(dealerFirstCardValue >= 2 && dealerFirstCardValue <= 6)
				{
					return getPlayer().Stay();
				}
				else if(dealerFirstCardValue >= 7 && dealerFirstCardValue <= 11)
				{
					getPlayer().Hit(getDeck());
				}
			}
			else if(playerHandValue == 15)
			{
				if(dealerFirstCardValue >= 2 && dealerFirstCardValue <= 6)
				{
					return getPlayer().Stay();
				}
				else if(dealerFirstCardValue >= 7 && dealerFirstCardValue <= 9)
				{
					getPlayer().Hit(getDeck());
				}
				else if(dealerFirstCardValue == 10)
				{
					if(isSurrenderAllowed)
					{
						return getPlayer().Surrender();
					}
					else if(!isSurrenderAllowed)
					{
						getPlayer().Hit(getDeck());
					}
				}
				else if(dealerFirstCardValue == 11)
				{
					getPlayer().Hit(getDeck());
				}
			}
			else if(playerHandValue == 16)
			{
				if(dealerFirstCardValue >= 2 && dealerFirstCardValue <= 6)
				{
					return getPlayer().Stay();
				}
				else if(dealerFirstCardValue >= 7 && dealerFirstCardValue <= 8)
				{
					getPlayer().Hit(getDeck());
				}
				else if(dealerFirstCardValue >= 9 && dealerFirstCardValue <= 11)
				{
					if(isSurrenderAllowed)
					{
						return getPlayer().Surrender();
					}
					else if(!isSurrenderAllowed)
					{
						getPlayer().Hit(getDeck());
					}
				}
			}
			else if(playerHandValue >= 17 && playerHandValue <= 21) 
			{
				return getPlayer().Stay();
			}
		}
		
		else if(getPlayer().isHandSoft())
		{
			if(playerHandValue >= 13 && playerHandValue <= 14)
			{
				if(dealerFirstCardValue >= 2 && dealerFirstCardValue <= 4)
				{
					getPlayer().Hit(getDeck());
				}
				else if(dealerFirstCardValue >= 5 && dealerFirstCardValue <= 6)
				{
					if(isDoubleAllowed)
					{
						getPlayer().Double(getDeck());
						return getPlayer().Stay();
					}
					else if(!isDoubleAllowed)
					{
						getPlayer().Hit(getDeck());
					}
				}
				else if(dealerFirstCardValue >= 7 && dealerFirstCardValue <= 11)
				{
					getPlayer().Hit(getDeck());
				}
			}
			else if(playerHandValue >= 15 && playerHandValue <= 16)
			{
				if(dealerFirstCardValue >= 2 && dealerFirstCardValue <= 3)
				{
					getPlayer().Hit(getDeck());
				}
				else if(dealerFirstCardValue >= 4 && dealerFirstCardValue <= 6)
				{
					if(isDoubleAllowed)
					{
						getPlayer().Double(getDeck());
						return getPlayer().Stay();
					}
					else if(!isDoubleAllowed)
					{
						getPlayer().Hit(getDeck());
					}
				}
				else if(dealerFirstCardValue >= 7 && dealerFirstCardValue <= 11)
				{
					getPlayer().Hit(getDeck());
				}
			}
			else if(playerHandValue == 17)
			{
				if(dealerFirstCardValue == 2)
				{
					getPlayer().Hit(getDeck());
				}
				else if(dealerFirstCardValue >= 3 && dealerFirstCardValue <= 6)
				{
					if(isDoubleAllowed)
					{
						getPlayer().Double(getDeck());
						return getPlayer().Stay();
					}
					else if(!isDoubleAllowed)
					{
						getPlayer().Hit(getDeck());
					}
				}
				else if(dealerFirstCardValue >= 7 && dealerFirstCardValue <= 11)
				{
					getPlayer().Hit(getDeck());
				}
			}
			else if(playerHandValue == 18)
			{
				if(dealerFirstCardValue == 2)
				{
					return getPlayer().Stay();
				}
				else if(dealerFirstCardValue >= 3 && dealerFirstCardValue <= 6)
				{
					if(isDoubleAllowed)
					{
						getPlayer().Double(getDeck());
						return getPlayer().Stay();
					}
					else if(!isDoubleAllowed)
					{
						return getPlayer().Stay();
					}
				}
				else if(dealerFirstCardValue >= 7 && dealerFirstCardValue <= 8)
				{
					return getPlayer().Stay();
				}
				else if(dealerFirstCardValue >= 9 && dealerFirstCardValue <= 11)
				{
					getPlayer().Hit(getDeck());
				}
			}
			else if(playerHandValue >= 19 && playerHandValue <= 21)
			{
				return getPlayer().Stay();
			}
		}
		
		return false;
	}
	private boolean isPlayerStaying()
	{
		int number = 0;
		
		Scanner sc = new Scanner(System.in);
		String tmp = "";
		
		tmp = sc.nextLine();
		
		if(tmp.compareTo(Integer.toString(++number)) == 0)
		{
			getPlayer().Hit(getDeck());
		}
		if(tmp.compareTo(Integer.toString(++number)) == 0)
		{
			return getPlayer().Stay();
		}
		if(canPlayerDouble())
		{
			if(tmp.compareTo(Integer.toString(++number)) == 0)
			{
				getPlayer().Double(getDeck());
				return getPlayer().Stay();
			}
		}
		if(canPlayerSplit())
		{
			if(tmp.compareTo(Integer.toString(++number)) == 0)
			{
				getPlayer().Split(getPlayer().getHand());
			}
		}
		if(canPlayerSurrender())
		{
			if(tmp.compareTo(Integer.toString(++number)) == 0)
			{
				return getPlayer().Surrender();
			}
		}
		
		return false;
	}
	
	private boolean hasBJ(Player player)
	{
		if(player.getHandValue() == 21 && player.getHand().getHand().size() == 2)
		{
			return true;
		}
		return false;
	}
	private void finishRoundAfterHumanHitting()
	{
		if(hasBJ(getPlayer()))
		{
			System.out.println("YOU WIN !!! YOU HAVE BJ !!!");
			setStartBJs(getStartBJs() + 1);
			setWonRounds(getWonRounds() + 1);
			return;
		}
		if(isPlayerBusted())
		{
			System.out.println("YOU LOSE !!! YOU BUSTED !!!");
			setLostRounds(getLostRounds() + 1);
			return;
		}
		if(getPlayer().getSurrenderFlag())
		{
			System.out.println("YOU LOSE !!! YOU SURRENDERED !!!");
			setLostRounds(getLostRounds() + 1);
			return;
		}
		
		while(getDealer().getHandValue() <= 17)
		{
			getDealer().Hit(getDeck());
		}
		
		showFullHands();
		
		if(getDealer().getHandValue() > 21)
		{
			if(getPlayer().getHandValue() <= 21)
			{
				System.out.println("YOU WIN !!! DEALER BUSTED !!!");
				setWonRounds(getWonRounds() + 1);
			}
		}
		else if(getDealer().getHandValue() <= 21)
		{
			if(getPlayer().getHandValue() > getDealer().getHandValue())
			{
				System.out.println("YOU WIN !!! YOU HAVE MORE THAN THE DEALER");
				setWonRounds(getWonRounds() + 1);
			}
			else if(getPlayer().getHandValue() < getDealer().getHandValue())
			{
				System.out.println("YOU LOSE !!! YOU HAVE LESS THAN THE DEALER");
				setLostRounds(getLostRounds() + 1);
			}
			else if(getPlayer().getHandValue() == getDealer().getHandValue())
			{
				System.out.println("EQUAL !!!");
				setEqualRounds(getEqualRounds() + 1);
				setLostRounds(getLostRounds() + 1);
			}
			else
			{
				System.out.println("Unknown behaviour");
			}
		}
	}
	private void finishRoundAfterBotHitting()
	{
		if(hasBJ(getPlayer()))
		{
			setStartBJs(getStartBJs() + 1);
			setWonRounds(getWonRounds() + 1);
			return;
		}
		if(isPlayerBusted())
		{
			setLostRounds(getLostRounds() + 1);
			return;
		}
		
		while(getDealer().getHandValue() <= 17)
		{
			getDealer().Hit(getDeck());
		}
		
		if(getDealer().getHandValue() > 21)
		{
			if(getPlayer().getHandValue() <= 21)
			{
				setWonRounds(getWonRounds() + 1);
			}
		}
		else if(getDealer().getHandValue() <= 21)
		{
			if(getPlayer().getHandValue() > getDealer().getHandValue())
			{
				setWonRounds(getWonRounds() + 1);
			}
			else if(getPlayer().getHandValue() < getDealer().getHandValue())
			{
				setLostRounds(getLostRounds() + 1);
			}
			else if(getPlayer().getHandValue() == getDealer().getHandValue())
			{
				setEqualRounds(getEqualRounds() + 1);
				setLostRounds(getLostRounds() + 1);
			}
		}
	}
	private void zanulqvane()
	{
		getPlayer().setSurrenderFlag(false);
		
		this.getDealer().setHandValue(0);
		this.getDealer().getHand().getHand().clear();
		getPlayer().setHandValue(0);
		getPlayer().getHand().getHand().clear();
	}
	private boolean canPlayerDouble()
	{
		if(getPlayer().getHand().getHand().size() > 2)
		{
			return false;
		}
		
		boolean canPlayerDouble = false;
		
		int dealerFirstCardValue = this.getDealer().getHand().getHand().get(0).getCardPoints();
		Player p = getPlayer();
		
		if(p.isHandSoft())
		{
			if(p.getHandValue() == 15 || p.getHandValue() == 16)//playera ima 15 ili 16
			{
				if(dealerFirstCardValue >= 4 && dealerFirstCardValue <= 6)
				{ // dealera ima 4-6
					canPlayerDouble = true;
				}
			}
			else if(p.getHandValue() == 17) // playera ima 17
			{
				if(dealerFirstCardValue >= 3 && dealerFirstCardValue <= 6)
				{ // dealera ima 3-6
					canPlayerDouble = true;
				}
			}
			else if(p.getHandValue() == 18) // playera ima 18
			{
				if(dealerFirstCardValue >= 2 && dealerFirstCardValue <= 6) 
				{ // dealera ima 2-6
					canPlayerDouble = true;
				}
			}
			else if(p.getHandValue() == 19) // playera ima 19
			{
				if(dealerFirstCardValue == 6) 
				{ // dealera ima 6
					canPlayerDouble = true;
				}
			}
		}
		
		else if(p.isHandHard())
		{
			if(p.getHandValue() == 9) // playera ima 9
			{
				if(dealerFirstCardValue >= 3 && dealerFirstCardValue <= 6)
				{ // dealera ima 3-6
					canPlayerDouble = true;
				}
			}
			else if(p.getHandValue() == 10) // playera ima 10
			{
				if(dealerFirstCardValue >= 2 && dealerFirstCardValue <= 9)
				{ // dealera ima 2-9
					canPlayerDouble = true;
				}
			}
			else if(p.getHandValue() == 11) // playera ima 11
			{
				canPlayerDouble = true;
			}
		}
		
		return canPlayerDouble;
	}
	
	private boolean canPlayerSplit()
	{
//		if(getPlayer().getHand().getHand().get(0).getValue() == getPlayer().getHand().getHand().get(1).getValue())
//		{
//			return true;
//		}
		return false;
	}
	
	private boolean canPlayerSurrender()
	{
		boolean canPlayerSurrender = false;
		
		int dealerFirstCardValue = this.getDealer().getHand().getHand().get(0).getCardPoints();
		Player p = getPlayer();
		
		if(p.isHandHard())
		{
			if(p.getHandValue() == 15) // playera ima 15
			{
				if(dealerFirstCardValue == 10)
				{ // dealera ima 10
					canPlayerSurrender = true;
				}
			}
			else if(p.getHandValue() == 16) // playera ima 16
			{
				if(dealerFirstCardValue >= 9 && dealerFirstCardValue <= 11)
				{ // dealera ima 9-11
					canPlayerSurrender = true;
				}
			}
		}
		
		return canPlayerSurrender;
	}
}

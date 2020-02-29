package blackjack;

public class Card {
	int value;
	String sign;
	Suite suite;
	
	public Card()
	{
		this.value = 0;
		this.sign = "";
		this.suite = new Suite();
	}
	public Card(int value, Suite suite)
	{
		this.value = value;
		this.suite = suite;
		
		if(value == 11) // stoinostta na kartata e 11 -> Aso , Ace
		{
			this.sign = "A";
		}
		
		else if(value == 12) // stoinostta na kartata e 12 -> vale, Jake
		{
			this.sign = "J";
		}
		
		else if(value == 13) // stoinostta na kartata e 13 -> dama, Queen
		{
			this.sign = "Q";
		}
		
		else if(value == 14) // stoinostta na kartata e 14 -> Pop, King
		{
			this.sign = "K";
		}
		
		else if(value > 14 || value < 1)
		{
			System.out.println("WRONG VALUE OF CARD !!!");
		}
		
		else 
		{
			this.sign = Integer.toString(value);
		}
		
	}
	
	public int getValue()
	{
		return this.value;
	}
	public String getSign()
	{
		return this.sign;
	}
	public Suite getSuite()
	{
		return this.suite;
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	public void setSign(String sign)
	{
		this.sign = sign;
	}
	public void setSuite(Suite suite)
	{
		this.suite = suite;
	}
	
	public void PrintCard()
	{
		System.out.print(getSign() + " of " + suite.getSuite());
	}
	
	public int getCardPoints()
	{
		if(this.getValue() >= 10 && getValue() != 11)
		{
			return 10;
		}
		else if(getValue() < 10 && getValue() != 11)
		{
			return getValue();
		}
		else if(getValue() == 11)
		{
			return 11;
		}
		else
		{
			return 1000000;
		}
	}
}
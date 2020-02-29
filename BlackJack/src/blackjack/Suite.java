package blackjack;

public class Suite {
	int value;
	String suite;
	
	public Suite()
	{
		this.value = 0;
		this.suite = "";
	}
	public Suite(int value)
	{
		if(value == 1)
		{
			this.suite = "Spades";
		}
		
		else if(value == 2)
		{
			this.suite = "Heart";
		}
		
		else if(value == 3)
		{
			this.suite = "Diamond";
		}
		
		else if(value == 4)
		{
			this.suite = "Clubs";
		}
		
	}
	
	public String getSuite()
	{
		return this.suite;
	}
	public void setSuite(String suite)
	{
		this.suite = suite;
	}
}
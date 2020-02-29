package blackjack;

import java.util.Scanner;

public class Human extends Player{
	String name;
	
	public Human()
	{
		super.setId(0);
		super.setType("Human");
		
		playerInit();
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	private void playerInit()
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String name = null;

		System.out.println("Enter name of the player : ");
		name = sc.nextLine();
		setName(name);
	}
}
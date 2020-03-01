package blackjack;

//ili nqkakvi globalni promenlivi ili promenlivi v klasa , ili shte napravq nov klas s LogicInformation , ili po nqkakuv nachin da vurja excel za kombinaciite i da zapisva v kletki promenlivite i tam da gi izchislqva
/*
 * int totalCombWith2Cards = 1326    ->   52 vuzmojnosti za 1vata karta, 51 za 2rata. 52*51 = 2652 , i delim na 2 zashtoto pravim kombinaciq s 2 karti. 2652 / 2 = 1326
 * 
 */


public class GameInformation
{	
	public static final int numberOfSimulations = 1000000;
	
	public static final int numberOfDecks = 6;
	
//gameRules
	public static final boolean isDoubleAllowed = true;
	public static final boolean isSurrenderAllowed = true;
	public static final boolean isDoubleAfterSplitAllowed = false;
	
//main variables
	private int startBJs;
	private int totalRounds;
	private int wonRounds;
	private int lostRounds;
	private int equalRounds;
	
	//private startMoney
	//private int currentMoney
	//private int pechalba

	private int roundsWonWith_MoreThan17pts_hand;
	private int roundsWonWith_From12To16pts_hand;
	private int roundsWonWith_LessThan11pts_hand;
	//private int roundsWonWith_4pts_hand; predstoi da se napravi
	
	
	InformationConstants constants;
	
	
//calculated variables
	private double wonRoundsPercent;
	private double lostRoundsPercent;
	private double winRate;
	private double blackJackProbability;
	
	private double roundsWonWith_MoreThan17pts_hand_Percent;
	private double roundsWonWith_From12To16pts_hand_Percent;
	private double roundsWonWith_LessThan11pts_hand_Percent;
	
	public GameInformation()
	{
		
	}
	
	public static double roundAvoid(double value, int places) {
	    double scale = Math.pow(10, places);
	    return Math.round(value * scale) / scale;
	}
	
	public void updateData()
	{	
		this.setWonRoundsPercent(calculateWonRoundsPercent());
		this.setLostRoundsPercent(calculateLostRoundsPercent());
		this.setWinRate(calculateWinRate());
		this.setBlackJackProbability(calculateBlackJackProbability());
	}

	public int getStartBJs() {
		return startBJs;
	}

	public void setStartBJs(int startBJs) {
		this.startBJs = startBJs;
	}

	public int getTotalRounds() {
		return totalRounds;
	}

	public void setTotalRounds(int totalRounds) {
		this.totalRounds = totalRounds;
	}

	public int getWonRounds() {
		return wonRounds;
	}

	public void setWonRounds(int wonRounds) {
		this.wonRounds = wonRounds;
	}

	public int getLostRounds() {
		return lostRounds;
	}

	public void setLostRounds(int lostRounds) {
		this.lostRounds = lostRounds;
	} 

	public int getEqualRounds() {
		return equalRounds;
	}

	public void setEqualRounds(int equalRounds) {
		this.equalRounds = equalRounds;
	}

	public int getRoundsWonWith_MoreThan17pts_hand() {
		return roundsWonWith_MoreThan17pts_hand;
	}

	public void setRoundsWonWith_MoreThan17pts_hand(int roundsWonWith_MoreThan17pts_hand) {
		this.roundsWonWith_MoreThan17pts_hand = roundsWonWith_MoreThan17pts_hand;
	}

	public int getRoundsWonWith_From12To16pts_hand() {
		return roundsWonWith_From12To16pts_hand;
	}

	public void setRoundsWonWith_From12To16pts_hand(int roundsWonWith_From12To16pts_hand) {
		this.roundsWonWith_From12To16pts_hand = roundsWonWith_From12To16pts_hand;
	}

	public int getRoundsWonWith_LessThan11pts_hand() {
		return roundsWonWith_LessThan11pts_hand;
	}

	public void setRoundsWonWith_LessThan11pts_hand(int roundsWonWith_LessThan11pts_hand) {
		this.roundsWonWith_LessThan11pts_hand = roundsWonWith_LessThan11pts_hand;
	}

	public double getWonRoundsPercent() {
		return wonRoundsPercent;
	}

	public void setWonRoundsPercent(double wonRoundsPercent) {
		this.wonRoundsPercent = wonRoundsPercent;
	}

	public double getLostRoundsPercent() {
		return lostRoundsPercent;
	}

	public void setLostRoundsPercent(double lostRoundsPercent) {
		this.lostRoundsPercent = lostRoundsPercent;
	}
	
	
	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public double getBlackJackProbability() {
		return blackJackProbability;
	}

	public void setBlackJackProbability(double blackJackProbability) {
		this.blackJackProbability = blackJackProbability;
	}

	public double calculateWonRoundsPercent()
	{
		int totalRounds;
		double wonRounds;
		double wonPercent;
		
		totalRounds = this.getTotalRounds();
		wonRounds = this.getWonRounds();
		
		wonPercent = wonRounds / totalRounds;
		
		return wonPercent;
	}
	public double calculateLostRoundsPercent()
	{
		int totalRounds;
		double lostRounds;
		double lostPercent;
		
		totalRounds = this.getTotalRounds();
		lostRounds = this.getLostRounds();
		
		lostPercent = lostRounds / totalRounds;
		
		return lostPercent;
	}
	
	public double calculateWinRate()
	{
		double wonRounds;
		double lostRounds;
		double winRate;
		
		wonRounds = this.getWonRounds();
		lostRounds = this.getLostRounds();
		
		winRate = wonRounds / (wonRounds + lostRounds);
		
		return roundAvoid(winRate, 3);
	}
	
	public double calculateBlackJackProbability()
	{
		double totalRounds;
		double blackJacks;
		double blackJackProbability;
		
		totalRounds = this.getTotalRounds();
		blackJacks = this.getStartBJs();
		
		blackJackProbability = blackJacks / totalRounds;
		
		return roundAvoid(blackJackProbability, 4);
	}
	
	public void printTotalRounds()
	{
		System.out.println("Total : " + getTotalRounds());
	}
	public void printWonRounds()
	{
		System.out.println("Won : " + getWonRounds());

	}
	public void printLostRounds()
	{
		System.out.println("Lost : " + getLostRounds());

	}
	public void printEqualRounds()
	{
		System.out.println("Equal : " + getEqualRounds());

	}
	public void printWinRate()
	{
		System.out.println("W/L : " + getWinRate());
	}
	public void printBJProbability()
	{
		System.out.println("BJ Probability : " + getBlackJackProbability());
	}
	public void printBJCount()
	{
		System.out.println("BJ : " + getStartBJs());
	}
	public void printScoreBoard()
	{
		printTotalRounds();
		printWonRounds();
		printLostRounds();
		printEqualRounds();
		printWinRate();
		printBJProbability();
		printBJCount();
	}
	
	//for calculating favourite card na bazata na kolko puti e pecheleno s vsqka edna ot value-tata i da pomni s koe value e pechelilo nai mnogo puti
}
package blackjack;

public class InformationConstants 
{
	private InformationConstants() {}
	
	public static final int totalCombinationWith2Cards = 1326;
	
	//first hand
	
		//17-21 Probability
		public static final double firstHand_BJ_Probability = 0.0483; // 4.83%
		public static final double firstHand_20pts_Probability = 0.1026; // 10.26%
		public static final double firstHand_19pts_Probability = 0.0603; // 6.03%
		public static final double firstHand_18pts_Probability = 0.0693; // 6.93%
		public static final double firstHand_17pts_Probability = 0.0723; // 7.23%
	public static final double firstHand_MoreThan17pts_Probability = 0.3528; // 35.28%
		//12-16 Probability
		public static final double firstHand_16pts_Probability = 0.0; // 0%
		public static final double firstHand_15pts_Probability = 0.0; // 0%
		public static final double firstHand_14pts_Probability = 0.0; // 0%
		public static final double firstHand_13pts_Probability = 0.0; // 0%
		public static final double firstHand_12pts_Probability = 0.0; // 0%
	public static final double firstHand_From12To16pts_Probability = 0.0; // 0%
		//5-11 Probability
		public static final double firstHand_11pts_Probability = 0.0; // 0%
		public static final double firstHand_10pts_Probability = 0.0; // 0%
		public static final double firstHand_9pts_Probability = 0.0; // 0%
		public static final double firstHand_8pts_Probability = 0.0; // 0%
		public static final double firstHand_7pts_Probability = 0.0; // 0%
		public static final double firstHand_6pts_Probability = 0.0; // 0%
		public static final double firstHand_5pts_Probability = 0.0; // 0%
	public static final double firstHand_LessThan11pts_Probability = 0.0; // 0%
		//4 Probability
}

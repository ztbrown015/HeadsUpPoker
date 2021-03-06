package nl.starapple.poker;
import java.util.ArrayList;

public class HandInfo
{
	private HandInfoType infoType;
	private int round;
	private ArrayList<PokerBot> bots;
	private int[] botStacks;
	private int sizeBB, sizeSB;
	private int mySeat;
	private Hand myHand;
	private int buttonSeat;
	private String table;
	private ArrayList<Integer> pots;
	
	public HandInfo(HandInfoType type, int roundNumber, ArrayList<PokerBot> botList, int[] stacks, int bigBlindSize, int smallBlindSize,
					 int button, String tableCards, ArrayList<Integer> allPots)
	{
		infoType = type;
		round = roundNumber;
		bots = botList;
		botStacks = stacks;
		sizeBB = bigBlindSize;
		sizeSB = smallBlindSize;
		buttonSeat = button;
		table = tableCards;
		pots = allPots;
	}
	
	
	/**
	 * Sets on which seat the bot is that receives this MatchInfo
	 * @param botName : the name of the bot
	 */
	public void setCurrentBotInfo(int seat, Hand hand)
	{
		if(seat >= bots.size() || seat < 0)
			System.err.println("The given bot is not part of this match!");
		mySeat = seat;
		myHand = hand;
	}
	
	
	/**
	 * Returns a String representation of the current table situation.
	 */
	public String toString()
	{
		String str = "";
		
		if(infoType.equals(HandInfoType.HAND_CARDS)) {
			str += String.format("bot_%d hand %s\n", mySeat, myHand.toString());
		}
		
		if(infoType.equals(HandInfoType.HAND_START))
		{
			str += String.format("Match round %d\n", round);
			str += String.format("Match smallBlind %d\n", sizeSB);
			str += String.format("Match bigBlind %d\n", sizeBB);
			str += String.format("Match onButton bot_%d\n", buttonSeat);
		}
		
		if( infoType.equals(HandInfoType.PREMOVE_INFO) 
			 || infoType.equals(HandInfoType.HAND_START) ) {
			for(int i = 0; i < bots.size(); i++)
				str += String.format("bot_%d stack %d\n", i, botStacks[i]);
		}
		
		if( infoType.equals(HandInfoType.NEW_BETROUND) ) {
			str += String.format("Match table %s\n", table);
		}
		
		if( infoType.equals(HandInfoType.PREMOVE_INFO) )
		{
			str += String.format("Match pot %d\n", pots.get(0));
			str += String.format("Match sidepots [");
			for(int i = 1; i < pots.size(); i++)
				str += ((i>1)?",":"") + pots.get(i);
			str += "]\n";
		}
		return str;
	}
}

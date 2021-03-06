import java.util.ArrayList;

public class Hand  {

  protected ArrayList<Card> cardlist; //where cards are stored
  protected int handValue; //the score a hand yields
  
  public Hand(){
    cardlist = new ArrayList<Card>(); //instantiates a new ArrayList of type Card
    handValue = getScore(); //assigns handValue by calling getScore()
  }
  
  //returns a string representation of a hand
  public String getHand(){
    return cardlist.toString();
  }
  
  //gets how many cards are in the hand
  public int getSize(){
    return cardlist.size();
  }
  
  //adds a card to the hand
  public void deal(Card c){
    cardlist.add(c);
  }
  
  //calculates the score a hand yields
  public int getScore(){
    if (isEmpty()){
      return 0;
    }
    else{
    int sum = 0;
    int aces = 0;
    
    for (Card c : cardlist){
      if (c.getCardValue().getFace().equals("A")){
        aces++;
      }
      else{
        sum += c.getCardValue().getNumeric();
      }
    }
    while (aces > 0){
      aces--;
      if(sum <= 10){
        sum+=11;
      }
      else{
        sum+=1;
      }
    }
    handValue = sum;
    return handValue;
    }
  }
  
  //returns true if a hand is empty
  public boolean isEmpty(){
    return (cardlist.size() == 0);
      
  }
  
  
  //tests on the Hand object and its methods
  public static void main(String[] args){
    Hand e = new Hand();
    e.deal(new Card());
    e.deal(new Card());
    e.deal(new Card());
    e.getHand();
    System.out.println(e.getSize());
    //System.out.println(e.getScore(e.h));
    System.out.println(e.getScore());
    Hand f = new Hand();
    f.deal(new Card());
    f.deal(new Card());
    f.getHand();
    System.out.println(f.getScore());
    
  }

}
import java.util.*;

public class Card{

  protected CardValue value; //holds a Card's CardValue so it can store face and numeric
  protected Suit suit; //holds a Card's suit
  
  public Card(){
    this.value = new CardValue(determineFace());  //assigns the output of a randomly chosen card to Card's this.value
                                                  //by plugging a call to determineFace() into a CardValue
    this.suit = determineSuit(); //this.suit is assigned a suit at random
                  
  }
  

  public static Suit determineSuit(){
    
    int randNum = (int) (Math.random() * 4); //generates a random value [0,4) as ints, so there can only be 0, 1, 2, 3
    
    if (randNum == 0){
      return Suit.CLUBS;
    }
    else if (randNum == 1){
      return Suit.DIAMONDS;
    }
    else if (randNum == 2){
      return Suit.HEARTS;
    }
    else{ //this did not compile with an else-if at the end
      return Suit.SPADES;
    }
  }
  
  //another valid way to do suit
//   public static Suit determineSuit(){
//
//        switch (rand.nextInt(Suit.values().length)) {
//          case 0: 
//           return Suit.CLUBS;
//          case 1:
//           return Suit.DIAMONDS;
//          case 2:
//           return Suit.HEARTS;
//          case 3:
//           return Suit.SPADES;
//         break;
//}
//
//}
  
  public static String determineFace(){ //determines a face at random so that CardValue in this.value's init has a parameter
    String f = null;
    
    int rand = (int)(Math.random()*13);
    
    if (rand == 0){
      f = "2";
    }
    if (rand == 1){
      f = "3";
    }
    if (rand == 2){
      f = "4";
    }
    if(rand == 3){
      f = "5";
    }
    if(rand == 4){
      f = "6";
    }
    if(rand == 5){
      f = "7";
    }
    if(rand == 6){
      f = "8";
    }
    if(rand == 7){
      f = "9";
    }
    if(rand == 8){
      f = "10";
    }
    if (rand == 9){
      f = "J";
    }
    if (rand == 10){
      f = "Q";
    }
    if (rand == 11){
      f = "K";
    }
    if(rand == 12){
      f = "A";
    }
    return f;
    
  }
  
  //also works
//    public static String determineFace(){
//    
//    
//    int rand = (int)(Math.random()*13);
//    
//    if (rand == 0){
//      return "2";
//    }
//    else if (rand == 1){
//      return "3";
//    }
//    else if (rand == 2){
//      return "4";
//    }
//    else if(rand == 3){
//      return "5";
//    }
//    else if(rand == 4){
//      return "6";
//    }
//    else if(rand == 5){
//      return "7";
//    }
//    else if(rand == 6){
//      return "8";
//    }
//    else if(rand == 7){
//      return "9";
//    }
//    else if(rand == 8){
//      return "10";
//    }
//    else if (rand == 9){
//      return "J";
//    }
//    else if (rand == 10){
//      return "Q";
//    }
//    else if (rand == 11){
//      return "K";
//    }
//    else{
//      return "A";
//    }
//    
//    
//  }
  
  //returns a String representation of the Card
  @Override
  public String toString(){
    return "" + value.getFace() + " of " + suit ;
  }
  //returns CardValue for a given Card
  public CardValue getCardValue(){
    return value;
  }
  
  ///an old method I used before actually making Hand objects
//  public boolean isIn(ArrayList<Card> hand){
//    int acc = 0;
//    for (Card c : hand){
//      if (!equiv(c)){
//        acc+=1;
//      }
//    }
//    if (acc == hand.size()-1){
//      return true;
//    }
//    else{
//      return false;
//    }
//      
//  }
  
  //a method to check if a card was in another hand; goes thru a Hand's cardlist instance variable
  public boolean isIn(Hand sampleHand){
    int acc = 0;
    for (Card c : sampleHand.cardlist){
      if (!equiv(c)){
        acc+=1;
      }
    }
    return (acc == sampleHand.getSize()-1);//{
 //     return true;
    }
//    else{
//      return false;
//    }
    
 // }
  
  //to see if two cards are equivalent in determinant parameters
  public boolean equiv(Card c){
    return((this.value.numeric == c.value.numeric) 
         && (this.suit.equals(c.suit)));
//{
//      return true;
//    }
//    else{
//      return false;
//    }    
  }
  

  
  public static void main(String[] args){
    
    ///these unit tests were used before making a Hand object
//    Card c = new Card();
//    System.out.println(c.toString());
//    Card d = new Card();
//    System.out.println(d.toString());
//    Card e = new Card();
//    System.out.println(e.toString());
//    Card f = new Card();
//    System.out.println(f.toString());
//    System.out.println(e.equals(d));
    
//    Card g = new Card();
//    g.value.face = "10";
//    g.value.numeric = 10;
//    g.suit = Suit.SPADES;
//    Card h = new Card();
//    h.value.face = "10";
//    h.value.numeric = 10;
//    h.suit = Suit.SPADES;
//    System.out.println(g.equiv(h));
//    ArrayList<Card> hand = new ArrayList<Card>();
//    hand.add(g);
//    System.out.println(h.isIn(hand));
    
    //tests to see if methods worked as expected
    Card alpha = new Card();
    alpha.value.face = "J";
    alpha.value.numeric = 10;
    alpha.suit = Suit.CLUBS;
    Card beta = new Card();
    beta.value.face = "J";
    beta.value.numeric = 10;
    beta.suit = Suit.CLUBS;
    System.out.println();
    System.out.println(alpha.equiv(beta));
    Hand hand1 = new Hand();
    hand1.deal(alpha);
    System.out.println(alpha.isIn(hand1));

  }
  
  
  
  
}
import java.util.Scanner;
import java.util.ArrayList;
//an Object Oriented console game

//hold static method to deal in players
public class CardGame2{
  
  //dealers in players by handing them unique cards,
  //returns the hand
  public static Hand dealIn(Hand h){
    
    Card a = new Card();
    Card b = new Card();
    
    h.deal(a);
    
    while(h.getSize() < 2){
      if(!b.isIn(h)){
        h.deal(b);
        
      }
      else dealIn(h);
    }
    return h;
  }
  
//  public static Hand dealInDealer(Hand dealerhand){
//    Hand playerhand = new Hand();
//    hit(dealerhand,playerhand);
//    hit(dealerhand,playerhand);
//    return dealerhand;
//    
//  } 

  
  
  
  
  
  //adds a card to a players hand after checking to ensure its not already in another hand
  public static void hit(Hand a, Hand b){
    Card c = new Card();
    if ((!c.isIn(a)) && (!c.isIn(b))){
      a.deal(c);
    }
    else{hit(a,b);}
  }
  
  
  //returns true if a player is busted
  public static boolean isBusted(Hand h){
    return(h.getScore() > 21);
      
  }
  
  // checks to see if anyone is busted
  public static boolean noBusted(Hand player, Hand dealer){
    return((!isBusted(player)) && (!isBusted(dealer)));
  }
  
  //runs a console version of blackjack
  public static void main(String[] args){
    
    System.out.println("Welcome to Blackjack!\n");
    Hand playerhand = new Hand();
    Hand dealerhand = new Hand();
    dealIn(playerhand);
    hit(dealerhand,playerhand);
    hit(dealerhand,playerhand);
    

    System.out.println("Dealer hand: " + dealerhand.cardlist);
    System.out.println("Your hand: " + playerhand.cardlist);
    
    boolean playerStillIn = true;
    Scanner in = new Scanner(System.in);
    while((playerStillIn) && (!isBusted(playerhand))){
      if(playerhand.getScore() != 21){
        System.out.println("Hit or stay? Press \"H\" for hit, \"S\" for stay");
        String choice = in.next();
        if(choice.equalsIgnoreCase("h")){
          hit(playerhand, dealerhand);
          //CardAdder d = new CardAdder(playerhand.cardlist.get(i));
          System.out.println("YOUR HAND: " + playerhand.cardlist);
          System.out.println("YOUR SCORE: " + playerhand.getScore());
        }
        else if (choice.equalsIgnoreCase("s")){
          playerStillIn = false;
        }
      }
      else{
        System.out.println("BLACKJACK 21!!! YOU WIN BIGLY");
        break;
      }
    }
    
    if (isBusted(playerhand)){
      System.out.println("BUSTED! YOU LOSE!!");

    }
    else{ 
      while(((dealerhand.getScore() < 17) && (noBusted(playerhand,dealerhand))) &&(playerhand.getScore() != 21)) {
        hit(dealerhand,playerhand);
      }
      
      if ((playerhand.getScore() == dealerhand.getScore())) {
        System.out.println("DEALER HAND: " + dealerhand.getHand() + " and SCORE: " + dealerhand.getScore());
        System.out.println("YOUR HAND: " + playerhand.getHand() + " and SCORE: " + playerhand.getScore());
        System.out.println("PUSH");
      }
      
      else if((isBusted(dealerhand)) && (playerhand.getScore() != 21)){
        System.out.println("DEALER HAND: " + dealerhand.getHand() + " and SCORE: " + dealerhand.getScore());
        System.out.println("YOUR HAND: " + playerhand.getHand() + " and SCORE: " + playerhand.getScore() );
        System.out.println("YOU WIN");
      }
      
      
      
      else if ((playerhand.getScore() < dealerhand.getScore())) {
        System.out.println("DEALER HAND: " + dealerhand.getHand() + " and SCORE: " + dealerhand.getScore());
        System.out.println("YOUR HAND: " + playerhand.getHand() + " and SCORE: " + playerhand.getScore());
        System.out.println("YOU LOSE");
      }
      
      else if ((playerhand.getScore() > dealerhand.getScore()) && playerhand.getScore() != 21)  {
        System.out.println("DEALER HAND: " + dealerhand.getHand() + " and SCORE: " + dealerhand.getScore());
        System.out.println("YOUR HAND: " + playerhand.getHand() + " and SCORE: " + playerhand.getScore());
        System.out.println("YOU WIN");
      }
      
      
    }

  }
  
}

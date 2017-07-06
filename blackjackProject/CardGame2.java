import java.util.Scanner;
import java.util.ArrayList;
//an Object Oriented console game
public class CardGame2{
  
  public static Hand dealIn(Hand h){
    
    Card a = new Card();
    Card b = new Card();
    
    h.deal(a);
    
    while(h.getSize() < 2){
      if(!b.equiv(a)){
        h.deal(b);
        
      }
      else dealIn(h);
    }
    return h;
  }
  
  public static Hand dealInDealer(Hand dealerhand){
    Hand playerhand = new Hand();
    Hand playerhandDelt = dealIn(playerhand);
    
    while(dealerhand.getSize() < 2){ //unless Hand extends ArrayList, does not compile
      Card a = new Card();
      if((!a.isIn(playerhandDelt)) && (!a.isIn(dealerhand))){
        dealerhand.deal(a);
      }
    }return dealerhand;
    
  } 
  
  
  
  
  
  public static void hit(Hand a, Hand b){
    Card c = new Card();
    if ((!c.isIn(a)) && (!c.isIn(b))){
      a.deal(c);
    }
    else{hit(a,b);}
  }
  
  
  
  public static boolean isBusted(Hand h){
    if(h.getScore() > 21)
      return true;
    else{
      return false;
    }
  }
  
  public static boolean noBusted(Hand player, Hand dealer){
    if ((!isBusted(player)) && (!isBusted(dealer)))
      return true;
    else{
      return false;
    }
  }
  
  
  public static void main(String[] args){
    
    System.out.println("Welcome to Blackjack!\n");
    Hand playerhand = new Hand();
    Hand dealerhand = new Hand();
    dealIn(playerhand);
    dealInDealer(dealerhand);
    

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

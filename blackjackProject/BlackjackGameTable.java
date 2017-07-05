import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.util.Scanner;

public class BlackjackGameTable extends JFrame{
  
  JButton stayButton = new JButton("STAY");
  JButton hitButton = new JButton("HIT");
  JLabel background;
  Hand playerhand = new Hand();
  Hand dealerhand = new Hand();
  JLabel score;
  JLabel text;
  

  
  
  class DecisionListener implements ActionListener{
    
    public void actionPerformed(ActionEvent a){
      if(a.getSource() == hitButton){
        
        CardGame2.hit(playerhand, dealerhand);        
        playerAddCardToTable(new CardRender2(playerhand.cardlist.get(playerhand.getSize()-1)));
       
       score.setText("Your score: " + playerhand.getScore());
       System.out.println("YOU CHOSE HIT! Your Score: " + playerhand.getScore());
        //revalidate();
       // 
      }
      else if(a.getSource() == stayButton){
        System.out.println("YOU CHOSE STAY!");
      }
      
    }
  }
  
  public BlackjackGameTable(){
    
    this.setTitle("Blackjack");
    background = new TableComponent();
    background.setLayout(null);
    this.add(background,BorderLayout.CENTER);
    this.setVisible(true);
    this.setSize(1300,867);
    
    JPanel buttonPanel = new JPanel();
    score = new JLabel("Your score: " + playerhand.getScore() );
    text = new JLabel("WELCOME TO BLACKJACK!");
    ActionListener pressChoice = new DecisionListener();
    hitButton.addActionListener(pressChoice);
    stayButton.addActionListener(pressChoice);
    buttonPanel.setSize(300,150);
    buttonPanel.add(text, BorderLayout.PAGE_START);
    buttonPanel.add(hitButton,BorderLayout.WEST);
    buttonPanel.add(stayButton,BorderLayout.EAST);
    buttonPanel.add(score, BorderLayout.PAGE_END);
    
    buttonPanel.setVisible(true);
    this.add(buttonPanel, BorderLayout.SOUTH);
    
    CardGame2.dealIn(playerhand);
    CardGame2.dealInDealer(dealerhand);
    CardRender2 playerFirstCard = new CardRender2(playerhand.cardlist.get(0));
    CardRender2 playerSecondCard = new CardRender2(playerhand.cardlist.get(1));
    background.add(playerFirstCard);
    playerFirstCard.setBounds(0,110, playerFirstCard.image.getWidth(), playerFirstCard.image.getHeight());
    background.add(playerSecondCard);
    playerSecondCard.setBounds(120, 110, playerSecondCard.image.getWidth(), playerSecondCard.image.getHeight());
    CardRender2 dealerFirstCard = new CardRender2();
    CardRender2 dealerSecondCard = new CardRender2(dealerhand.cardlist.get(1));
    background.add(dealerFirstCard);
    dealerFirstCard.setBounds(0, 550, dealerFirstCard.image.getWidth(), dealerFirstCard.image.getHeight());
    background.add(dealerSecondCard);
    dealerSecondCard.setBounds(120,550,dealerSecondCard.image.getWidth(), dealerSecondCard.image.getHeight());
    score.setText("Your score: " + playerhand.getScore());
    dealerAddCardToTable(new CardRender2());
    
  } 
  
  public void playerAddCardToTable(CardRender2 c){

    background.add(c);
    c.setBounds((playerhand.getSize() - 1) * c.image.getWidth(), 110, c.image.getWidth(), c.image.getHeight());
    revalidate();
  }
  
  public void dealerAddCardToTable(CardRender2 c){
    
  }
  
  public static void main(String[] args){
        
    System.out.println("Welcome to Blackjack!\n");
    BlackjackGameTable g = new BlackjackGameTable();
    System.out.println("Dealer hand: " + g.dealerhand.cardlist);
    System.out.println("Your hand: " + g.playerhand.cardlist + " " + g.playerhand.getScore());
    
    boolean playerStillIn = true;
    
    Scanner in = new Scanner(System.in);
    
    while((playerStillIn) && (!CardGame2.isBusted(g.playerhand))){
      if(g.playerhand.getScore() != 21){
        g.text.setText("Hit or stay?");
        String choice = in.next();
        if((choice.equalsIgnoreCase("h"))){
          CardGame2.hit(g.playerhand, g.dealerhand);
          
          g.playerAddCardToTable(new CardRender2(g.playerhand.cardlist.get(g.playerhand.getSize()-1)));
          
          g.score.setText("Your score: " + g.playerhand.getScore());
          System.out.println("YOUR HAND: " + g.playerhand.cardlist);
          System.out.println("YOUR SCORE: " + g.playerhand.getScore());
        }
        else if (choice.equalsIgnoreCase("s")){
          playerStillIn = false;
        }
      }
      else{
        System.out.println("BLACKJACK 21!!! YOU WIN BIGLY");
        g.text.setText("BLACKJACK 21!!! YOU WIN BIGLY");
        break;
      }
    }
    
    if (CardGame2.isBusted(g.playerhand)){
      System.out.println("BUSTED! YOU LOSE!!");
      g.text.setText("BUSTED! YOU LOSE!!");
    }
    else{ 
      while(((g.dealerhand.getScore() < 17) && (CardGame2.noBusted(g.playerhand,g.dealerhand))) &&(g.playerhand.getScore() != 21)) {
        CardGame2.hit(g.dealerhand,g.playerhand);
      }
      
      if ((g.playerhand.getScore() == g.dealerhand.getScore())) {
        System.out.println("DEALER HAND: " + g.dealerhand.getHand() + " and SCORE: " + g.dealerhand.getScore());
        System.out.println("YOUR HAND: " + g.playerhand.getHand() + " and SCORE: " + g.playerhand.getScore());
        System.out.println("PUSH");
      }
      
      else if((CardGame2.isBusted(g.dealerhand)) && (g.playerhand.getScore() != 21)){
        System.out.println("DEALER HAND: " + g.dealerhand.getHand() + " and SCORE: " + g.dealerhand.getScore());
        System.out.println("YOUR HAND: " + g.playerhand.getHand() + " and SCORE: " + g.playerhand.getScore() );
        System.out.println("YOU WIN");
      }
      
      
      
      else if ((g.playerhand.getScore() < g.dealerhand.getScore())) {
        System.out.println("DEALER HAND: " + g.dealerhand.getHand() + " and SCORE: " + g.dealerhand.getScore());
        System.out.println("YOUR HAND: " + g.playerhand.getHand() + " and SCORE: " + g.playerhand.getScore());
        System.out.println("YOU LOSE");
      }
      
      else if ((g.playerhand.getScore() > g.dealerhand.getScore()) && g.playerhand.getScore() != 21)  {
        System.out.println("DEALER HAND: " + g.dealerhand.getHand() + " and SCORE: " + g.dealerhand.getScore());
        System.out.println("YOUR HAND: " + g.playerhand.getHand() + " and SCORE: " + g.playerhand.getScore());
        System.out.println("YOU WIN");
      }
      
      
    }
  }
}
  
  
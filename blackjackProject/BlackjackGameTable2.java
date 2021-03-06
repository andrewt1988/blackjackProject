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

public class BlackjackGameTable2 extends JFrame{
  
  ///assign and instantiate game buttons
  JButton stayButton = new JButton("STAY");
  JButton hitButton = new JButton("HIT");
  JButton resetButton = new JButton("Reset");
  //declare the background on which to place TableComponent
  JLabel background;
  //instantiate Hand objects for player and dealer
  Hand playerhand = new Hand();
  Hand dealerhand = new Hand();
  //declare score and test as JLabels
  JLabel score;
  JLabel text;
  //initialize boolean values for whether the game is over, and thus dealer score is hidden
  Boolean gameOver = false;
  Boolean isDealerScoreHidden = true;
  
  //constructs an instance of a full game
  public BlackjackGameTable2(){
    //set title, size, visibility, and instantiate a TableComponent()
    this.setTitle("Blackjack");
    background = new TableComponent();
    background.setLayout(null);
    this.add(background,BorderLayout.CENTER);
    this.setVisible(true);
    this.setSize(1300,867);
    
    //instantiate a Jpanel for buttons
    JPanel buttonPanel = new JPanel();
    
    //set text on the JFrame and for debug purposes the console
    score = new JLabel("Your score: " + playerhand.getScore() + ", dealer score: hidden" );
    text = new JLabel("WELCOME TO BLACKJACK!");
    
    //Instantiate a DecisionListener, add it to the buttons, and place 
    //buttons on the buttonPanel
    ActionListener pressChoice = new DecisionListener();
    hitButton.addActionListener(pressChoice);
    stayButton.addActionListener(pressChoice);
    resetButton.addActionListener(pressChoice);
    buttonPanel.setSize(300,150);
    buttonPanel.add(text, BorderLayout.PAGE_START);
    buttonPanel.add(hitButton,BorderLayout.WEST);
    buttonPanel.add(stayButton,BorderLayout.EAST);
    buttonPanel.add(score, BorderLayout.PAGE_END);
    buttonPanel.add(resetButton, BorderLayout.EAST);
    buttonPanel.setVisible(true);
    this.add(buttonPanel, BorderLayout.SOUTH);
    
    //add Cards to both player and dealer hands
    CardGame2.dealIn(playerhand);
    CardGame2.hit(dealerhand,playerhand);
    CardGame2.hit(dealerhand,playerhand);
    
    //physically place the player and dealers first two cards each on the screen
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
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    playerTwentyOne();
    dealerTwentyOne();
    
    if(isDealerScoreHidden){
      score.setText("Your score: " + playerhand.getScore() + ", dealer score: hidden");
    }
    else{
      score.setText("Your score: " + playerhand.getScore() + ", dealer score: " + dealerhand.getScore());
    }
    
    //for testing only
    System.out.println("YOUR CARDS: " + playerhand.getHand() + ", your score: " + playerhand.getScore());
    System.out.println("DEALER CARDS: " + dealerhand.getHand() + ", dealer score: " + dealerhand.getScore());
    //
  } 
  
  //adds a physical representation of a card to the player's physically displayed hand
  public void playerAddCardToTable(CardRender2 c){

    background.add(c);
    c.setBounds((playerhand.getSize() - 1) * c.image.getWidth(), 110, c.image.getWidth(), c.image.getHeight());
    revalidate();
  }
  //adds a physical rep of a card to the dealers physically displayed hand
  public void dealerAddCardToTable(CardRender2 d){
    background.add(d);
    d.setBounds((dealerhand.getSize() -1) * d.image.getWidth(), 550, d.image.getWidth(), d.image.getHeight());
    revalidate();
  }
  //reveals the dealer's first card, done when a game is over
  public void dealerReveal(){
    isDealerScoreHidden = false;
    CardRender2 dealerOGCard = new CardRender2(dealerhand.cardlist.get(0));
    background.add(dealerOGCard);
    dealerOGCard.setBounds(0, 500, dealerOGCard.image.getWidth(), dealerOGCard.image.getHeight());
    gameOver = true;
  }
  
  //used to make the dealer hit until he's at 17 or higher after player has stayed
  public void dealerStandOnSeventeen(){
    while(((dealerhand.getScore() < 17) && (CardGame2.noBusted(playerhand,dealerhand))) &&(playerhand.getScore() != 21)) {
      CardGame2.hit(dealerhand,playerhand);
      dealerAddCardToTable(new CardRender2(dealerhand.cardlist.get(dealerhand.getSize()-1)));
    }
  }
  
  //used for when stay is pressed, checks scores to see who wins
  public void winLossConditions(){
    
    if ((playerhand.getScore() == dealerhand.getScore())) {
      System.out.println("DEALER HAND: " + dealerhand.getHand() + " and SCORE: " + dealerhand.getScore());
      System.out.println("YOUR HAND: " + playerhand.getHand() + " and SCORE: " + playerhand.getScore());
      System.out.println("PUSH");
      text.setText("PUSH");
      dealerReveal();
    }
    
    else if((CardGame2.isBusted(dealerhand)) && (playerhand.getScore() != 21)){
      System.out.println("DEALER HAND: " + dealerhand.getHand() + " and SCORE: " + dealerhand.getScore());
      System.out.println("YOUR HAND: " + playerhand.getHand() + " and SCORE: " + playerhand.getScore() );
      System.out.println("YOU WIN");
      text.setText("YOU WIN");
      dealerReveal();
    }
    
    
    else if ((playerhand.getScore() < dealerhand.getScore())) {
      System.out.println("DEALER HAND: " + dealerhand.getHand() + " and SCORE: " + dealerhand.getScore());
      System.out.println("YOUR HAND: " + playerhand.getHand() + " and SCORE: " + playerhand.getScore());
      System.out.println("YOU LOSE");
      text.setText("YOU LOSE");
      dealerReveal();
    }
    
    else if ((playerhand.getScore() > dealerhand.getScore()) && playerhand.getScore() != 21)  {
      System.out.println("DEALER HAND: " + dealerhand.getHand() + " and SCORE: " + dealerhand.getScore());
      System.out.println("YOUR HAND: " + playerhand.getHand() + " and SCORE: " + playerhand.getScore());
      System.out.println("YOU WIN");
      text.setText("YOU WIN");
      dealerReveal();
    }
    isDealerScoreHidden = false;
    gameOver = true;
  }
  
  //checks to see if the player has a score of 21, at which the game ends
  public void playerTwentyOne(){
    //for non-push condition
    if ((playerhand.getScore() == 21) &&(dealerhand.getScore() !=21)){
      System.out.println("BLACKJACK 21!!! YOU WIN BIGLY");
      text.setText("BLACKJACK 21!!! YOU WIN BIGLY");
      dealerReveal();          
      gameOver = true;
      isDealerScoreHidden = false;
    }
    //for push condition aka a tie
    else if ((playerhand.getScore() == 21) && (dealerhand.getScore() == 21)){
      System.out.println("PUSH");
      text.setText("PUSH");
      dealerReveal();          
      gameOver = true;
      isDealerScoreHidden = false;
    }
    score.setText("Your score: " + playerhand.getScore() + ", dealer score: " + dealerhand.getScore());
  }
    //similar to playerTwentyOne but for the dealer
    public void dealerTwentyOne(){
    if ((dealerhand.getScore() == 21) &&(playerhand.getScore() !=21)){
      System.out.println("YOU LOSE!");
      text.setText("YOU LOSE!");
      dealerReveal();          
      gameOver = true;
      isDealerScoreHidden = false;
    }
    else if ((playerhand.getScore() == 21) && (dealerhand.getScore() == 21)){
      System.out.println("PUSH");
      text.setText("PUSH");
      dealerReveal();          
      gameOver = true;
      isDealerScoreHidden = false;
    }
    score.setText("Your score: " + playerhand.getScore() + ", dealer score: " + dealerhand.getScore());
  }
  
    //an inner class which implemements interface ActionListener so that buttons can be pressed
    class DecisionListener implements ActionListener{
    
      public void actionPerformed(ActionEvent a){
        //actions for the hit button
        if(a.getSource() == hitButton){
          if(!gameOver){
            System.out.println("YOU CHOSE HIT! Your Score: " + playerhand.getScore());
            
            playerTwentyOne();
            
            if(playerhand.getScore() < 21){
              CardGame2.hit(playerhand, dealerhand);        
              playerAddCardToTable(new CardRender2(playerhand.cardlist.get(playerhand.getSize()-1)));
              
              if(CardGame2.isBusted(playerhand)){
                System.out.println("BUSTED! YOU LOSE!!");
                text.setText("BUSTED! YOU LOSE!!");
                dealerReveal();
              }
              
              
              else{
                System.out.println("Hit or stay?");
                System.out.println("YOUR CARDS: " + playerhand.getHand() + ", your score: " + playerhand.getScore());
                System.out.println("DEALER CARDS: " + dealerhand.getHand() + ", dealer score: " + dealerhand.getScore());
                text.setText("Hit or stay?");
              }
            }
            
            else{
              System.out.println("BUSTED! YOU LOSE!!");
              text.setText("BUSTED! YOU LOSE!!");
              dealerReveal();
            }
            playerTwentyOne();
          }
        }
        //actions for the stay button
        else if(a.getSource() == stayButton){
          if(!gameOver){
            System.out.println("YOU CHOSE STAY!");
            dealerStandOnSeventeen();
            winLossConditions();
          }
        }
        //actions for the reset button
        else if (a.getSource() == resetButton){
          System.out.println("RESETTING...");
          dispose(); //disposes the current running
          main(null); //instantiates a new instance of the game and calls the main; 
          System.out.println("RESET!");
          
        }
        if(isDealerScoreHidden){
          score.setText("Your score: " + playerhand.getScore() + ", dealer score: hidden ");
        }
        else{
          score.setText("Your score: " + playerhand.getScore() + ", dealer score: " + dealerhand.getScore());
        }
      }
    }
    //an instance of a game
  public static void main(String[] args){
        BlackjackGameTable2 g = new BlackjackGameTable2();
    }
  }

  
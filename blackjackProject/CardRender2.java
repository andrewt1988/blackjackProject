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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.Dimension;
import java.awt.Window;
import javax.swing.JWindow;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class CardRender2 extends JComponent{
  
  BufferedImage image;
  String val;
  String suit;
  String filename;
  
  public CardRender2(Card card) {
    this.val = card.value.face;
    this.suit = card.suit.toString();
    filename = this.fetchCardFileLabel();
    try {
      image = ImageIO.read(new File("card deck\\" + filename + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  public CardRender2(){
    this.val = null;
    this.suit = null;
    filename = "DEALER_FIRST_CARD";
    try {
      image = ImageIO.read(new File("card deck\\" + filename + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    super.paintComponent(g2);
    g2.drawImage(image, 0, 0, null);
    
  }
  
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(image.getWidth(), image.getHeight());
  }
  
  public String fetchCardFileLabel(){
    String first = null;
    String second = suit.substring(0,1);
    if(!val.equals("10"))
      first = val.substring(0,1);
    else
      first = val.substring(0,2);
    
    return "" + first + second;
    
  }
  
  public static void main(String[] args){
    CardRender2 dealerfirst = new CardRender2();
    JPanel j = new JPanel();
    //j.setSize(dealerfirst.getPreferredSize());
    j.add(dealerfirst);
    JFrame jf = new JFrame();
    jf.add(j);
    jf.setSize(100,200);
    jf.setVisible(true);
    
    j.setVisible(true);
    CardRender2 k = new CardRender2(new Card());
    JFrame j2 = new JFrame();
    j2.add(k);
    j2.setSize(k.getPreferredSize());
    
  }
  
}
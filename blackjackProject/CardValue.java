public class CardValue{

  protected String face; //a CardValue's verbal name
  protected int numeric; //a CardValue's numeric value 
  
  public CardValue(String faceVal){
    face = faceVal; // set's a CV's face name passed into the constructor
    numeric = findNumericVal(faceVal); //sets the numeric value equal to the output of the call to findNumericVal on the faceVal
  }
  
  public static int findNumericVal(String f){ //converts a face value, denoted by either number or first letter of 
                                              //non-numeric card to an integer value
    
    if (f.equals("A")){
      return 11;
    }
    if (f.equals("J") || f.equals("K") || f.equals("Q")){
      return 10;
    }
        else{
      return Integer.parseInt(f);
    }
  }
  //returns the card's face
  public String getFace(){
    return face;
  }
  //returns the card's numeric value
  public int getNumeric(){
    return numeric;
  }
  
  // tests to see if methods and CV object interact as expected
  public static void main(String[] args){
    CardValue c = new CardValue("K");
    System.out.println(c.numeric);
    CardValue d = new CardValue("A");
    System.out.println(d.numeric);
    CardValue e = new CardValue("4");
    System.out.println(e.numeric);
    
  }
  

  }

  


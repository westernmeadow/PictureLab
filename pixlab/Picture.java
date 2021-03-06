import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  } // END zeroBlue()
  
  public void zeroRed()
  {
    Pixel[][] picture = this.getPixels2D();
    for (Pixel[] row: picture)
    {
      for (Pixel p: row)
      {
        int red = p.getRed();
        p.setRed(red-red);
      }
    }
  } // END zeroRed()
  
  public void zeroGreen()
  {
    Pixel[][] picture = this.getPixels2D();
    for (Pixel[] row: picture)
    {
      for (Pixel p: row)
      {
        int green = p.getGreen();
        p.setGreen(green-green);
      }
    }
  } // END zeroGreen()
  
  public void keepOnlyBlue()
  {
    zeroRed();
    zeroGreen();
  }
  
  public void keepOnlyRed()
  {
    zeroBlue();
    zeroGreen();
  }
  
  public void keepOnlyGreen()
  {
    zeroRed();
    zeroBlue();
  }
  
  public void negate()
  {
    Pixel[][] picture = this.getPixels2D();
    for (Pixel[] row: picture)
    {
      for (Pixel p: row)
      {
        p.setBlue(255-p.getBlue());
        p.setRed(255-p.getRed());
        p.setGreen(255-p.getGreen());
      }
    }
  }
  
  public void grayscale()
  {
    Pixel[][] picture = this.getPixels2D();
    int average = 0;
    for (Pixel[] row: picture)
    {
      for (Pixel p: row)
      {
        average = (p.getBlue()+p.getRed()+p.getGreen())/3;
        p.setBlue(average);
        p.setRed(average);
        p.setGreen(average);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel botPixel = null;
    int width = pixels.length;
    for (int row = 0; row < pixels[0].length; row++)
    {
      for (int col = 0; col < width / 2; col++) 
      {
        topPixel = pixels[col][row];
        botPixel = pixels[width-1-col][row];
        botPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel botPixel = null;
    int width = pixels.length;
    for (int row = 0; row < pixels[0].length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        topPixel = pixels[col][row];
        botPixel = pixels[width-1-col][row];
        topPixel.setColor(botPixel.getColor());
      }
    } 
  }
  
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = Math.min(pixels.length,pixels[0].length);
    for (int row = 0; row < width; row++)
    {
      for (int col = 0; col < width; col++)
      {
        if (row > col)
        {
         leftPixel = pixels[row][col];
         rightPixel = pixels[col][row];
         rightPixel.setColor(leftPixel.getColor());
        }
      }
    } 
  }
  
  public void mirrorDiagonalRec()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int ywidth = pixels.length;
    int xwidth = pixels[0].length;
    double slope = (double)ywidth/xwidth;
    for (int row = 0; row < ywidth; row++)
    {
      for (int col = 0; col < xwidth; col++)
      {
        if (row > slope*col)
        {
         leftPixel = pixels[row][col];
         rightPixel = pixels[ywidth - 1 - row][xwidth - 1 - col];
         rightPixel.setColor(leftPixel.getColor());
        }
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  public void mirrorArms()
  {
    int mirrorPoint = 192;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 165; row < mirrorPoint; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 102; col < 295; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[mirrorPoint - row + mirrorPoint]                       
                         [col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  public void copy(Picture fromPic, 
                 int startRow, int startCol,
                 int fromStartRow, int fromEndRow,
                 int fromStartCol, int fromEndCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = fromStartRow, toRow = startRow; 
         fromRow < fromEndRow &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = fromStartCol, toCol = startCol; 
           fromCol < fromEndCol &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,10);
    this.copy(flower2,100,20);
    this.copy(flower1,200,30);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,40);
    this.copy(flower1,400,50);
    this.copy(flower2,500,60);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  public void myCollage()
  {
    Picture flower1 = new Picture("swan.jpg");
    Picture flower2 = new Picture("swan.jpg");
    Picture flower3 = new Picture("swan.jpg");
    flower1.negate();
    flower2.zeroGreen();
    flower3.mirrorDiagonalRec();
    this.copy(flower3,0,10);
    this.copy(flower2,100,10,100,300,100,300);
    this.copy(flower1,250,10);
    this.mirrorDiagonal();
    this.write("collage.jpg");
  }
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  public void edgeDetection2(int edgeDist)
  {
    Pixel refPixel = null;
    Pixel rightPixel = null;
    Pixel botPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color botColor = null;
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        refPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        botPixel = pixels[row+1][col];
        rightColor = rightPixel.getColor();
        botColor = botPixel.getColor();
        if (refPixel.colorDistance(rightColor) > 
            edgeDist)
          refPixel.setColor(Color.BLACK);
        if (refPixel.colorDistance(botColor) >
            edgeDist)
          refPixel.setColor(Color.BLACK);
        else
          refPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this

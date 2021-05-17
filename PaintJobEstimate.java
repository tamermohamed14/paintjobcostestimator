import java.util.Scanner;
import java.io.*;

/**
   This program will be used to estimate the cost
   of a paint job. This is a group programming
   project that expands on the PaintJob project.
   This project will use text files to populate
   arrays and save the output to a file.
*/

public class PaintJobEstimate
{
   public static void main(String[] args) throws IOException
   {
      double paintPrice = 0.0;                // Price of a gallon of paint per color
      double wallSpace;                       // Amount of wall space
      double totalWallSpace = 0.0;            // Total amount of wall space
      int roomNumbers;                        // Number of rooms
      int colorNumber = 0;                    // Int to hold User's color input
      String clientName;                      // Name of the client
      String clientAddress;                   // Address of the client
      double[] priceArray;                    // Initialize the price array
      String[] paintersArray;                 // Initialize the painters array
      
      // Call the price and painters array methods
      priceArray = populateColorPrice();
      paintersArray = populatePainters();
      
      // Create a scanner object to read from the keyboard
      Scanner keyboard = new Scanner(System.in);
      
      // Get the Client's name
      System.out.print("Please enter the Client's name: ");
      
      clientName = keyboard.nextLine();
      
      // Get the Client's address
      System.out.print("Please enter the Client's address: ");
      
      clientAddress = keyboard.nextLine();
      
      // Get the color of paint to be used
      System.out.print("The available colors and their price per" + 
                       " gallon:\n" +
                       "1: Red    - $10.00 per gallon\n" + 
                       "2: Blue   -  $8.00 per gallon\n" +
                       "3: White  - $12.00 per gallon\n" +
                       "4: Yellow -  $7.00 per gallon\n" +
                       "5: Gray   -  $9.00 per gallon\n");
                       
      System.out.print("Enter the number for color of paint for" + 
                       " the rooms: ");
                       
      
      colorNumber = keyboard.nextInt();
      
      paintPrice = priceArray[colorNumber - 1];
      
      
      // Get the number of rooms      
      System.out.print("Enter the number of rooms to be painted: ");
      
      roomNumbers = keyboard.nextInt();
      
      // Get the wall space for each room
      for(int roomCurrent = 1; roomCurrent <= roomNumbers; roomCurrent++)
      {
         System.out.print("Enter the square feet of wall space in room " +
         roomCurrent + ": ");
         
         wallSpace = keyboard.nextDouble();
         totalWallSpace += wallSpace;
         
      } 
      
      // Added line for seperation
      System.out.println();
      
      // Call and print the methods used to calculate the paint job
      int paintersNeeded = getPaintersNeeded(roomNumbers);
      double paintGallons = getPaintGallons(totalWallSpace);
      double laborHours = getLaborHours(totalWallSpace);
      double paintCost = totalPaintCost(paintPrice, paintGallons);
      double laborCharges = getLaborCharges(laborHours, paintersNeeded);
      double totalCost = getTotalCost(paintCost, laborCharges);
      String color = getColor(colorNumber);
      
     
      displayResults(clientName, clientAddress, color, paintersNeeded, paintGallons, 
                     laborHours, paintCost, laborCharges, totalCost, paintersArray);                  

      printResults(clientName, clientAddress, color, paintersNeeded, paintGallons, 
                   laborHours, paintCost, laborCharges, totalCost, paintersArray);

     
   }
   
   /**
      The populateColorPrice method will be used to create an
      array from the color_price.txt file.
      @return price The array of responses for an answer
   */ 
   
   public static double[] populateColorPrice() throws IOException
   {
      int i = 0;
      double[] price = new double[5];
      
      File file = new File("color_price.txt");
      Scanner inputFile = new Scanner(file);
   
      while (inputFile.hasNext())
      {
         price[i] = inputFile.nextDouble();
         i++;
      
      }
      inputFile.close();
      return price;
   }
   
   /**
      The populatePainters method will be used to create an array
      from the List_Of_Painters.txt file.
      @return painters The array of painter's names
   */
   
   public static String[] populatePainters() throws IOException
   {
      int i = 0;
      String[] painters = new String[4];
      
      File file = new File("List_Of_Painters.txt");
      Scanner inputFile = new Scanner(file);
   
      while (inputFile.hasNext())
      {
         painters[i] = inputFile.nextLine();
         i++;
      
      }
      inputFile.close();
      return painters;
   }
   
   /**
     The getPaintersNeeded method will use an if else statement
     to determine how many painter will be need by the number
     of rooms to paint.
     @param roomNumbers The number of rooms to be painted
     @return getPaintersNeeded
   */
   
   public static int getPaintersNeeded(int roomNumbers)
   {
      int getPaintersNeeded;
      
      if (roomNumbers <= 2)
         getPaintersNeeded = 1;
      else if (roomNumbers <= 4)
         getPaintersNeeded = 2;
      else if (roomNumbers <= 6)
         getPaintersNeeded = 3;
      else
         getPaintersNeeded = 4;
      
      return getPaintersNeeded;   
   }
   
   /**
      The getColor method will use a switch statement
      to match the user input for color with its
      corresponding color.
      @param colorNumber The user input to choose color
      @return colorPicked The chosen color
   */
   
   public static String getColor(int colorNumber)
   {
      String colorPicked;
 
      switch (colorNumber) 
      {      
        case 1: 
            colorPicked = "Color selected: Red\n";
            break;  
        case 2: 
            colorPicked = "Color selected: Blue\n";
            break; 
        case 3: 
            colorPicked = "Color selected: White\n";
            break;
        case 4: 
            colorPicked = "Color selected: Yellow\n";
            break; 
        case 5: 
            colorPicked = "Color selected: Gray\n";
            break;
        default:
            throw new IllegalArgumentException("Error: Invalid Entry");
    
      }
      return colorPicked;
   }
   
   /** 
      The getPaintGallons method calculates the amount
      of paint needed.
      115 square feet = 1 gallon of paint
      @param totalWallSpace The total wall space
      @return getPaintGallons The numbers of gallons needed
   */
       
   public static double getPaintGallons(double totalWallSpace)
   {
     double getPaintGallons;
     getPaintGallons = (totalWallSpace / 115);
     return getPaintGallons;
   }
   
   /**
      The getLaborHours method calculates the amount
      of hours needed.
      115 square feet = 8 hours of labor
      @param totalWallSpace The total wall space
      @return getLaborHours The total hours of labor
   */
   
   public static double getLaborHours(double totalWallSpace)
   {
      double getLaborHours;
      getLaborHours = (totalWallSpace / 115) * 8;
      return getLaborHours;
   }
   
   /**
      The totalPaintCost method calculates the total
      cost of paint needed.
      @param paintPrice Price of a gallon of paint
      @param paintGallons Number of paint gallons needed
      @return totalPaintCost The total cost of paint
   */
   
   public static double totalPaintCost(double paintPrice, double getPaintGallons)
   {
      double totalPaintCost;
      totalPaintCost = (paintPrice * getPaintGallons);
      return totalPaintCost;
   }
   
   /**
      The getLaborCharges method calculates the total cost of
      labor.
      1 hour = 18.00 dollars
      @param getLaborHours The total number of hours
      @return getLaborCharges The total cost of labor
   */
   
   public static double getLaborCharges(double getLaborHours, double getPaintersNeeded)
   {
      double getLaborCharges;
      getLaborCharges = (getLaborHours * 18.0) * getPaintersNeeded;
      return getLaborCharges;
   }
   
   /**
      The totalCost method is used to calculate the
      total cost of the paint job.
      @param totalPaintCost The total cost of paint
      @param getLaborCharges The total cost of labor
      @return getTotalCost The total cost of the paint job
   */
   
   public static double getTotalCost(double totalPaintCost, double getLaborCharges)
   {
      double getTotalCost;
      getTotalCost = totalPaintCost + getLaborCharges;
      return getTotalCost;
   }  
   
   /**
      The displayResults method is used to display the
      estimate of the paint job.
      @param clientName The client's name
      @param clientAddress The client's address
      @param getColor The client's chosen color
      @param getPaintGallons Total amount of paint in gallons
      @param getLaborHours Total amount of hours for the job
      @param totalPaintCost The total cost of paint needed
      @param getLaborCharges The total amount of paid labor
      @param getTotalCost The total cost of the paint job
   */
   
   public static void displayResults(String clientName, String clientAddress, String getColor,
                                     int getPaintersNeeded, double getPaintGallons, double getLaborHours,
                                     double totalPaintCost, double getLaborCharges, double getTotalCost,
                                     String[] paintersArray)
   {
   
      System.out.print("Client's Name: " + clientName + "\n" +
                       "Client's Address: " + clientAddress + "\n" +
                        getColor);
      System.out.printf("The number of gallons of paint required: %.1f\n" +
                               "The number of painters needed: " + getPaintersNeeded + "\n" +
                               "The hours of labor required: %.1f\n" +
                               "The cost of the paint: $%,.2f\n" +
                               "The cost of labor: $%,.2f\n" +
                               "The total cost of the paint job: $%,.2f\n", getPaintGallons, +
                                getLaborHours, totalPaintCost, getLaborCharges, getTotalCost);
      
      System.out.print("The painters will be: ");
      
      for (int i = 0; i < getPaintersNeeded; i++)
         {
         
         System.out.print(paintersArray[i] + ", ");
         
         }
         
      System.out.println("\n");
      
   }
   
   /**
      The printResults method is used to save the estimate of the paint job to a file.
      @param clientName The client's name
      @param clientAddress The client's address
      @param getColor The client's chosen color
      @param getPaintGallons Total amount of paint in gallons
      @param getLaborHours Total amount of hours for the job
      @param totalPaintCost The total cost of paint needed
      @param getLaborCharges The total amount of paid labor
      @param getTotalCost The total cost of the paint job
   */
   
   public static void printResults(String clientName, String clientAddress, String getColor, 
                                   int getPaintersNeeded, double getPaintGallons,double getLaborHours,
                                   double totalPaintCost, double getLaborCharges, 
                                   double getTotalCost, String[] paintersArray) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      String filename;
   
      System.out.print("Save the file as: ");
      filename = keyboard.nextLine() + ".txt";
      
   
      // Call the PrintWriter class and print to file
      PrintWriter outputFile = new PrintWriter(filename);
      
      outputFile.println("Paint Job Estimate: ");
      outputFile.println();   // To add a space
      outputFile.print("Client's Name: " + clientName + "\n" +
                       "Client's Address: " + clientAddress + "\n" +
                        getColor);
      outputFile.printf("The number of gallons of paint required: %.1f\n" +
                               "The number of painters needed: " + getPaintersNeeded + "\n" +
                               "The hours of labor required: %.1f\n" +
                               "The cost of the paint: $%,.2f\n" +
                               "The cost of labor: $%,.2f\n" +
                               "The total cost of the paint job: $%,.2f\n", getPaintGallons, +
                                getLaborHours, totalPaintCost, getLaborCharges, getTotalCost);
      
      // Call the painters needed from the painter's array                          
      outputFile.print("The painters will be: ");
      
      for (int i = 0; i < getPaintersNeeded; i++)
         {
         
         outputFile.print(paintersArray[i] + ", ");
         
         }
      
      outputFile.close();  // Close the file
      
      System.out.print("The file has been saved!");
      
   }
}
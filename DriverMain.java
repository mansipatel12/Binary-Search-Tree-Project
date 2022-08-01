/* Mansi Patel
 * CS 3345.004 -- Data Structures and Introduction to Algorithmic Analysis
 * April 4 2022
 * 
 */

import java.io.*;
import java.util.Scanner;

public class DriverMain {

	public static void main(String[] args) {
		/* The main class will take two command line arguments; the first argument
		 *  is the input file name and the second will be the output file name.
		 */
		
		// If two arguments are not provided on the command line, print an error message
		// and end the program.
		if (args.length != 2) {
			System.out.println("Two arguments must be provided: the input file name and the output file name.\n");
			return;
		}
		
		// Create a new instance of the LazyBinarySearchTree class.
		// The operations specified in the input file will be on this instance.
		LazyBinarySearchTree myTree = new LazyBinarySearchTree();

		try {
			
			// Create a new File object for the input file.
			File inputFile = new File(args[0]);
			// Create a new FileWriter object to write to the output file.
			FileWriter outputFile = new FileWriter(args[1]);
			// Create a Scanner object to read in contents of the input file.
			Scanner inputReader = new Scanner(inputFile);
			
			// Iterate through the input file.
			while (inputReader.hasNextLine()) {
				
				// Store the next line read by the Scanner.
				String currentLine = inputReader.nextLine();
				// Split the current line of the input file by spaces 
				// and by the colon. Store the split string into a
				// String array called inputArr.
				String inputArr[] = currentLine.split("[: ]");
				
				// Call the validateInputFormat method to check the current line to see if it 
				// matches any of the methods in LazyBinarySearchTree class and 
				// that the appropriate number of inputs are provided
				// for a given method.
				if (validateInputFormat(inputArr, outputFile)) {
					// Once the input array format has been validated, call the appropriate
					// method using index 0 of inputArr.
					switch (inputArr[0]) {
					
					case "Insert":
							try {
								// Convert the second index of the inputArr String array to an integer.
								// The second index of the inputArr is the key value that is passed
								// as an input parameter to insert. 
								int insertInput = Integer.parseInt(inputArr[1]);
								// Call the insert method to insert the value into the tree
								// and catch exceptions if they occur. Store the return value
								// in a boolean variable.
								boolean result = myTree.insert(insertInput);
								
								// If the result is true, write True to the output file. If the result
								// is false, write False to the file.
								if (result) {
									outputFile.write("True\n");
								} else if (result == false) {
									outputFile.write("False\n");
								}
							} catch (IllegalArgumentException e) {
								// Catch IllegalArgumentExceptions and write an error message to the 
								// output file.
								outputFile.write("Error in insert: IllegalArgumentException raised\n");
							} 
							// Continue to the next line in the input file.
							continue;
							
					case "Delete":
							try {
								// Convert the second index of the inputArr String array to an integer.
								// The second index of the inputArr is the key value that is passed
								// as an input parameter to delete. 
								int deleteInput = Integer.parseInt(inputArr[1]);
								// Call the delete method to delete the value in the tree
								// and catch exceptions. Store the return value
								// in a boolean variable.
								boolean result = myTree.delete(deleteInput);
								
								// If the result is true, write True to the output file. If the result is false,
								// write False to the file.
								if (result == true) {
									outputFile.write("True\n");
								} else if (result == false) {
									outputFile.write("False\n");
								}
							} catch (IllegalArgumentException e) {
								// Catch IllegalArgumentExceptions and write an error message to the output
								// file.
								outputFile.write("Error in delete: IllegalArgumentException raised\n");
							} 
							// Continue to the next line in the input file.
							continue;
							
					case "Contains":
							try {
								// Convert the second index of the inputArr String array to an integer.
								// The second index of the inputArr is the key value that is passed
								// as an input parameter to contains. 
								int containsInput = Integer.parseInt(inputArr[1]);
								// Call the contains method to check if the value in the tree
								// and catch exceptions. Store the return value
								// in a boolean variable.
								boolean result = myTree.contains(containsInput);
								
								// If the result is true, write True to the file. If the result is false,
								// write False to the output file.
								if (result) {
									outputFile.write("True\n");
								} else if (result == false) {
									outputFile.write("False\n");
								}
							} catch (IllegalArgumentException e) {
								// Catch IllegalArgumentExceptions and write an error message to the file.
								outputFile.write("Error in contains: IllegalArgumentException raised\n");
							} 
							// Continue to the next line in the input file.
							continue;
							
					case "FindMin":
							// Call the findMin method of the LazyBinarySearchTree class, convert
							// the return value to a String, and write the value to the output file.
							outputFile.write(Integer.toString(myTree.findMin()));
							outputFile.write("\n");
							// Continue to the next line in the input file.
							continue;
							
					case "FindMax":
							// Call the findMax method of the LazyBinarySearchTree class, convert
							// the return value to a String, and write the value to the output file.
							outputFile.write(Integer.toString(myTree.findMax()));
							outputFile.write("\n");
							// Continue to the next line in the input file.
							continue;
							
					case "PrintTree":
							// Call the toString method of the LazyBinarySearchTree class,
							// and write it to the output file.
							outputFile.write(myTree.toString());
							outputFile.write("\n");
							// Continue to the next line in the input file.
							continue;
							
					case "Height":
							// Call the height method of the LazyBinarySearchTree class, convert
							// the return value to a String, and write the value to the output file.
							outputFile.write(Integer.toString(myTree.height()));
							outputFile.write("\n");
							// Continue to the next line in the input file.
							continue;
							
					case "Size":
							// Call the size method of the LazyBinarySearchTree class, convert
							// the return value to a String, and write the value to the output file.
							outputFile.write(Integer.toString(myTree.size()));
							outputFile.write("\n");
							// Continue to the next line in the input file.
							continue;
					} // end switch
				} else {
					// If it doesn't, write an error message to the file.
					String errorMessage = "Error in Line: " + currentLine;
					outputFile.write(errorMessage);
					outputFile.write("\n");
					continue;
				} // end else
				
			} // end while
			
			// Once we've reached the end of the input file, close the output file.
			outputFile.close();
		} catch (IOException e) {
			// Catch IOExceptions if there's an error writing to the output file.
			System.out.println("Error with files.\n");
		} // end outer try/catch
		
	} // end main method
	
	/* The validateInputFormat validates the inputArr created by splitting the current line
	 * in the input file. The method checks if the first index of the inputArr (command name)
	 * matches a method in the LazyBinarySearchTree class and checks that the appropriate
	 * number of inputs are provided for the corresponding method. The method takes in a 
	 * String array and FileWriter object as input parameters. It returns a boolean.
	 */
	private static boolean validateInputFormat(String[] inputArr, FileWriter outputFile) {
		// Create a boolean flag variable to change as we validate the inputArr.
		boolean isValid = false;
		// Store the first index of the inputArr (should be the command name) into
		// a String variable.
		String commandName = inputArr[0];
		
		// Note: Commands are case sensitive!
		// Match the command name to a method, then check for number of input values.
		// For methods Insert, Delete, Contains, one additional input value is needed.
		if (commandName.equals("Insert") || commandName.equals("Delete") || commandName.equals("Contains")) {
			
			// If the inputArr length is equal to 1, the input line is not valid.
			if (inputArr.length == 1) {
				isValid = false;
			// If the inputArray length is greater than 1 but index 1 in the array
			// is null (there should be a number in its place), the input line is not valid.
			} else if (inputArr.length > 1 && inputArr[1] == null) {
				isValid = false;
			// If the inputArr.length is greater than two (i.e. Insert:78 99), notify the user
			// that the input value after the command name will be utilized.
			} else if (inputArr.length > 2) {
				try {
					// We still consider it valid (input number will be validated in main).
					isValid = true;
					// // Write a message to the output file notifying the user 
					// that the command name and the input value right after it is only utilized.
					outputFile.write("Only the first input value after the command name will be utilized.\n");
				} catch (IOException e) {
					// Catch IOExceptions if there's an error writing to the output file.
					System.out.println("Error writing to file.\n");
				}
			// If none of the above cases were satisfied (input arr length is equal to 2 
			// and there is a number value present in index 1 of inputArr), 
			// then the input line is valid.
			} else {
				// Set the boolean flag to true.
				isValid = true;
			}
			
		// For all other matched methods, if the inputArr length is greater than or 
		// equal to 2, notify the user that the command name itself will only be 
		// utilized. These methods do not require any additional input values.
		} else if (commandName.equals("FindMin") || commandName.equals("FindMax") || commandName.equals("PrintTree")
				|| commandName.equals("Height") || commandName.equals("Size")) {
			
			if (inputArr.length >= 2) {
				// We still consider it valid.
				try {
					// Set the boolean flag to true.
					isValid = true;
					// Write a message to the output file notifying the user 
					// that the command name itself is only utilized.
					outputFile.write("Only the command name will be utilized.\n");
				} catch (IOException e) {
					// Catch IOExceptions if there's an error writing to the output file.
					System.out.println("Error writing to file.\n");
				}
			} else {
				// If the inputArr length is 1, it is valid.
				// Set the boolean flag to true.
				isValid = true;
			}
			
		// If the commandName didn't match up with any of the 
		// method names, the input line is not valid.
		} else {
			// Set the boolean flag to false.
			isValid = false;
		}
		
		// Return the value of the flag variable.
		return isValid;
	} // end validateInputFormat method

} // end DriverMain class

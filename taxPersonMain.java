package taxPerson;

import java.util.Scanner;

/**
 * 
 * @author Monalisa Mishra
 * 
 * This program serves to implement the following question:
 * Calculate the total cost of an item including tax based on whether
 * it is considered a necessary or luxury item. The tax rate for basic
 * necessities is 1%, the tax rate for luxury items is 9%. For simplicity
 * of implementation, all transactions are measured in cents (pennies).
 * 
 *
 */
public class taxPersonMain {

	/**
	 * 
	 * @param args
	 * 
	 * Main class. Performs the following operations:
	 * 
	 * Takes user input for two items:
	 * <ol>
	 * <li> A floating-point value denoting an item's cost </li>
	 * <li> A string denoting whether an item is a luxury item 
	 * ("yes" or "YES") or is not a luxury item ("no" or "NO") </li>
	 * </ol>
	 * 
	 * The program then creates an object of the Item class, which 
	 * comprises the properties and methods of an item.
	 * 
	 * Then, the program parses valid values from the input 
	 * or prompts the user for new, valid input. 
	 * After receiving valid input, it sets the luxury status 
	 * and cost and returns the final cost after tax.
	 */
	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		double initialPrice = -1.0;
		String luxuryStatusInput = "";
		
		//Get cost input at least once
		//If the input is an invalid number, ask for new input
		
		do {
			System.out
					.println("Please input the price of an item as an integer or floating point number with two decimals");
			initialPrice = parseCostInput(scan.nextLine());
		} while (initialPrice == -1.0);

		//Get luxury status input at least once
		//If the input is an invalid response (anything but no or yes, case-insensitive)
		do {
			System.out
					.println("Please input 'yes' if this is a luxury item, which covers everything not needed for basic survival");
			luxuryStatusInput = scan.nextLine();
		} while (!(luxuryStatusInput.toLowerCase().matches("yes") || luxuryStatusInput.toLowerCase()
				.matches("no")));
		
		//Build Item object with the known-to-be-valid inputs
		Item itemToPrice = new Item();
		itemToPrice.setIsLuxury(parseLuxuryStatus(luxuryStatusInput));
		itemToPrice.setCost(initialPrice);

		//output result
		System.out.println("Item cost is: "
				+ itemToPrice.calculateCostAfterTax());
	}

	

	/**
	 * 
	 * Returns a parsed double value that can be provided as a cost value
	 * to the Item class.
	 * 
	 * The format of the parse regex in the code represents, in order:
	 * <ul>
	 * <li>Many numbers (dot) one number, example: ###.#</li>
	 * <li>Many numbers (dot) two numbers, example: ###.##</li>
	 * <li>One number, as in an integer, example: ##</li>
	 * </ul>
	 * 
	 * @param costInput
	 * @return	Parsed double value or a sentinel double value to force the user to input a non-negative valid value
	 */
	public static double parseCostInput(String costInput) {

		if (costInput.matches("\\d+\\.\\d")
				|| costInput.matches("\\d+\\.\\d\\d")
				|| costInput.matches("\\d+")) {
			return Double.parseDouble(costInput);
		}
		return -1.0;
	}

	/**
	 * Parses the input string and determines (case-insensitively) if the input describes a luxury item or not
	 * 
	 * @param isLuxuryInput
	 * @return	Boolean denoted whether the item described by the input is a luxury item or not
	 */
	public static Boolean parseLuxuryStatus(String isLuxuryInput) {

		if (isLuxuryInput.toLowerCase().matches("yes")) {
			return true;
		}
		return false;
	}
	
}

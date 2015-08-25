package taxPerson;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 * @author Ray Alfano
 *
 * Class describing an Item, which is subject to one of two fixed tax rates
 * and provides several methods
 * 
 *
 */

public class Item {
	private static final double LUXURYTAXRATE = 1.09;
	private static final double NECESSITIESTAXRATE = 1.01;

	private double cost;
	private Boolean isLuxury;

	/**
	 * Basic constructor
	 */
	
	public Item() {
		cost = 0;
		isLuxury = false;
	}

	/**
	 * Setter for the cost value of an item
	 * Sets the value based on a pre-validated input cost number
	 * @param inputCost a valid double representing the user's input for an item's cost
	 */
	public void setCost(double inputCost) {

		cost = inputCost;
	}

	/**
	 * Getter for cost
	 * @return cost The user's input for cost
	 */
	public double getCost() {

		return cost;
	}

	/**
	 * Setter for determining tax rate based on item classification
	 * 
	 * @param inputIsLuxury A true/false value denoting whether the user stated
	 * that this item is or is not a luxury item
	 * 
	 */
	public void setIsLuxury(Boolean inputIsLuxury) {

		isLuxury = inputIsLuxury;
	}

	/**
	 * Getter for isLuxury
	 * @return isLuxury If the user stated this is a luxury item or not
	 */
	public Boolean getIsLuxury() {

		return isLuxury;
	}

	/**
	 * Calculates the after-tax cost of an item
	 * Multiplies the user input cost with the tax rate
	 * If the resulting value would exceed 2^1023, output 2^1023
	 * After calculating the after-tax value, this method calls a subroutine
	 * to round the value to the nearest hundredth (penny) value.
	 * This method then returns the rounded value.
	 * 
	 * @return The after-tax rounded value
	 */
	public double calculateCostAfterTax() {

		if (this.isLuxury) {
			// Handle edge case of data boundary overflow
			// Highly unlikely given datatype size
			if (this.cost * LUXURYTAXRATE >= Double.MAX_VALUE) {
				return Double.MAX_VALUE;
			}
			return this.roundToPennies((this.cost * LUXURYTAXRATE));
		}
		// Handle edge case of data boundary overflow
		// Highly unlikely given datatype size
		if (this.cost * NECESSITIESTAXRATE >= Double.MAX_VALUE) {
			return Double.MAX_VALUE;
		}
		return this.roundToPennies(this.cost * NECESSITIESTAXRATE);
	}

	/**
	 * 
	 * @param input The after-tax, non-rounded cost value
	 * @return result The final, rounded-to-hundredth output of the program
	 */
	public double roundToPennies(double input) {

		BigDecimal value = new BigDecimal(input);
		return value.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

}

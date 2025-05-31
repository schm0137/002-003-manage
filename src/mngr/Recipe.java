package mngr;

/**		
 *	002-003-manage: This java file manages the Recipe object.
 *	Recipe.java
 *	@author Lily S.
 *	@version 1.0
 *	@since JDK 21.0.4 
*/
public class Recipe {
	/**
	 * Holds name of recipe object 
	 */
	private String name;
	/**
	 * Holds sugar amount given in ingredient list for individual recipe object
	 */
	private float sugar;
	/**
	 * Holds egg amount given in ingredient list for individual recipe object
	 */
	private float eggs;
	/**
	 * Holds flour amount given in ingredient list for individual recipe object
	 */
	private float flour;
	/**
	 * Holds yeast amount given in ingredient list for individual recipe object
	 */
	private float yeast;
	/**
	 * Holds butter amount given in ingredient list for individual recipe object
	 */
	private float butter;
	/**
	 * Holds a simple counter that tracks the amount of a recipe the user has added to their list
	 */
	private int counter;
	
	/**
	 * Empty constructor for Recipe object
	 */
	public Recipe() {
		
	}
	
	/**
	 * modcounter(int amount) is a function dedicated to updating an individual recipe object's counter, which represents how many of said recipe the user has added to their object. If statement is to prevent the user from entering large negative values and sending the counter to the negatives (should be impossible for a recipe to be negative on a list, it's either 0 (not there) or a certain amount.) Otherwise, amount requested is added to the overall counter value.
	 * @param amount of recipes requested by user when interacting with the "create shopping list" option in the program. Can be either positive (addition) or negative (subtraction)
	 */
	public void modCounter(int amount) {
		if (counter + amount < 0) {
			System.out.print("Removal requested is higher than current recipe amount on list. Recipe amount set to 0. \n");
			counter = 0;
		} else {
			counter = counter + amount;
		}
	
	}
	
	/**
	 * This getter returns the value held within the counter variable.
	 * @return amount value of specific recipe object on user's list
	 */
	public int getCounter() {
		return counter;
	}
	/**
	 * This getter returns the recipe name held within the name variable.
	 * @return recipe name for the recipe 
	 */
	public String getName() {
		return name;
	}
	/**
	 * This setter should take in a String (representing recipe name) and set the recipe's corresponding variable (name) to said input. Substring + strip is used to remove the word "recipe" from the title, (which is used to identify a recipe object in a txt file) and strip is to remove leading & trailing blank space.
	 * @param name for the recipe
	 */
	public void setName(String name) {
		this.name = name.substring(6).strip();
	}
	/**
	 * This getter returns the value held within the sugar variable.
	 * @return sugar value (amount) included in the recipe's ingredient list
	 */
	public float getSugar() {
		return sugar;
	}
	/**
	 * This setter should take in a float and set the recipe's corresponding variable (sugar) to said input.
	 * @param sugar value for the recipe, corresponding to ingredient list
	 */
	public void setSugar(float sugar) {
		this.sugar = sugar;
	}
	/**
	 * This getter returns the value held within the egg variable.
	 * @return egg value (amount) included in the recipe's ingredient list
	 */
	public float getEggs() {
		return eggs;
	}
	/**
	 * This setter should take in a float and set the recipe's corresponding variable (egg) to said input.
	 * @param egg value for the recipe, corresponding to ingredient list
	 */
	public void setEggs(float eggs) {
		this.eggs = eggs;
	}
	/**
	 * This getter returns the value held within the flour variable.
	 * @return flour value (amount) included in the recipe's ingredient list
	 */
	public float getFlour() {
		return flour;
	}
	/**
	 * This setter should take in a float and set the recipe's corresponding variable (flour) to said input.
	 * @param flour value for the recipe, corresponding to ingredient list
	 */
	public void setFlour(float flour) {
		this.flour = flour;
	}
	/**
	 * This getter returns the value held within the yeast variable.
	 * @return yeast value (amount) included in the recipe's ingredient list
	 */
	public float getYeast() {
		return yeast;
	}
	/**
	 * This setter should take in a float and set the recipe's corresponding variable (yeast) to said input.
	 * @param yeast value for the recipe, corresponding to ingredient list
	 */
	public void setYeast(float yeast) {
		this.yeast = yeast;
	}
	/**
	 * This getter returns the value held within the butter variable.
	 * @return butter value (amount) included in the recipe's ingredient list
	 */
	public float getButter() {
		return butter;
	}
	/**
	 * This setter should take in a float and set the recipe's corresponding variable (butter) to said input.
	 * @param butter value for the recipe, corresponding to ingredient list
	 */
	public void setButter(float butter) {
		this.butter = butter;
	}

}

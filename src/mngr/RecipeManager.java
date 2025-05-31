package mngr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**		
 *	002-003-manage: This java file manages the RecipeManager object.
 *	RecipeManager.java
 *	@author Lily S.
 *	@version 1.0
 *	@since JDK 21.0.4 
*/
public class RecipeManager {
	
	/**
	 * String variable that holds each line of the text file using a scanner object, load() then takes this value and decides what to do with it depending on the content. (Could be ingredient title, ingredient amount, recipe title, etc.)
	 */
	private String recipeStart = "";
	/**
	 * String variable that holds an ingredient title for a specific recipe object. Variable is used later in a switch statement in order to attach the proper amount (ingredientAmount) to their corresponding ingredient title inside a recipe object. 
	 */
	private String ingredientTitle = "";
	/**
	 * String variable that holds an ingredient amount for a specific recipe object.
	 */
	private String ingredientAmountStr = "";
//	private String printListStr = "";
	/**
	 * String variable that appends any system prints that is done during the printList() function. Done to provide export functionality to a txt file, a writer object takes in this String in the save() function. 
	 */
	private String writeStr = "";
	/**
	 * Float variable that holds the converted and cleaned up value from ingredientAmountStr. Conversion required as Recipe.java object ingredients variables are floats. 
	 */
	private Float ingredientAmountFl = 0.0f;
	/**
	 * Array list created to hold individual recipe objects.
	 */
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();  
	/**
	 * Holds flour amount added to user's list.
	 */
	private float flourList = 0.0f;
	/**
	 * Holds sugar amount added to user's list.
	 */
	private float sugarList = 0.0f;
	/**
	 * Holds eggs amount added to user's list.
	 */
	private float eggsList = 0.0f;
	/**
	 * Holds butter amount added to user's list.
	 */
	private float butterList = 0.0f;
	/**
	 * Holds yeast amount added to user's list.
	 */
	private float yeastList = 0.0f;
	/**
	 * Boolean that is used to detect if the user has added any recipes to their list. Ex. getAnyOrders() checks if any order has a counter (list add) and if any does in the arrayList, set anyOrders to true. Boolean used to handle if user asks to print list without anything added.
	 */
	private boolean anyOrders = false;
	
	/**
	 * This getter returns the array list that holds individual recipe objects, created within the RecipeManager object.
	 * @return arraylist of individual recipe objects
	 */
	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	/**
	 * Load functionality called at beginning of program, takes in a text file, separates blocks of text into individual Recipe.java objects using the word recipe as a separator, then separates the ingredient title and amount which is then passed into their corresponding object to give a recipe all the required values. Additional functionality is present to prevent user from using a blank file. 
	 */
	public void load() {
		try {
			/**
			 * file object creation assigned to recipelist.txt
			 */
			File recipeList = new File("recipelist.txt");
			/**
			 * Scanner creation to read File object
			 */
			try (Scanner recipeScan = new Scanner(recipeList)) {
				if (recipeList.length() == 0) {
					System.out.print("Recipe text file is empty. Please restart the program after ensuring that recipelist.txt has content inside.");
					System.exit(0);
				}
				recipeStart = recipeScan.nextLine().toLowerCase();
				while (recipeScan.hasNext()) {
				
					if (recipeStart.contains("recipe")) {
						Recipe recipe = new Recipe();
						recipe.setName(recipeStart.strip());
						while(recipeScan.hasNextLine()) {
							
							recipeStart = recipeScan.nextLine().toLowerCase();
							if (recipeStart.contains("recipe")) {
								
//								Test line to see when program separates objects
//								System.out.print("\nBreaked! \n");
								
								break;
							}
				
							for (int i = 0; i < recipeStart.length(); i++) {
								if (Character.isLetter(recipeStart.charAt(i))) {
									ingredientTitle = ingredientTitle + recipeStart.charAt(i);
								
								} else if (Character.isDigit(recipeStart.charAt(i)) || recipeStart.charAt(i) == '.') {
									ingredientAmountStr = ingredientAmountStr + recipeStart.charAt(i);
									
								}
							}
							if (ingredientAmountStr != "") {
								ingredientAmountFl = Float.parseFloat(ingredientAmountStr); 
							}

							switch(ingredientTitle) {
								case "flour":
									recipe.setFlour(ingredientAmountFl);
									break;
								case "sugar":
									recipe.setSugar(ingredientAmountFl);
									break;
								case "eggs":
									recipe.setEggs(ingredientAmountFl);
									break;
								case "butter":
									recipe.setButter(ingredientAmountFl);
									break;
								case "yeast":
									recipe.setYeast(ingredientAmountFl);
									break;
							}
							ingredientTitle = "";
							ingredientAmountStr = "";
							ingredientAmountFl = 0.0f;
							
						}
						recipes.add(recipe);
						
					} else {
						break;
					}
					
					
				}
				
			}
		
		} catch (FileNotFoundException fnfe) {
			System.out.print("Cannot find recipe text file.\nPlease ensure that the recipes are in a text file called \"recipelist.txt\" located in the assignment3 folder, and restart the program. ");
			System.exit(0);
		} 
	}
	/**
	 * Displays available recipes to the user, taken from arrayList recipes that holds all individual recipe objects, which were created from given txt file.
	 */
	public void showRecipe() {
		System.out.println("\nAvailable Recipes:");
		for ( Recipe recipe : recipes) {
			
			System.out.println((recipes.indexOf(recipe) + 1) + ". " + recipe.getName());
			
		}
	}
	/**
	 * Provides functionality for the user to add any amount of a specific recipe to their list, alongside using negative amounts to take away from list if they add too many, for example.
	 * 
	 * @param recipe, taken from user input. User is stating the recipe they want to add to their list.
	 * @param amount, taken from user input. User is stating the amount of the specific recipe selected in the parameter recipe they want to add to their list.
	 */
	public void addList(int recipe, int amount) {
		try {
			recipes.get(recipe).modCounter(amount);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Please enter a number between the available recipes. (" + getRecipes().size() + ")");
		}
	}
	/**
	 * Provides functionality for the user to print their entire shopping list that is held within the program. Additionally, it adds the content shown to the user to a string variable, which is later passed into a writer object if they decide to save (export) their list to a txt file.
	 */
	public void printList() {
		// resets shopping list ingredient variables on function call to prevent improper math
		flourList = 0;
		sugarList = 0;
		eggsList = 0;
		butterList = 0;
		yeastList = 0;
		
		for ( Recipe recipe : recipes ) {
			if (recipe.getCounter() > 0) {
				if (recipe.getCounter() > 1) {
					System.out.println(recipe.getCounter() + " " + recipe.getName() + " loaves");
					writeStr = writeStr + ("\n" + recipe.getCounter() + " " + recipe.getName() + " loaves");
				} else {
					System.out.println(recipe.getCounter() + " " + recipe.getName() + " loaf");
					writeStr = writeStr + ("\n" + recipe.getCounter() + " " + recipe.getName() + " loaf");
				}
				flourList = flourList + (recipe.getFlour() * recipe.getCounter());
				sugarList = sugarList + (recipe.getSugar() * recipe.getCounter());
				eggsList = eggsList + (recipe.getEggs() * recipe.getCounter());
				butterList = butterList + (recipe.getButter() * recipe.getCounter());
				yeastList = yeastList + (recipe.getYeast() * recipe.getCounter());
			}
			
		}
		
		writeStr = writeStr + "\n\n";
		
		System.out.println("\nYou will need a total of: \n");
		if (flourList > 0.0f) {
			System.out.println(flourList + " grams of flour");
			writeStr = writeStr + (flourList + " grams of flour\n");
		}
		if (sugarList > 0.0f) {
			System.out.println(sugarList + " grams of sugar");
			writeStr = writeStr + (sugarList + " grams of sugar\n");
		}
		if (eggsList > 0.0) {
			System.out.println(Math.round(eggsList) + " egg(s)");
			writeStr = writeStr + (Math.round(eggsList) + " egg(s)\n");
		}
		if (butterList > 0.0) {
			System.out.println(butterList + "grams of butter");
			writeStr = writeStr + (butterList + " grams of butter\n");
		}
		if (yeastList > 0.0) {
			System.out.println(yeastList + " grams of yeast");
			writeStr = writeStr + (yeastList + " grams of yeast\n");
		}
		anyOrders = false;
	}
	/**
	 * Provides the ability to check if there are any recipes added to the user's list. True if recipes present, false if not. 
	 * @return anyOrders boolean which states if there are any current recipes added to the user's list.
	 */
	public boolean getAnyOrders() {
		for ( Recipe recipe : recipes ) {
			if (recipe.getCounter() > 0) {
				anyOrders = true;
			}
		}
		return anyOrders;
	}
	/**
	 * Save functionality for the user. Saves user's shopping list to a txt file on their computer. Catches IOExceptions. 
	 */
	public void save() {
		
		try {
			/**
			 * New writer object used in printing out shopping list to txt file.
			 */
			FileWriter writer = new FileWriter("shoppinglist.txt");
			writer.write(writeStr);
			writer.close();
			System.out.println("List saved to shoppinglist.txt. Thank you for using the program!");
		} catch (IOException ioe) {
			System.out.println("Warning: Could not save list into a txt file. Below is the error.");
			ioe.printStackTrace();
		}
		
	}
	
}

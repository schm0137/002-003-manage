package mngr;
import java.util.InputMismatchException;
import java.util.Scanner;

/**		
 *	002-003-manage: This java file holds the main method for the manager application.
 *	Main.java
 *	@author Lily S.
 *	@version 1.0
 *	@since JDK 21.0.4 
*/
public class Main {
	/**
	 * main driver class for the recipe manager program. This is where the user's inputs are read, and the menu is displayed. 
	 * @param args (not used)
	 */
	public static void main(String[] args) {

		boolean menuCheck = true;
		try (Scanner scanner = new Scanner(System.in)) {
			RecipeManager recipeManager = new RecipeManager();

			recipeManager.load();
			
			/**
			 * Catches if text file is present, but no recipes are found.
			 */
			if (recipeManager.getRecipes().size() == 0) {
				System.out.println("Unable to find any recipes in the text file. \nPlease identify each individual recipe with the word \"Recipe\" on the title line and restart the program.");
				System.exit(0);
			} else {
				System.out.print("Recipes loaded.\n");
			}
			
			/**
			 * Menu loop, displays options, takes in user input, puts into switch statement to call appropriate actions. 
			 */
			while (menuCheck) {
				
				/** 
				 * String variable that takes in a users choice (read by scanner) after prompt, in this case, this is used during [Y/N] inputs.
				 */
				String menuChoiceStr;
				/** 
				 * Integer variable that takes in a users choice (read by scanner) after prompt, in this case, this is used during number related inputs.
				 */
				int menuChoiceInt = 0;
				/**
				 * Variable that is present in Create shopping list option. Used to take in user's requested recipe and passes it into RecipeManager's addList() method.
				 */
				int orderRecipe = 0;
				/**
				 * Variable that is present in Create shopping list option. Used to take in user's requested recipe amount and passes it into RecipeManager's addList() method.
				 */
				int orderAmount = 0;
				/**
				 * Boolean value that is used to keep the user in a input while loop until they provide an acceptable response.
				 */
				boolean intCheck = true;
			
				System.out.println("\nWelcome to Lily's recipe manager!");
				System.out.println("Please select one of the follow options:");
				System.out.println("1. Show available recipes.");
				System.out.println("2. Create shopping list.");
				System.out.println("3. Print shopping list.");
				System.out.println("4. Quit program.");
				System.out.println("0. To reprint this menu.\n");
				
				while (intCheck) {
					System.out.printf("Please enter your choice: ");
					try {
						menuChoiceInt = scanner.nextInt(); 
						if(menuChoiceInt < 0 || menuChoiceInt > 4) {
							System.out.println("Please enter a number between the expected range. (0-4)");
							scanner.nextLine();
						} else {
							intCheck = false;
							scanner.nextLine();
						}
					} catch(InputMismatchException ime) {
						System.out.println("Please use numbers only to navigate the menu.");
						scanner.nextLine();
					}
				}

				switch(menuChoiceInt) {
					case 0:
						break;
					case 1:
						recipeManager.showRecipe();
						intCheck = true;
						while (intCheck) {
							System.out.printf("\nEnter 1 to return to menu: ");
							try {
								menuChoiceInt = scanner.nextInt(); 
								if(menuChoiceInt < 1 || menuChoiceInt > 1) {
									System.out.println("Please enter a number between the expected range. (1)");
									scanner.nextLine();
								} else {
									intCheck = false;
									scanner.nextLine();
								}
							} catch(InputMismatchException ime) {
								System.out.println("Please use numbers only to navigate the menu.");
								scanner.nextLine();
							}
						}
						break;
						
					case 2:
						System.out.printf("\nThere are " + recipeManager.getRecipes().size() + " available recipes. Please enter your recipe choice: ");
						intCheck = true;
						while (intCheck) {
							try {
								orderRecipe = scanner.nextInt();				
								if(orderRecipe < 0 || orderRecipe - 1 < 0 || orderRecipe > recipeManager.getRecipes().size()) {
									System.out.println("Please enter a number between the available recipes. (" + recipeManager.getRecipes().size() + ")");
									scanner.nextLine();
								} else {
									intCheck = false;
									// remove's 1 off value to make user choice accurate to object arraylist which starts at 0. front-end displayed recipes start at 1 for easier experience on the user.
									orderRecipe = (orderRecipe - 1);
									scanner.nextLine();
								}
							} catch(InputMismatchException ime) {
								System.out.println("Please use numbers only to navigate the menu.");
								scanner.nextLine();
							}
						}
						System.out.printf("How many would you like? ");
						intCheck = true;
						while (intCheck) {
							try {
								orderAmount = scanner.nextInt(); 
								if(orderAmount > 1000) {
									System.out.println("Please enter a number no higher than 1000.");
									scanner.nextLine();
								} else {
									intCheck = false;
									
								}
							} catch(InputMismatchException ime) {
								System.out.println("Please use numbers only to navigate the menu.");
								scanner.nextLine();
							}
						}
						recipeManager.addList(orderRecipe, orderAmount);
						break;
					case 3:
						
						if (recipeManager.getAnyOrders() == false) {
							System.out.println("\nYou have nothing in your shopping cart. Please add something before asking to print.");
						} else {
							System.out.println("\nHere is your shopping list: \n");
							recipeManager.printList();
							
							System.out.println("\nWould you like to save this list? [Y/N]");
							intCheck = true;
							while (intCheck) {
								try {
									menuChoiceStr = scanner.nextLine().toLowerCase(); 
									if(!menuChoiceStr.equals("n") && !menuChoiceStr.equals("y") && !menuChoiceStr.equals("no") && !menuChoiceStr.equals("yes")) {
										System.out.println("Please enter a valid response. [Y/N]");
										
									} else if (menuChoiceStr.equals("y") || menuChoiceStr.equals("yes")){
										recipeManager.save();
										
										intCheck = false;
									} else if (menuChoiceStr.equals("n") || menuChoiceStr.equals("no")) {
										intCheck = false;
									} else {
										intCheck = false;
									}
								} catch(InputMismatchException ime) {
									System.out.println("Please use numbers only to navigate the menu.");
									scanner.nextLine();
								}
							}
							
							
							

						}
						break;
					case 4: 
						quitProgram();
						break;
				}
			}
		}
		

	}
	
	/**
	 * Quits programs and displays a fancy message telling the user goodbye.
	 */
	public static void quitProgram() {
		System.out.println("**********************************");
		System.out.println("*                                *");
		System.out.println("* Quitting program now, goodbye! *");
		System.out.println("*                                *");
		System.out.println("**********************************");
		System.exit(0);
	}
}



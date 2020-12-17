public class MinionGame {
	public static void main(String[] args) {

		double starterSelection;

		boolean controlNorbert = false;

		int starter;
		int choice = 0;
		String choosenSide;
		boolean choiceValid = false;
		String choiceTemp = "";
		
		


		System.out.println(order);
		System.out.println("Norbert steht an " + positionNorbert + ". Stelle.");
		System.out.println("Es stehen/steht " + (minionsRight) + " rechts.");
		System.out.println("Es stehen/ steht " + (minionsLeft) + " links.");

		/**
		 * Auswahl Spieler oder Computer
		 */

		starterSelection = Math.random();
		if (starterSelection >= 0.5)

		{
			System.out.println("Computer beginnt");
			starter = 0;

		} else {
			System.out.println("Spieler beginnt");
			starter = 1;
		}

		if (starter == 0) {
			/**
			 * Zug Computer
			 */
			System.out.println("Computer wählt");
		} else {
			/**
			 * Zug Mensch
			 */
			while (order.length() > 0) {

				while (choiceValid == false) {
					choice = 0;
					System.out.println(
							"Von welcher Seite willst du ziehen?(Eingabe 'l' für Links, Eingabe 'r' für Rechts");
					choiceTemp = StaticScanner.nextString().toLowerCase();
					while (true) {

						if (choiceTemp.equals("r") || choiceTemp.equals("l")) {
							choosenSide = choiceTemp;
							System.out.println("Wähle zwischen 1 und 3 Minions");
							choice = StaticScanner.nextInt();

							if (choice == 1 || choice == 2 || choice == 3) {
								if (choiceTemp.equals("r") && minionsRight >= choice
										|| choiceTemp.equals("l") && minionsLeft >= choice) {
									String side = "";
									if (choiceTemp.equals("l")) {
										side = "Links";
									} else if (choiceTemp.equals("r")) {
										side = "Rechts";
									}
									System.out.println("Du ziehst " + choice + " Minions von " + side);
									choiceValid = true;
									break;
								}

							} else {
								System.out.println("Du musst zwischen 1 und 3 Minions wählen!");
								choiceValid = false;
							}

						} else {
							System.out.println("Du musst l oder r eingeben um die Seite zu wählen");
						}
					}
				}

				/**
				 * Ende des Zuges durch Abziehen der gezogenen Minions von der Reihenfolge
				 */

				if (choiceTemp.equals("l")) {
					minionsLeft = minionsLeft - choice;

				} else if (choiceTemp.equals("r")) {
					minionsRight = minionsRight - choice;

				}
				order = "";
				for (int i = 0; i < minionsLeft; i++) {
					order += " M ";
				}
				order += " O ";
				for (int i = 0; i < minionsRight; i++) {
					order += " M ";
				}
				System.out.println("Verbleibende Minions:");
				System.out.println(order);
				
				choiceValid = false;
			}
		}
	}
	/**
	 * Methode Reihenfolge generieren
	 */
	public static String generateOrder(boolean controlNorbert) {
		String order = "";
		int positionNorbert = 0;
		int minionsLeft = 0;
		int minionsRight = 0;
		
		while (controlNorbert == false) {
			order = "";
			for (int i = 0; i < 11; i++) {
				if (controlNorbert == false) {
					double probability = Math.random() * i;
					if (probability < 0.85) {
						order += " M ";
					} else {
						order += " O ";
						controlNorbert = true;
						positionNorbert = i + 1;
						minionsLeft = positionNorbert - 1;
						minionsRight = 11 - positionNorbert;
					}
				} else {
					order += " M ";
				}
			}
		}
		return order;
	}
	
	public static int getPositionNorbert(String order) {
		
	}
}

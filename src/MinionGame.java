public class MinionGame {
	public static void main(String[] args) {

		double starterSelection;

		int choice = 0;
		String choosenSide;
		boolean choiceValid = false;

		String order = generateOrder();
		int positionNorbert = getPositionNorbert(order);
		int minionsLeft = getMinionsLeft(positionNorbert);
		int minionsRight = getMinionsRight(positionNorbert);
		int starter = getBeginner();

		// Ausgabe Aufbau der Reihe an Minions
		
		System.out.println(order);
		System.out.println("Norbert steht an " + getPositionNorbert(order) + ". Stelle.");
		System.out.println("Es stehen/steht " + minionsRight + " rechts.");
		System.out.println("Es stehen/ steht " + minionsLeft + " links.");

		movePlayer(order);
		/*if (starter == 1) {
			
		} else if (starter == 0) {
			System.out.println("Pc ist dran");
		} else {
			System.out.println("Fehler");
		}*/

		System.out.println("Zug beendet.");
	}

	/**
	 * Methode Reihenfolge generieren
	 */
	public static String generateOrder() {
		String order = "";
		boolean controlNorbert = false;

		while (controlNorbert == false) {
			order = "";
			for (int i = 0; i < 11; i++) {
				if (controlNorbert == false) {
					double probability = Math.random() * i;
					if (probability < 0.85) {
						order += "M";
					} else {
						order += "O";
						controlNorbert = true;
					}
				} else {
					order += "M";
				}
			}
		}
		return order;
	}

	// Methode Position Norbert feststellen

	public static int getPositionNorbert(String order) {
		// wemm -1 return, dann Norbert nicht dabei.
		int positionNorbert = -1;
		for (int i = 0; i < order.length(); i++) {
			if (order.charAt(i) == 'O') {
				positionNorbert = i + 1;
			}

		}
		return positionNorbert;
	}
	// Methode Minions links von Norbert berechnen

	public static int getMinionsLeft(int positionNorbert) {
		int minionsLeft = positionNorbert - 1;
		return minionsLeft;
	}

	// Methode Minions links von Norbert berechnen

	public static int getMinionsRight(int positionNorbert) {
		int minionsRight = 11 - positionNorbert;
		return minionsRight;
	}

	// Methode beginner festlegen

	public static int getBeginner() {
		double starterSelection;
		int starter;

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

		}
		return starter;
	}
	// Methode Zug Mensch

	public static void movePlayer(String order) {

		boolean choiceValid = false;
		int choice = 0;
		String choiceTemp = "";
		int minionsLeft = getMinionsLeft(getPositionNorbert(order));
		int minionsRight = getMinionsRight(getPositionNorbert(order));

		while (order.length() > 0) {

			while (choiceValid == false) {
				choice = 0;
				System.out.println("Von welcher Seite willst du ziehen?(Eingabe 'l' für Links, Eingabe 'r' für Rechts");
				choiceTemp = StaticScanner.nextString().toLowerCase();
				while (true) {

					if (choiceTemp.equals("r") || choiceTemp.equals("l")) {
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
								drawMinions(order, choice, choiceTemp);
								choiceValid = true;
								break;
							}

						} else {
							System.out.println("Du musst zwischen 1 und 3 Minions wählen!");
							choiceValid = false;
						}

					} else {
						System.out.println("Du musst l oder r eingeben um die Seite zu wählen");
						choiceValid = false;
					}
				}
			}
		}
	}

	public static void drawMinions(String order, int amount, String side) {
		int minionsLeft = getMinionsLeft(getPositionNorbert(order));
		int minionsRight = getMinionsRight(getPositionNorbert(order));
		int positionNorbert = order.indexOf('O');
		
		if (side.equals("l")) {
			minionsLeft = minionsLeft - amount;

		} else if (side.equals("r")) {
			minionsRight = minionsRight - amount;

		}
		
		for(int i = 0; i < order.length(); i++) {
			if(order.charAt(i) == 'M') {
				// Wenn von Links gezogen wird, sollen vom Anfang aus die Minions gezogen werden
				if(side.equals("l") && i < positionNorbert) {
					for(int j = i; j < i + amount; j++) {
						order = order.substring(0, j) + "-" + order.substring(j + 1);
					}
					break;
				}
				// Wenn von Rechts gezogen wird, sollen von außen aus die Minions gezogen werden
				else if(side.equals("r") && i == order.length() - amount) {
					for(int j = i; j < i + amount; j++) {
						order = order.substring(0, j) + "-" + order.substring(j + 1);
					}
					break;
				}
			}
		}
		
		System.out.println("Verbleibende Minions:");
		System.out.println(order);

	}

}

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
 
		// Ausgabe der durch Methoden gewonnenen Werte
		System.out.println(generateOrder());
		System.out.println("Norbert steht an " + getPositionNorbert(order) + ". Stelle.");
		System.out.println("Es stehen/steht " + minionsRight + " rechts.");
		System.out.println("Es stehen/ steht " + minionsLeft + " links.");
		System.out.println(getBeginner(order));
		/**
		 * Auswahl Spieler oder Computer
		 */

		System.out.println(movePlayer(order, minionsLeft, minionsRight));
	}

	/**
	 * Ende des Zuges durch Abziehen der gezogenen Minions von der Reihenfolge
	 */

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
		for (int i = 0; i < 11; i++) {
			if (order.charAt(i) == 'O') {
				positionNorbert = i;
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

	public static int getBeginner(String order) {
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

	public static int movePlayer(String order, int minionsLeft, int minionsRight) {

		boolean choiceValid = false;
		int choice = 0;
		String choiceTemp = "";
		String choosenSide;

		while (order.length() > 0) {

			while (choiceValid == false) {
				choice = 0;
				System.out.println("Von welcher Seite willst du ziehen?(Eingabe 'l' für Links, Eingabe 'r' für Rechts");
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
		}
		return choice;
	}

}

/**
 * if (choiceTemp.equals("l")) { minionsLeft = minionsLeft - choice;
 * 
 * } else if (choiceTemp.equals("r")) { minionsRight = minionsRight - choice;
 * 
 * } order = ""; for (int i = 0; i < minionsLeft; i++) { order += "M"; } order
 * += "O"; for (int i = 0; i < minionsRight; i++) { order += "M"; }
 * System.out.println("Verbleibende Minions:"); System.out.println(order);
 * 
 * choiceValid = false;
 */

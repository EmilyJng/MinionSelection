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
		// int computerMinionsTotal = getComputerMinionsTotal(choice,
		// computerMinionsTotal);
		// int playerMinionsTotal = getPlayerMinionsTotal(choice, playerMinionsTotal);

		// Ausgabe Aufbau der Reihe an Minions

		System.out.println(order);
		System.out.println("Norbert steht an " + getPositionNorbert(order) + ". Stelle.");
		System.out.println("Es stehen/steht " + minionsRight + " rechts.");
		System.out.println("Es stehen/ steht " + minionsLeft + " links.");

		while ((minionsLeft > 0) && (minionsRight > 0)) {
			if (starter == 1) {
				order = movePlayer(order);
				System.out.println("Zug beendet.");
				starter = 0;

			} else if (starter == 0) {
				order = moveComputer(order);
				System.out.println("Zug beendet.");
				starter = 1;
			} else {
				System.out.println("Fehler");

			}
			System.out.println(order);
		}
	}

	/**
	 * Methode Reihenfolge generieren
	 */

	public static String generateOrder() {
		String order = "";
		boolean controlNorbert = false;

		while (controlNorbert == false) {
			order = "";
			order += "M";
			for (int i = 0; i < 10; i++) {
				if (controlNorbert == false) {
					{
						double probability = Math.random();
						if (i == 8) {
							order += "O";
							controlNorbert = true;
						}
						if (probability < 0.85) {
							order += "M";
						} else {
							order += "O";
							controlNorbert = true;
						}
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

		return starter;
	}
	// Methode Zug Mensch

	public static String movePlayer(String order) {

		boolean choiceValid = false;
		int choice = 0;
		String choiceTemp = "";
		int minionsLeft = getMinionsLeft(getPositionNorbert(order));
		int minionsRight = getMinionsRight(getPositionNorbert(order));
		String newOrder = order;

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
							newOrder = drawMinions(order, choice, choiceTemp);
							System.out.println("erfolgreicher Zug vom Spieler");
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
		return newOrder;
	}

	public static String moveComputer(String order) {
		int minionsLeft = getMinionsLeft(getPositionNorbert(order));
		int minionsRight = getMinionsRight(getPositionNorbert(order));
		int choice = 0;
		String choiceSide = "";

		if ((minionsLeft == 1 || minionsLeft == 2 || minionsLeft == 3) && minionsLeft < minionsRight) {
			// hier noch 3,2 oder 1 auswählen
			choice = 1;
			choiceSide = "l";
			System.out.println("3,2 oder 1 von Links");

		} else if ((minionsRight == 1 || minionsRight == 2 || minionsRight == 3) && minionsLeft > minionsRight) {
			// hier noch 3,2 oder 1 auswählen
			choice = 1;
			choiceSide = "r";
			System.out.println("3,2 oder 1 von Rechts");
		} else {
			double probabilitySide = Math.random();
			double probabilityAmount = Math.random();
			if (probabilitySide > 0.5) {

				// links

				if (probabilityAmount <= 0.3) {

					choice = 1;
					choiceSide = "l";
					System.out.println("Ich nehme 1 Minion von Links");
				} else if (probabilityAmount > 0.3 && probabilityAmount <= 0.6) {

					choice = 2;
					choiceSide = "l";
					System.out.println("Ich nehme 2 Minions von Links");
				} else if (probabilityAmount > 0.6 && probabilityAmount < 1) {

					choice = 3;
					choiceSide = "l";
					System.out.println("Ich nehme 3 Minions von Links");
				}
			} else {

				// rechts

				if (probabilityAmount <= 0.3) {
					choice = 1;
					choiceSide = "r";

					System.out.println("Ich nehme 1 Minion von Rechts");
				} else if (probabilityAmount > 0.3 && probabilityAmount <= 0.6) {

					choice = 2;
					choiceSide = "r";
					System.out.println("Ich nehme 2 Minions von Rechts");
				} else if (probabilityAmount > 0.6 && probabilityAmount < 1) {

					choice = 3;
					choiceSide = "r";
					System.out.println("Ich nehme 3 Minions von Rechts");

				}
			}

		}

		System.out.println("Der Computer zieht " + choice + " Minions von " + choiceSide);
		return drawMinions(order, choice, choiceSide);

	}

	// eigene Minions vom Computer

	public static int getComputerMinionsTotal(int choice, int computerMinionsTotal) {

		computerMinionsTotal = computerMinionsTotal + choice;

		return computerMinionsTotal;
	}

	// eigene Minions vom Spieler

	public static int getPlayerMinionsTotal(int choice, int playerMinionsTotal) {

		playerMinionsTotal = playerMinionsTotal + choice;

		return playerMinionsTotal;
	}

	public static String drawMinions(String order, int amount, String side) {
		int minionsLeft = getMinionsLeft(getPositionNorbert(order));
		int minionsRight = getMinionsRight(getPositionNorbert(order));
		int positionNorbert = order.indexOf('O');

		if (side.equals("l")) {
			minionsLeft = minionsLeft - amount;

		} else if (side.equals("r")) {
			minionsRight = minionsRight - amount;

		}

		for (int i = 0; i < order.length(); i++) {
			if (order.charAt(i) == 'M') {
				// Wenn von Links gezogen wird, sollen vom Anfang aus die Minions gezogen werden
				if (side.equals("l") && i < positionNorbert) {
					for (int j = i; j < i + amount; j++) {
						order = order.substring(0, j) + "-" + order.substring(j + 1);
					}
					break;
				}
				// Wenn von Rechts gezogen wird, sollen von außen aus die Minions gezogen werden
				else if (side.equals("r") && i == order.length() - amount) {
					for (int j = i; j < i + amount; j++) {
						order = order.substring(0, j) + "-" + order.substring(j + 1);
					}
					break;
				}
			}
		}

		System.out.println("Verbleibende Minions:");
		return order;
	}

}

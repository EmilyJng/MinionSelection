public class MinionGame {

	public static void main(String[] args) {

		int positionNorbert = getPositionNorbert();
		int minionsLeft = getMinionsLeft(positionNorbert);
		int minionsRight = getMinionsRight(positionNorbert);
		int starter = getBeginner();
		String order = getOrder(positionNorbert);

		// Ausgabe der Position von Norbert,
		System.out.println("Norbert ist an Position " + (positionNorbert));

		// AUsgabe der Anzahl von Minions links
		System.out.println("Es sind " + getMinionsLeft(positionNorbert) + " Minions Links");

		// Ausgabe der Anzahl von Minions rechts
		System.out.println("Es sind " + getMinionsRight(positionNorbert) + " Minions Rechts");

		// Ausgabe der Reihenfolge der Minions mit "M" für Minion und "O" für Norbert
		System.out.println(getOrder(positionNorbert));

		// Beginner feststellen durch Aufrufen der Methode
		getBeginner();

		// Schleife für den Spielverlauf

		while ((minionsLeft > 0) && (minionsRight > 0)) {
			if (starter == 1) {
				order = movePlayer(order, positionNorbert);
				System.out.println("Player Zug beendet.");
				starter = 0;

			} else if (starter == 0) {
				// order = moveComputer(order);
				System.out.println("Computer Zug beendet.");
				starter = 1;
			} else {
				System.out.println("Fehler");

			}
			System.out.println(order);
		}

	}

	// Methoden

	// Methode für Position Norbert erhalten

	public static int getPositionNorbert() {
		int positionNorbert = 1;

		while (positionNorbert < 3 || positionNorbert > 9) {

			positionNorbert = (int) (Math.random() * 10);
		}

		return positionNorbert;

	}

	// Methode für Anzahl Minions links erhalten

	public static int getMinionsLeft(int positionNorbert) {
		int minionsLeft;

		minionsLeft = positionNorbert - 1;

		return minionsLeft;

	}

	// Methode für Minions rechts erhalten

	public static int getMinionsRight(int positionNorbert) {
		int minionsRight;

		minionsRight = 11 - positionNorbert;

		return minionsRight;

	}

	// Methode für Reihenfolge als String erstellen und oben aufrufen bzw anzeigen
	// können

	public static String getOrder(int positionNorbert) {
		String order = "";

		for (int i = 0; i < 11; i++) {
			if (i < positionNorbert) {
				order += " M ";
			} else if (i == positionNorbert) {
				order += " O ";
			} else if (i > positionNorbert) {
				order += " M ";
			} else {
				System.out.println("Fehler");
			}

		}
		return order;
	}

	// Methode um den Beginner festzulegen

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

	public static String movePlayer(String order, int positionNorbert) {

		boolean choiceValid = false;
		int choice = 0;
		String choiceTemp = "";
		int minionsLeft = getMinionsLeft(getPositionNorbert());
		int minionsRight = getMinionsRight(getPositionNorbert());
		String newOrder = order;
		int playerMinionsTotal = 0;

		while (choiceValid == false) {
			choice = 0;
			System.out.println("Von welcher Seite willst du ziehen?(Eingabe 'l' für Links, Eingabe 'r' für Rechts");
			choiceTemp = StaticScanner.nextString().toLowerCase();
			while (choiceValid = true) {

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
							newOrder = drawMinions(order, choice, choiceTemp, positionNorbert);
							System.out.println("erfolgreicher Zug vom Spieler");
							choiceValid = true;
							System.out.println("Du ziehst " + choice + " Minions von " + side);
							playerMinionsTotal = getMinionsTotal(choice, playerMinionsTotal);
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
		int minionsLeft = getMinionsLeft(getPositionNorbert());
		int minionsRight = getMinionsRight(getPositionNorbert());
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
		return drawMinions(order, choice, choiceSide, positionNorbert);

	}
	// Methode verrechnen der abgezogenen Minions mit der Reihenfolge
	public static String drawMinions(String order, int amount, String side, int positionNorbert) {
		int minionsLeft = getMinionsLeft(getPositionNorbert());
		int minionsRight = getMinionsRight(getPositionNorbert());
		int minionsLeftTaken = 0;
		int minionsRightTaken = 0;

		order = "";

		if (side.equals("l")) {
			minionsLeft = minionsLeft - amount;
			minionsLeftTaken = amount;

		} else if (side.equals("r")) {
			minionsRight = minionsRight - amount;
			minionsRightTaken = minionsRightTaken + amount;

		}

		for (int i = 0; i < minionsLeftTaken; i++) {
			order += " - ";
		}
		for (int j = 0; j < minionsLeft; j++) {
			order += " M ";
		}

		order += " O ";

		for (int l = 0; l < minionsRight; l++) {
			order += " M ";
		}
		for (int m = 0; m < minionsRightTaken; m++) {
			order += " - ";
		}

		return order;
	}

// eigene Minions mit eigenem Zug verrechnen

	public static int getMinionsTotal(int choice, int minionsTotal) {

		minionsTotal = minionsTotal + choice;

		return minionsTotal;
	}

}

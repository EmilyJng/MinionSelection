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
				order = movePlayer(order,positionNorbert);
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

		for (int i = 1; i < 12; i++) {
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

	// Methode verrechnen der abgezogenen Minions mit der Reihenfolge
	public static String drawMinions(String order, int amount, String side, int positionNorbert) {
		int minionsLeft = getMinionsLeft(getPositionNorbert());
		int minionsRight = getMinionsRight(getPositionNorbert());
	
		order = "";

		if (side.equals("l")) {
			minionsLeft = minionsLeft - amount;

		} else if (side.equals("r")) {
			minionsRight = minionsRight - amount;

		}

		for (int i = 1; i < 12; i++) {
			if ((i >= (positionNorbert - minionsLeft) && (i < positionNorbert)) || ((i < (positionNorbert + minionsRight) && (i > positionNorbert)))) {
				order += " M ";

			} else if (i < (positionNorbert - minionsLeft) || (i > (positionNorbert + minionsRight))) {
				order += " - ";

			} else if ( i == positionNorbert){
				order += " O ";

			}

		}

		System.out.println("Verbleibende Minions:");
		return order;
	}

}

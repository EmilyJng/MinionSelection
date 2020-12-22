public class MinionGame {

	public static void main(String[] args) {
		int positionNorbert = getPositionNorbert();
		int minionsLeft = getMinionsLeft(positionNorbert);
		int minionsRight = getMinionsRight(positionNorbert);
		int minionsLeftTaken = 0;
		int minionsRightTaken = 0;
		int starter = getBeginner();
		int minionsTotalPlayer = 0;
		int minionsTotalComputer = 0;

		// Ausgabe der Position von Norbert,
		System.out.println("Folgende Minions stehen zur Auswahl:");
		printOrder(positionNorbert, minionsLeft, minionsRight, minionsLeftTaken, minionsRightTaken);

		// Ausgabe der Position von Norbert,
		System.out.println("Norbert ist an Position " + (positionNorbert));

		// AUsgabe der Anzahl von Minions links
		System.out.println("Es stehen " + getMinionsLeft(positionNorbert) + " Minions Links");

		// Ausgabe der Anzahl von Minions rechts
		System.out.println("Es stehen " + getMinionsRight(positionNorbert) + " Minions Rechts");

		// Beginner feststellen durch Aufrufen der Methode


		// Schleife für den Spielverlauf

		while ((minionsLeft > 0) || (minionsRight > 0)) {
			if (starter == 1) {

				boolean choiceValid = false;
				int choice = 0;
				char side = 0;
				char takeNorbert = 'n';

				while (choiceValid == false) {
					choice = 0;
					System.out.println(
							"Von welcher Seite willst du ziehen?(Eingabe 'l' für Links, Eingabe 'r' für Rechts");
					side = StaticScanner.nextChar();
					while (choiceValid = true) {

						if (side == 'r' || side == 'l') {
							System.out.println("Wähle zwischen 1 und 3 Minions");
							choice = StaticScanner.nextInt();

							if (choice == 1 || choice == 2 || choice == 3) {
								if (side == 'r' && minionsRight >= choice || side == 'l' && minionsLeft >= choice) {

									if (side == 'l') {

										minionsLeft = minionsLeft - choice;
										minionsLeftTaken = minionsLeftTaken + choice;

									} else if (side == 'r') {

										minionsRight = minionsRight - choice;
										minionsRightTaken = minionsRightTaken + choice;
									}
									System.out.println("Du ziehst " + choice + " Minions von " + side);

									System.out.println("erfolgreicher Zug vom Spieler");
									choiceValid = true;
									System.out.println("Du ziehst " + choice + " Minions von " + side);
									minionsTotalPlayer = minionsTotalPlayer + choice;
									System.out.println("Du hast " + minionsTotalPlayer + " Minions in Total");

									break;
								}

							} else {
								if ((side == 'l') && (choice > minionsLeft)) {

									System.out.println("Du musst zwischen 1 und 3 Minions wählen!");
									choiceValid = false;
								} else if ((side == 'r') && (choice > minionsRight)) {

									System.out.println("Du musst zwischen 1 und 3 Minions wählen!");
									choiceValid = false;
								} else if ((minionsLeft < 1) || (minionsRight < 1)) {
									System.out.println("Auf dieser Seite ist nur noch Norbert übrig");
									System.out.println("Möchtest du wirklich Norbert ziehen? Du hast dann verloren!");
									System.out.println("Gebe 'y' für Ja und 'n' für nein ein:");
									takeNorbert = StaticScanner.nextChar();
									if (takeNorbert == 'n') {
										choiceValid = false;
									}

									else if (takeNorbert == 'y') {
										choiceValid = true;
									}
								}

							}

						} else {
							System.out.println("Du musst l oder r eingeben um die Seite zu wählen");
							choiceValid = false;
						}
					}

				}
			

				System.out.println("- Player Zug beendet. -");
				System.out.println("Folgende Minions sind noch übrig:");
				printOrder(positionNorbert, minionsLeft, minionsRight, minionsLeftTaken, minionsRightTaken);
				starter = 0;

			} else if (starter == 0) {
				int choice = 0;
				char side = 0;
				if ((minionsLeft == 1 || minionsLeft == 2 || minionsLeft == 3) && minionsLeft < minionsRight) {
					// hier wird entsprechend die 3,2 oder 1 übrigen von links ausgewählt
					choice = minionsLeft;
					side = 'l';


				} else if ((minionsRight == 1 || minionsRight == 2 || minionsRight == 3)
						&& minionsLeft > minionsRight) {
					// hier wird entsprechend die 3,2 oder 1 übrigen von rechts ausgewählt
					choice = minionsRight;
					side = 'r';

				} else {
					double probabilitySide = Math.random();
					double probabilityAmount = Math.random();
					if (probabilitySide > 0.5) {

						// links

						if (probabilityAmount <= 0.3) {
							choice = '1';
							if (minionsLeft >= 1) {

								side = 'l';

							} else {

								side = 'r';

							}
						} else if (probabilityAmount > 0.3 && probabilityAmount <= 0.6) {
							choice = 2;
							if (minionsLeft >= 2) {

								side = 'l';

							} else {

								side = 'r';

							}

							// System.out.println("Ich nehme 2 Minions von Links");
						} else if (probabilityAmount > 0.6 && probabilityAmount < 1) {
							choice = 3;
							if (minionsLeft >= 3) {

								side = 'l';

							} else {

								side = 'r';

							}
							// System.out.println("Ich nehme 3 Minions von Links");
						}
					} else {

						// rechts

						if (probabilityAmount <= 0.3) {
							choice = 1;
							if (minionsRight >= 1) {

								side = 'r';

							} else {

								side = 'l';

							}
							// System.out.println("Ich nehme 1 Minion von Rechts");
						} else if (probabilityAmount > 0.3 && probabilityAmount <= 0.6) {
							choice = 2;
							if (minionsRight >= 2) {

								side = 'r';

							} else {

								side = 'l';
							}
							// System.out.println("Ich nehme 2 Minions von Rechts");
						} else if (probabilityAmount > 0.6 && probabilityAmount < 1) {
							choice = 3;
							if (minionsRight >= 3) {

								side = 'r';

							} else {

								side = 'l';

							}
							// System.out.println("Ich nehme 3 Minions von Rechts");

						}

						
					}

				}
				System.out.println("Ich nehme " + choice + " Minion(s) von " + side);
				
				if (side == 'l') {
					minionsLeft = minionsLeft - choice;
					minionsLeftTaken = minionsLeftTaken + choice;
				} else if (side == 'r') {
					minionsRight = minionsRight - choice;
					minionsRightTaken = minionsRightTaken + choice;
				}
				System.out.println("Der Computer zieht " + choice + " Minion(s) von " + side);

				System.out.println("- Computer Zug beendet. -");
				System.out.println("Folgende Minions sind noch übrig:");
				printOrder(positionNorbert, minionsLeft, minionsRight, minionsLeftTaken, minionsRightTaken);
				starter = 1;
				minionsTotalComputer = minionsTotalComputer + choice;

			} else {
				System.out.println("Fehler");

			}

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

	public static void printOrder(int positionNorbert, int minionsLeft, int minionsRight, int minionsLeftTaken,
			int minionsRightTaken) {
		String order = "";

		for (int i = 0; i < minionsLeftTaken; i++) {
			order += "- ";
		}
		for (int i = 0; i < minionsLeft; i++) {
			order += "M ";
		}
		order += "O ";
		for (int i = 0; i < minionsRight; i++) {
			order += "M ";
		}
		for (int i = 0; i < minionsRightTaken; i++) {
			order += "- ";
		}
		System.out.println(order);

	}

	// Methode um den Beginner festzulegen

	public static int getBeginner() {
		double starterSelection;
		int starter;

		starterSelection = Math.random();
		if (starterSelection >= 0.5)

		{
			System.out.println("Der Computer beginnt diese Runde!");
			starter = 0;

		} else {
			System.out.println("Der Spieler beginnt diese Runde!");
			starter = 1;
		}

		return starter;
	}

}

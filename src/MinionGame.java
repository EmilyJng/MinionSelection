public class MinionGame {

	public static void main(String[] args) {
		int positionNorbert = getPositionNorbert();
		int minionsLeft = getMinionsLeft(positionNorbert);
		int minionsRight = getMinionsRight(positionNorbert);
		int minionsLeftTaken = 0;
		int minionsRightTaken = 0;

		int minionsTotalPlayer = 0;
		int minionsTotalComputer = 0;
		char hasNorbert = 'O';
		boolean gameOver = false;
		String choosenSide = "";

		//Spiel Erklärung und Regeln
        System.out.println("------------------- MISSION WITHOUT NORBERT ----------------------------------------------------" + "\n");
        System.out.println("Norbert, einst der Star der Minion Agenten, wurde nach seinem anfänglich rum Arrogant.\n"

                + "Seine letzten Missionen liefen durch seine Alleingänge alle miserabel.\n"
        		+ "\n"
                + "Sie, als Direktor der Minion Einheiten, haben die Aufgabe, Teams für die Einsätze zu erstellen.\n"
                + "Das Management ist aber nach den letzten Misserfolgen nicht sehr erfreut und stellt in Frage,\n"
                + "ob sie die nötigen Fähigkeiten haben ein Team zu erstellen.\n"
        		+ "\n"
                + "Beweisen Sie sich gegen den Computer, der Ihren Job ersetzten soll!!\n"
                + "Erstellen Sie dazu ein besseres Agententeam, als es der Computer tut!\n"
        		+ "\n"
                + "Alle Agenten haben die gleichen Qualifikationen, abgesehn von Norbert, \n"
                + "der dafür sorgen wird, dass die Mission scheitert.\n"
        		+ "\n"
                + "Spielregeln für die Teamauswahl:\n"
        		+ "\n"
                + "1. Es gibt 10 fähige Agenten. Und es gibt Norbert.\n"
                + "2. Norbert mischt sich anfangs zufällig unter die anderen Agenten.\n"
                + "3. Der Zufall entscheidet, ob Sie oder der Computer beginnen, Ihr Team zusammen zu stellen. \n"
                + "4. Danach werden abwechselnd von rechts oder links außen Minions gezogen.\n"
                + "5. Es muss mindestens 1 und maximal 3 Minions gezogen werden.\n"
                + "6. Wenn nur noch Norbert zur Auswahl steht, muss dieser gezogen werden.\n"
                + "7. Wer Norbert in seinem Team hat, hat verloren und bekommt den Job als Direktor nicht.\n"
                + "");

        System.out.println("-----------------------------GAME START-------------------------------------------------------\n");
        
		int starter = getBeginner();
		// Ausgabe der Position von Norbert,
		System.out.println("Folgende Minions stehen zur Auswahl:");
		printOrder(positionNorbert, minionsLeft, minionsRight, minionsLeftTaken, minionsRightTaken);
		
		// Ausgabe der Position von Norbert,
		System.out.println("Norbert steht an " + (positionNorbert) + ". Stelle.");

		// AUsgabe der Anzahl von Minions links
		System.out.println("Minions links: " + getMinionsLeft(positionNorbert));

		// Ausgabe der Anzahl von Minions rechts
		System.out.println("Minions rechts: " + getMinionsRight(positionNorbert) + "\n");

		// Beginner feststellen durch Aufrufen der Methode

		// Schleife für den Spielverlauf

		while (((minionsLeft > 0) || (minionsRight > 0)) && gameOver == false) {
			if (starter == 1) {
				int amount = 0;
				char side = '-';
				boolean sideInput = true;
				while (sideInput) {
					boolean minionAmountInput = true;
					System.out.println("Wähle eine Seite von der du ziehen möchtest. 'r' für Rechts, 'l' für Links");
					side = StaticScanner.nextChar();
					if (side == 'r' || side == 'l') {
						while (minionAmountInput) {
							System.out.println("Wähle zwischen 1 und 3 Minions! Wie viele möchtest du ziehen?");
							amount = StaticScanner.nextInt();
							if ((amount > 0 && amount < 4)) {
								if (side == 'r' && amount <= minionsRight || side == 'l' && amount <= minionsLeft) {
									if (side == 'r') {
										minionsRight = minionsRight - amount;
										minionsRightTaken = minionsRightTaken + amount;
										minionsTotalPlayer = minionsTotalPlayer + amount;
							
									} else if (side == 'l') {
										minionsLeft = minionsLeft - amount;
										minionsLeftTaken = minionsLeftTaken + amount;
										minionsTotalPlayer = minionsTotalPlayer + amount;
									}
									sideInput = false;
									starter = 0;
									break;
								} else {
									System.out.println("Möchtest du Norbert ziehen? (j/n)");
									char choice = StaticScanner.nextChar();
									if (choice == 'j') {
										gameOver = true;
										hasNorbert = 'p';

									} else {
										break;
									}
								}
							} else {
								System.out.println(
										"Es ist nicht möglich die gewählte Anzahl an Minions zu ziehen. Möchtest du eine andere Seite wählen? (j/n)");
								char choice = StaticScanner.nextChar();
								if (choice == 'j') {
									break;
								}
							}
						}
					}
				}
				if (side == 'l') {
					choosenSide = "links";
				}
				else {
					choosenSide = "rechts";
				}
				System.out.println("Du ziehst " + amount + " Minions von " + choosenSide + "\n");
				System.out.println("Aktuell hast du " + minionsTotalPlayer + " Minions in deinem Team \n");
				System.out.println ("- Spieler Zug beendet - \n");
				System.out.println("________________________________________________________________________\n");
				System.out.println("Folgende Minions sind noch übrig:");
				printOrder(positionNorbert, minionsLeft, minionsRight, minionsLeftTaken, minionsRightTaken);
				
				if ((minionsLeft == 0) && (minionsRight == 0)) {
					System.out.println(
							"Nur noch Norbert ist übrig. Der Computer muss bei seinem nächsten Zug Norbert ziehen. \n");
					hasNorbert = 'c';
					minionsTotalComputer = minionsTotalComputer + 1;
					System.out.println("Der Spieler gewinnt! \n");
					gameOver = true;
				}

			} else if (starter == 0) {
				int amount = 0;
				char side = '-';
				if (minionsLeft == 1 || minionsLeft == 2 || minionsLeft == 3) {
					// hier wird entsprechend die 3,2 oder 1 übrigen von links ausgewählt
					amount = minionsLeft;
					side = 'l';

				} else if (minionsRight == 1 || minionsRight == 2 || minionsRight == 3) {
					// hier wird entsprechend die 3,2 oder 1 übrigen von rechts ausgewählt
					amount = minionsRight;
					side = 'r';

				} else {
					double probabilitySide = Math.random();
					double probabilityAmount = Math.random();
					if (probabilitySide > 0.5) {

						// links

						if (probabilityAmount <= 0.3) {
							amount = 1;
							if (minionsLeft >= 1) {

								side = 'l';

							} else {

								side = 'r';

							}
						} else if (probabilityAmount > 0.3 && probabilityAmount <= 0.6) {
							amount = 2;
							if (minionsLeft >= 2) {

								side = 'l';

							} else {

								side = 'r';

							}

							// System.out.println("Ich nehme 2 Minions von Links");
						} else if (probabilityAmount > 0.6 && probabilityAmount < 1) {
							amount = 3;
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
							amount = 1;
							if (minionsRight >= 1) {

								side = 'r';

							} else {

								side = 'l';

							}
							// System.out.println("Ich nehme 1 Minion von Rechts");
						} else if (probabilityAmount > 0.3 && probabilityAmount <= 0.6) {
							amount = 2;
							if (minionsRight >= 2) {

								side = 'r';

							} else {

								side = 'l';
							}
							// System.out.println("Ich nehme 2 Minions von Rechts");
						} else if (probabilityAmount > 0.6 && probabilityAmount < 1) {
							amount = 3;
							if (minionsRight >= 3) {

								side = 'r';

							} else {

								side = 'l';

							}
							// System.out.println("Ich nehme 3 Minions von Rechts");

						}

					}

				}
				if (side == 'l') {
					choosenSide = "links";
				}
				else {
					choosenSide = "rechts";
				}
				System.out.println("Ich nehme " + amount + " Minion(s) von " + choosenSide + "! \n");

				if (side == 'l') {
					minionsLeft = minionsLeft - amount;
					minionsLeftTaken = minionsLeftTaken + amount;
				} else if (side == 'r') {
					minionsRight = minionsRight - amount;
					minionsRightTaken = minionsRightTaken + amount;
				}
				System.out.println("Der Computer zieht " + amount + " Minion(s) von " + choosenSide + "\n");
				minionsTotalComputer = minionsTotalComputer + amount;
				System.out.println("Der Computer hat aktuell " + minionsTotalComputer + " Minions in seinem Team \n");
				System.out.println("- Computer Zug beendet - \n");
				System.out.println("________________________________________________________________________\n");
				System.out.println("Folgende Minions sind noch übrig:");
				printOrder(positionNorbert, minionsLeft, minionsRight, minionsLeftTaken, minionsRightTaken);
				starter = 1;


				if ((minionsLeft == 0) && (minionsRight == 0)) {
					System.out
							.println("Nur noch Norbert ist übrig. Beim nächsten Zug muss der Spieler Norbert ziehen! \n");
					System.out.println("Der Computer gewinnt! \n");
					hasNorbert = 'p';
					minionsTotalPlayer = minionsTotalPlayer + 1;
					break;
				}
			} else {
				System.out.println("Fehler");

			}

		if(hasNorbert == 'c') {
			System.out.println("Der Computer hat Norbert gezogen!");
		}
		else if(hasNorbert == 'p') {
			System.out.println("Der Spieler hat Norbert gezogen! \n");
		}}
		System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n");

		System.out.println("Der Computer hat insgesamt " + minionsTotalComputer + " Minions in seinem Team. \n");
		System.out.println("Der Spieler hat insgesamt " + minionsTotalPlayer + " Minions in seinem Team. \n");

		System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n");
		
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
		
		System.out.println("\n" + order + "\n");
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
		System.out.println ("");
		return starter;
	}

}

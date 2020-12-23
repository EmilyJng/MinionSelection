public class MinionGame {

	public static void main(String[] args) {
		// variablen deklation und initialisierung
		// die Position von Norbert wird durch Aufrufen der Methode zufällig bestimmt
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

		// Spiel Erklärung und Regeln
		System.out.println(
				"------------------- MISSION WITHOUT NORBERT ----------------------------------------------------"
				+ "\n");
		System.out.println(
				"Norbert, einst der Star der Minion Agenten, wurde nach seinem anfänglich Ruhm Arrogant.\n"
				+ "Seine letzten Missionen liefen durch seine Alleingänge alle miserabel.\n" + "\n"
				+ "Sie, als Direktor der Minion Einheiten, haben die Aufgabe, Teams für die Einsätze zu erstellen.\n"
				+ "Das Management ist aber nach den letzten Misserfolgen nicht sehr erfreut und stellt in Frage,\n"
				+ "ob sie die nötigen Fähigkeiten haben, ein Team zu erstellen.\n" + "\n"
				+ "Beweisen Sie sich gegen den Computer, der Ihren Job ersetzten soll!!\n"
				+ "Erstellen Sie dazu ein besseres Agententeam, als es der Computer tut!\n" + "\n"
				+ "Alle Agenten haben die gleichen Qualifikationen, abgesehn von Norbert, \n"
				+ "der dafür sorgen wird, dass die Mission scheitert.\n" + "\n" + "Spielregeln für die Teamauswahl:\n"
				+ "\n" + "1. Es gibt 10 fähige Agenten. Und es gibt Norbert.\n"
				+ "2. Norbert mischt sich anfangs zufällig unter die anderen Agenten.\n"
				+ "3. Der Zufall entscheidet, ob Sie oder der Computer beginnen, Ihr Team zusammen zu stellen. \n"
				+ "4. Danach werden abwechselnd von rechts oder links außen Minions gezogen.\n"
				+ "5. Es muss mindestens 1 und maximal 3 Minions gezogen werden.\n"
				+ "6. Wenn nur noch Norbert zur Auswahl steht, muss dieser gezogen werden.\n"
				+ "7. Wer Norbert in seinem Team hat, hat verloren und bekommt den Job als Direktor nicht.\n" + "");

		System.out.println(
				"--------------------------GAME START----------------------------------------------------------\n");
		// Aufrufen der Methode um zufällig zu bestimmen, wer anfängt
		int starter = getBeginner();
		// Ausgabe der Position von Norbert,
		System.out.println("Folgende Minions stehen zur Auswahl:");
		printOrder(positionNorbert, minionsLeft, minionsRight, minionsLeftTaken, minionsRightTaken);

		// Ausgabe der Position von Norbert,
		System.out.println("Norbert steht an " + (positionNorbert) + ". Stelle.");

		// Ausgabe der Anzahl von Minions links
		System.out.println("Minions links: " + getMinionsLeft(positionNorbert));

		// Ausgabe der Anzahl von Minions rechts
		System.out.println("Minions rechts: " + getMinionsRight(positionNorbert) + "\n");

		// Beginner feststellen durch Aufrufen der Methode

		// Schleife für den Spielverlauf
		// Sie läuft solange, wie noch Minions vorhanden sind und keiner Norbert gezogen hat
		// Norbert gezogen würde gameOver auf true setzen

		while (((minionsLeft > 0) || (minionsRight > 0)) && gameOver == false) {
			// Spielerzug
			if (starter == 1) {
				int amount = 0;
				char side = '-';
				boolean sideInput = true;
				// Solange sideInput true ist, läuft die zweite Schleife
				while (sideInput) {
					boolean minionAmountInput = true;
					System.out.println("Wähle eine Seite von der du ziehen möchtest. 'r' für Rechts, 'l' für Links");
					side = StaticScanner.nextChar();
					if (side == 'r' || side == 'l') {
						//solange minionAmountInput true ist, läuft die dritte Schleife
						while (minionAmountInput) {
							System.out.println("Wähle zwischen 1 und 3 Minions! Wie viele möchtest du ziehen?");
							amount = StaticScanner.nextInt();
							// Da nur zwischen 1 und 3 Minions gewählt werden darf, muss amount größer als 0 und kleiner als 4 sein
							// Falls die Ausgewählte Zahl größer ist, landet man unten beim else und wird gefragt ob man die Seite neu wählen will
							if ((amount > 0 && amount < 4)) {
								// Abgleich der Auswahl mit den noch vorhandenen Minions
								if (side == 'r' && amount <= minionsRight || side == 'l' && amount <= minionsLeft) {
									if (side == 'r') {
										// Aktualisieren der Minions durch bisherige Anzahl - die ausgewählten Minions
										// Die "-" werden durch minionsRightTaken dargestellt
										// Die Aktualisierung ist wie bei den normalen Minions auf der Seite
										// Um am Ende sagen zu können, wie viele Minion jeder hat, werden diese über minionTotalPlayer gespeichert
										minionsRight = minionsRight - amount;
										minionsRightTaken = minionsRightTaken + amount;
										minionsTotalPlayer = minionsTotalPlayer + amount;

									} else if (side == 'l') {
										minionsLeft = minionsLeft - amount;
										minionsLeftTaken = minionsLeftTaken + amount;
										minionsTotalPlayer = minionsTotalPlayer + amount;
									}
									// egal ob links oder rechts wird starter als 0 gestellt, damit die else if Bedingung in der While Schleife erfüllt ist
									// Der Computer ist somit als nächstes dran
									// Durch sideInput false kommt man aus der dritten Schleife heraus
									sideInput = false;
									starter = 0;
									break;
								} else {
									// Mit der Eingabe würde man Norbert ziehen, deshalb Nachfrage
									// je nach Antwort neue Wahl der Seite oder Norbert ziehen hasNorbert wird 'p'-> Spiel vorbei
									System.out.println("Möchtest du Norbert ziehen? (j/n)");
									char choice = StaticScanner.nextChar();
									if (choice == 'j') {
										gameOver = true;
										sideInput = false;
										hasNorbert = 'p';
										break;

									} else {
										break;
									}
								}
							} else {
								// bei falscher Eingabe neue Eingabemöglichkeit durch Frage nach der Seite
								// break um wieder zur Wahl der Seite zu gelangen
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
				// um nicht nur r oder l in der Ausgabe zu haben, umwandeln in String choosenSide
				if (side == 'l') {
					choosenSide = "links";
				} else {
					choosenSide = "rechts";
				}
				
				// Ausgabe der gewählten Minions + Seite
				// Ausgabe der aktuellen Minions im eigenen Team
				System.out.println("Du ziehst " + amount + " Minions von " + choosenSide + "\n");
				System.out.println("Aktuell hast du " + minionsTotalPlayer + " Minions in deinem Team \n");
				System.out.println("- Spieler Zug beendet - \n");
				// Strich zur Übersichtlichkeit, Abgrenzung der Züge von Spieler und Computer
				System.out.println("________________________________________________________________________\n");
				// Ausgabe der aktuellen Minionreihe durch Aufrufen der Methode mit den aktuellen Werten
				System.out.println("Folgende Minions sind noch übrig:");
				printOrder(positionNorbert, minionsLeft, minionsRight, minionsLeftTaken, minionsRightTaken);

				// Wenn nur links und rechts keine Minions mehr stehen, also nur Norbert übrig, verliert der Computer, der als nächstes ziehen muss
				// hasNorbert wird 'c' zugewiesen um es am Ende ausgeben zu können
				if ((minionsLeft == 0) && (minionsRight == 0)) {
					System.out.println(
							"Nur noch Norbert ist übrig. Der Computer muss bei seinem nächsten Zug Norbert ziehen. \n");
					hasNorbert = 'c';
					// plus 1, da Norbert mit ins andere Team kommt
					minionsTotalComputer = minionsTotalComputer + 1;
					System.out.println("Der Spieler gewinnt! \n");
					System.out.println("Du hast dich gegen den Computer bewiesen und behälst deinen Job! \n");
					// true um aus der Schleife des Spielverlaufs zu gelangen
					gameOver = true;
				}
				// Computerzug
			} else if (starter == 0) {
				int amount = 0;
				char side = '-';
				// sofern auf einer Seite nur noch 1,2 oder 3 Minions stehen, wählt der Computer diese aus
				if (minionsLeft == 1 || minionsLeft == 2 || minionsLeft == 3) {
					// hier wird entsprechend die 3,2 oder 1 übrigen von links ausgewählt
					amount = minionsLeft;
					side = 'l';

				} else if (minionsRight == 1 || minionsRight == 2 || minionsRight == 3) {
					// hier wird entsprechend die 3,2 oder 1 übrigen von rechts ausgewählt
					amount = minionsRight;
					side = 'r';

				} else {
					// Wenn die Bedingung oben nicht zutrifft, wählt der Computer zufällig Seite und Anzahl der Minions aus
					double probabilitySide = Math.random();
					double probabilityAmount = Math.random();
					if (probabilitySide > 0.5) {

						// links

						if (probabilityAmount <= 0.3) {
							amount = 1;
							// Sofern auf der linken Seite genügend Minions stehen, wird dort 1 gewählt
							// Sind dort nicht genügend Minions, wird automatisch die Anzahl auf der rechten Seite gewählt
							// Das Gleiche gilt für amount 2 und 3
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

						} else if (probabilityAmount > 0.6 && probabilityAmount < 1) {
							amount = 3;
							if (minionsLeft >= 3) {

								side = 'l';

							} else {

								side = 'r';

							}
						}
					} else {

						// rechts
						// Gleiches verfahren wie bei links, nur umgekehrt

						if (probabilityAmount <= 0.3) {
							amount = 1;
							if (minionsRight >= 1) {

								side = 'r';

							} else {

								side = 'l';

							}
							
						} else if (probabilityAmount > 0.3 && probabilityAmount <= 0.6) {
							amount = 2;
							if (minionsRight >= 2) {

								side = 'r';

							} else {

								side = 'l';
							}

						} else if (probabilityAmount > 0.6 && probabilityAmount < 1) {
							amount = 3;
							if (minionsRight >= 3) {

								side = 'r';

							} else {

								side = 'l';

							}

						}

					}

				}
				if (side == 'l') {
					choosenSide = "links";
				} else {
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
				// Starter 1 zuweisen damit der Spieler als nächstes dran ist
				starter = 1;
				// falls keine Minions mehr links und rechts stehen, ist klar, dass der Spieler Norbert ziehen muss
				if ((minionsLeft == 0) && (minionsRight == 0)) {
					System.out.println(
							"Nur noch Norbert ist übrig. Beim nächsten Zug muss der Spieler Norbert ziehen! \n");
					System.out.println("Der Computer gewinnt! \n");
					System.out.println("Du verlierst leider deinen Job... \n");
					// zuweisung von hasNorbert für den Spieler
					hasNorbert = 'p';
					minionsTotalPlayer = minionsTotalPlayer + 1;
					break;
				}
			}
			// je nachdem was hasNorbert zugewiesen bekommen hat, Ausgabe für wer Norbert gezogen hat 
			if (hasNorbert == 'c') {
				System.out.println("Der Computer hat Norbert gezogen!");
			} else if (hasNorbert == 'p') {
				System.out.println("Der Spieler hat Norbert gezogen! \n");
			}
		}
		// Punkte zur visuellen Abgrenzung, dass das Spiel vorbei ist. Anschließende Ausgabe der Minions im jeweiligen Team
		System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n");

		System.out.println("Der Computer hat insgesamt " + minionsTotalComputer + " Minions in seinem Team. \n");
		System.out.println("Der Spieler hat insgesamt " + minionsTotalPlayer + " Minions in seinem Team. \n");

		System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n");
		System.out.println(
				"-----------------------------GAME END-------------------------------------------------------\n");

	}

	// Methoden

	// Methode für Position Norbert erhalten

	public static int getPositionNorbert() {
		int positionNorbert = 1;
		// Damit Norbert nicht ganz am Rand steht, begrenzung auf größer 3 und kleiner 9
		// Falls der Wert zufällig außerhalb des bereiches ist, läuft die Schleife erneut durch
		while (positionNorbert < 3 || positionNorbert > 9) {

			positionNorbert = (int) (Math.random() * 10);
		}

		return positionNorbert;

	}

	// Methode für den Anfangswert von den Minions links

	public static int getMinionsLeft(int positionNorbert) {
		int minionsLeft;

		minionsLeft = positionNorbert - 1;

		return minionsLeft;

	}

	//Methode für den Anfangswert von den Minions rechts

	public static int getMinionsRight(int positionNorbert) {
		int minionsRight;

		minionsRight = 11 - positionNorbert;

		return minionsRight;

	}

	// Methode für Reihenfolge aus den einzelnen Teilen erstellen und oben aufrufen bzw als String anzeigen
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
			// oben in der Schleife wird durch 0 und 1 gesagt, wer dran ist
			starter = 0;

		} else {
			System.out.println("Der Spieler beginnt diese Runde!");
			starter = 1;
		}
		System.out.println("\n");
		return starter;
	}

}

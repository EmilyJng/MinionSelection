public class MinionGame {
	public static void main(String[] args) {

		double starterSelection;
		String order = "";
		boolean controlNorbert = false;
		int positionNorbert = 0;
		int minionsLeft = 0;
		int minionsRight = 0;
		int starter;
		int choice = 0;
		String choosenSide;
		boolean choiceValid = false;

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
		System.out.println(order);
		System.out.println("Norbert steht an " + positionNorbert + ". Stelle.");
		System.out.println("Es stehen/steht " + (minionsRight) + " rechts.");
		System.out.println("Es stehen/ steht " + (minionsLeft) + " links.");

		starterSelection = Math.random();
		if (starterSelection >= 0.5)

		{
			System.out.print("Computer beginnt");
			starter = 0;

		} else {
			System.out.print("Spieler beginnt");
			starter = 1;
		}

		if (starter == 0) {
			System.out.print("Computer wählt");
		} else {

		}
		while (choiceValid == false) {
			choice = 0;

			System.out.print("Wähle zwischen 1 und 3 Minions");
			choice = StaticScanner.nextInt();

			if (choice == 1 || choice == 2 || choice == 3) {
				System.out.print("Von Links oder Rechts? (Eingabe 'l' für Links, Eingabe 'r' für Rechts");
				String choiceTemp = StaticScanner.nextString();

				if (choiceTemp.equals("r") || choiceTemp.equals("l")) {
					choosenSide = choiceTemp;
					choiceValid = true;

				} else {
					System.out.print("Du musst l oder r eingeben um die Seite zu wählen");
					choiceValid = false;
				}

			} else {
				System.out.print("Du musst zwischen 1 und 3 Minions wählen!");
				choiceValid = false;

			}
		}

	}
}

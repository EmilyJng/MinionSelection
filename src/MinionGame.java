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
		System.out.println("Es stehen " + (minionsRight) + " rechts.");
		System.out.println("Es stehen " + (minionsLeft) + " links.");

		/* starterSelection = Math.random();
		if (starterSelection >= 0.5)

		{
			System.out.print("Computer beginnt");
			starter = 0;
			
		} else {
			System.out.print("Spieler beginnt");
			starter = 1;
		}

		if (starter == 0)
		{System.out.print("Computer wählt");
		
		} else {
			choice = StaticScanner.nextInt();		
		}
		*/
	}
}

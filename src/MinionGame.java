
public class MinionGame {
	public static void main(String[] args) {

		double starterSelection;
		String order = "";
		boolean controlNorbert = false;

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
					}
				} else {
					order += " M ";
				}
			}
		}
		System.out.println(order);

		/*
		 * starterSelection=Math.random();if(starterSelection>=0.5)
		 * 
		 * { System.out.print("Computer beginnt"); }else {
		 * System.out.print("Spieler beginnt"); }
		 * neuer Kommentar
		 */

	}
}

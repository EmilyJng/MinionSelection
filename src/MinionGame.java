
public class MinionGame {
	public static void main(String[] args) {

		double starterSelection;
		String order = "";
		boolean controlNorbert = false;

		while (controlNorbert == false) {
			for (int i = 0; i < 11; i++) {
				if (controlNorbert == false) {
					double probability = Math.random();
					if (probability <= 0.5) {
						order += "M";
					} else {
						order += "N";
						controlNorbert = true;
					}
				} else {
					order += "M";
				}
			}
		}
		System.out.println(order);

		/*
		 * starterSelection=Math.random();if(starterSelection>=0.5)
		 * 
		 * { System.out.print("Computer beginnt"); }else {
		 * System.out.print("Spieler beginnt"); }
		 */

	}
}

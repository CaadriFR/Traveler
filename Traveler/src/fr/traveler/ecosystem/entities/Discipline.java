package fr.traveler.ecosystem.entities;

/**
 * Représente les disciplines de recherche disponibles dans l'écosystème.
 * 
 * @author Adrien Riffaut
 */
public enum Discipline {

	/**
	 * Mathématiques.
	 */
	MATHEMATICS("Mathematics"),

	/**
	 * Informatique.
	 */
	COMPUTER_SCIENCE("Computer Science"),

	/**
	 * Gestion.
	 */
	MANAGEMENT("Management"),

	/**
	 * Droit.
	 */
	LAW("Law"),

	/**
	 * Sciences sociales.
	 */
	SOCIAL_SCIENCES("Social Sciences");

	/**
	 * Nom en chaîne de caractère pour un bon affichage dans la console.
	 */
	private String cleanName;

	/**
	 * Construit une discipline avec le nom en chaîne de caractère
	 * 
	 * @param cleanName un nom en chaîne de caractère pour un bon affichage dans la
	 *                  console
	 */
	Discipline(String cleanName) {
		this.cleanName = cleanName;
	}

	/**
	 * Retourne une description textuelle de la discipline.
	 * 
	 * @return une chaîne décrivant la discipline
	 */
	@Override
	public String toString() {
		return cleanName;
	}
}

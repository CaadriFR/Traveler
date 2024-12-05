package fr.traveler.ecosystem.entities;

public enum Discipline {
    MATHEMATICS("Mathematics"),
    COMPUTER_SCIENCE("Computer Science"),
    MANAGEMENT("Management"),
    LAW("Law"),
    SOCIAL_SCIENCES("Social Sciences");

	private String cleanName;
	
	Discipline (String cleanName) {
		this.cleanName = cleanName;
	}
	
    @Override
    public String toString() {
        return cleanName;
    }
}

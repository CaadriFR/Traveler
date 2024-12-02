package fr.traveler.ecosystem;

public enum Discipline {
    MATHEMATICS,
    COMPUTER_SCIENCE,
    MANAGEMENT,
    LAW,
    SOCIAL_SCIENCES;

    @Override
    public String toString() {
        return name().replace("_", " ").toLowerCase();
    }
}

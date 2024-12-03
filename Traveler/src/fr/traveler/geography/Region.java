package fr.traveler.geography;

import java.util.Collections;
import java.util.List;

public enum Region {
	AUVERGNE_RHONE_ALPES("Lyon", 69711.0, 8197296,
			List.of("01", "03", "07", "15", "26", "38", "42", "43", "63", "69", "73", "74")),
	BOURGOGNE_FRANCHE_COMTE("Dijon", 47784.0, 2810480, List.of("21", "25", "39", "58", "70", "71", "89", "90")),
	BRETAGNE("Rennes", 27208.0, 3379224, List.of("22", "29", "35", "56")),
	CENTRE_VAL_DE_LOIRE("Orléans", 39151.0, 2559078, List.of("18", "28", "36", "37", "41", "45")),
	CORSE("Ajaccio", 8680.0, 351255, List.of("2A", "2B")),
	GRAND_EST("Strasbourg", 57433.0, 5555262, List.of("08", "10", "51", "52", "54", "55", "57", "67", "68", "88")),
	HAUTS_DE_FRANCE("Lille", 31813.0, 5980697, List.of("02", "59", "60", "62", "80")),
	ILE_DE_FRANCE("Paris", 12011.0, 12358932, List.of("75", "77", "78", "91", "92", "93", "94", "95")),
	NORMANDIE("Rouen", 29906.0, 3317023, List.of("14", "27", "50", "61", "76")),
	NOUVELLE_AQUITAINE("Bordeaux", 84061.0, 6110365,
			List.of("16", "17", "19", "23", "24", "33", "40", "47", "64", "79", "86", "87")),
	OCCITANIE("Toulouse", 72724.0, 6101005,
			List.of("09", "11", "12", "30", "31", "32", "34", "46", "48", "65", "66", "81", "82")),
	PAYS_DE_LA_LOIRE("Nantes", 32082.0, 3907426, List.of("44", "49", "53", "72", "85")),
	PROVENCE_ALPES_COTE_D_AZUR("Marseille", 31400.0, 5160091, List.of("04", "05", "06", "13", "83", "84")),
	UNKNOWN("Unknown", 0, 0, Collections.emptyList());

	private final String capitalCity;
    private final double area;
    private final int population;
    private final List<String> departments;

    Region(String capitalCity, double area, int population, List<String> departments) {
        this.capitalCity = capitalCity;
        this.area = area;
        this.population = population;
        this.departments = departments;
    }
    
    public String getCapitalCity() {
    	return capitalCity;
    }

    @Override
    public String toString() {
        return "Region [Name=" + name() + ", Capital City=" + capitalCity + ", Area=" + area + " km², Population=" 
                + population + ", Departments=" + departments + "]";
    }

    public static Region findRegionByCity(City city) {
        for (Region region : values()) {
            if (region.departments.contains(city.getDepartment())) {
                return region;
            }
        }
        return Region.UNKNOWN;
    }
}
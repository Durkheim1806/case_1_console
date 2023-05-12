package marktplaats.model;


public enum Bezorgwijze {
    VERSTUREN("versturen"), AFHALEN("afhalen"), REMBOURS("rembours"), MAGAZIJN("magazijn"), VERZEKERDE_VERZENDING("verzekerde verzekering");

    private String shortName;

    Bezorgwijze(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Bezorgwijze fromShortName(String shortName) {
        switch (shortName) {
            case "versturen":
                return Bezorgwijze.VERSTUREN;
            case "afhalen":
                return Bezorgwijze.AFHALEN;
            case "rembours":
                return Bezorgwijze.REMBOURS;
            case "magazijn":
                return Bezorgwijze.MAGAZIJN;
            case "verzekerde verzending":
                return Bezorgwijze.VERSTUREN;
            default:
                throw new IllegalArgumentException("ShortName [" + shortName
                        + "] not supported.");
        }
    }
}

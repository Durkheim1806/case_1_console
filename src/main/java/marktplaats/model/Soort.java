package marktplaats.model;

public enum Soort {
    PRODUCT("product"), DIENST("dienst");

    private String shortName;

    private Soort(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Soort fromShortName(String shortName) {
        switch (shortName) {
            case "product":
                return Soort.PRODUCT;

            case "dienst":
                return Soort.DIENST;

            default:
                throw new IllegalArgumentException("ShortName [" + shortName
                        + "] not supported.");
        }
    }

}

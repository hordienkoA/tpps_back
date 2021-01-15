package ua.cn.stu.tpps.buyfly.values;

public enum Country {
    USA("United States"),
    GBR("United Kingdom"),
    UKR("Ukraine"),
    ARE("United Arab Emirates"),
    POL("Poland"),
    FIN("Finland"),
    FRA("France");

    private final String countryName;

    Country(String countryName) {
        this.countryName = countryName;
    }

    public String getFullName() {
        return countryName;
    }
}

package ua.cn.stu.tpps.buyfly.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.cn.stu.tpps.buyfly.values.Country;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "getCityByName", query = "SELECT c FROM City c WHERE c.name = ?1"),
    @NamedQuery(name = "getCitiesByCountry", query = "SELECT c FROM City c WHERE c.country = ?1"),
    @NamedQuery(name = "getCitiesByAirline", query = "SELECT c FROM City c, Airline a WHERE a.name =  ?1")
})
public class City extends DomainSuperClass implements Serializable {

    @NotNull
    @Size(max = 35)
    private String name;

    @NotNull
    @Size(max = 70)
    private String country;

    @OneToMany(targetEntity = Airport.class, mappedBy = "city", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Airport> airports;

    public City() {
        super();
    }


    public String getName() {
        return name;
    }

    @SuppressWarnings("SameParameterValue")
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        // return Country.valueOf(country).getFullName();
        return this.country;
    }

    @SuppressWarnings("SameParameterValue")
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        for (Country c : Country.values()) {
            if (c.getFullName().equals(country)) {
                return c.toString();
            }
        }

        return "";
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }
}

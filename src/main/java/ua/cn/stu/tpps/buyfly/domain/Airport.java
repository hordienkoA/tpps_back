package ua.cn.stu.tpps.buyfly.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NamedQueries({
    @NamedQuery(name = "getByCountry", query = "SELECT a FROM Airport a, City c WHERE c.name = ?1"),
    @NamedQuery(name = "getByCity", query = "SELECT a FROM Airport a, City c WHERE c.country= ?1")
})
@JsonIgnoreProperties("cityId")
public class Airport extends DomainSuperClass implements Serializable {

    @NotNull
    @Size(max = 70)
    private String name;

    @Size(min = 4, max = 4)
    private String ICAO; // International Civil Aviation Organization airport code

    @Size(min = 3, max = 3)
    private String IATA; // International Air Transport Association airport code

    @NotNull
    private double taxPrice;

    @ManyToOne(targetEntity = City.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;


    public Airport() {
        super();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getICAO() {
        return ICAO;
    }

    public void setICAO(String ICAO) {
        this.ICAO = ICAO;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}

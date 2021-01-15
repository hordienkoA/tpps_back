package ua.cn.stu.tpps.buyfly.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NamedQuery(name = "getAirlineByAircraft",
    query = "SELECT x FROM Airline x WHERE x.id IN (SELECT c.airline.id FROM Aircraft c WHERE c.id = ?1)")
public class Airline extends DomainSuperClass implements Serializable {

    @NotNull
    @Size(max = 50)
    private String name;

    @Size(min = 3, max = 3)
    private String ICAO; // International Civil Aviation Organization airline designator

    @Size(min = 2, max = 2)
    private String IATA; // International Air Transport Association airline designator


    public Airline() {
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

}

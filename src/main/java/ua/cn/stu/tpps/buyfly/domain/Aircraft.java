package ua.cn.stu.tpps.buyfly.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(name = "getFlightsForAircraft", query = "select x from Flight x where x.aircraft.id = ?1")
@JsonIgnoreProperties("airlineId")
public class Aircraft extends DomainSuperClass implements Serializable {

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String model;

    @OneToMany(targetEntity = Seat.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "aircraft_id")
    private List<Seat> seats;

    @OneToOne(targetEntity = Airline.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_id")
    private Airline airline;


    public Aircraft() {
        super();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void addSeats(Seat seat) {
        this.seats.add(seat);
    }

    public void addSeats(List<Seat> seats) {
        this.seats.addAll(seats);
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}

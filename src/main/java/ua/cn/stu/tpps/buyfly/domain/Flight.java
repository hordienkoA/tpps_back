package ua.cn.stu.tpps.buyfly.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.hibernate.validator.constraints.NotEmpty;
import ua.cn.stu.tpps.buyfly.util.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("JpaQlInspection")
@Entity
@NamedQueries({
    @NamedQuery(name = "getByDirection",
        query = "SELECT f FROM Flight f WHERE f.originAirport.city.id = ?1 AND f.destinationAirport.city.id = ?2"),
    @NamedQuery(name = "getBetweenDates", query = "SELECT f FROM Flight f WHERE f.departureTime BETWEEN ?1 AND ?2"),
    @NamedQuery(name = "getByAircraft", query = "SELECT f FROM Flight f WHERE f.aircraft.id = ?1"),
    @NamedQuery(name = "getByDatesAndAircraft",
        query = "SELECT f FROM Flight f WHERE f.departureTime BETWEEN ?1 AND ?2 AND f.aircraft.id = ?3"),
    @NamedQuery(name = "getByCountries",
        query = "SELECT f FROM Flight f WHERE f.originAirport.city.country = ?1 AND f.destinationAirport.city.country = ?2"),
    @NamedQuery(name = "getByAirports",
        query = "SELECT f FROM Flight f WHERE f.originAirport.id = ?1 AND f.destinationAirport.id = ?2"),
})
@JsonIgnoreProperties({
    "originAirportId",
    "destinationAirportId",
    "aircraftId"
})
public class Flight extends DomainSuperClass implements Serializable {

    @SuppressWarnings("FieldCanBeLocal")
    @Transient
    private final String HOURS_FORMAT = "k:mm";

    @NotNull
    @NotEmpty
    @Column(unique = true)
    @Size(max = 15)
    private String number;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime boardingTime;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime departureTime;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime arrivalTime;

    @OneToOne(targetEntity = Airport.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "origin_airport_id")
    private Airport originAirport;

    @OneToOne(targetEntity = Airport.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dest_airport_id")
    private Airport destinationAirport;

    @NotNull
    @Size(max = 30)
    private String gate;

    @OneToOne(targetEntity = Aircraft.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;


    public Flight() {
        super();
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(LocalDateTime boardingTime) {
        this.boardingTime = boardingTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDuration() {
        Duration duration = Duration.between(departureTime, arrivalTime);
        LocalTime time = LocalTime.MIDNIGHT.plus(duration);

        return DateTimeFormatter.ofPattern(HOURS_FORMAT).format(time);
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }
}

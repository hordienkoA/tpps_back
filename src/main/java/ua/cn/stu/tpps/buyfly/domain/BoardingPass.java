package ua.cn.stu.tpps.buyfly.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.hibernate.annotations.ColumnDefault;
import ua.cn.stu.tpps.buyfly.util.LocalDateTimeAttributeConverter;
import ua.cn.stu.tpps.buyfly.values.BoardingPassStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
    @NamedQuery(name = "setStatus", query = "UPDATE BoardingPass b SET b.status = ?1 WHERE b.id = ?2"),
    @NamedQuery(name = "getByStatus", query = "SELECT b FROM BoardingPass b WHERE b.status = ?1"),
    @NamedQuery(name = "getByFlight", query = "SELECT b FROM BoardingPass b WHERE b.flight.id = ?1"),
    @NamedQuery(name = "getByStatusAndFlight",
        query = "SELECT b FROM BoardingPass b WHERE b.status = ?1 AND b.flight.id = ?2"),
    @NamedQuery(name = "getByCustomer",
        query = "SELECT b FROM BoardingPass b WHERE b.customer.id = ?1"),
    @NamedQuery(name = "getCurrentByCustomer",
        query = "SELECT b FROM BoardingPass b WHERE b.customer.id = ?1 AND b.bookingDate >= ?2 ORDER BY b.bookingDate DESC"),
    @NamedQuery(name = "getCompletedByCustomer",
        query = "SELECT b FROM BoardingPass b WHERE b.customer.id = ?1 AND b.bookingDate <= ?2 ORDER BY b.bookingDate DESC")
})
@JsonIgnoreProperties({
    "userId",
    "flightId",
    "seatId"
})
public class BoardingPass extends DomainSuperClass implements Serializable {

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime bookingDate;

    @NotNull
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User customer;

    @NotNull
    private String status;

    @NotNull
    @OneToOne(targetEntity = Flight.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToOne(targetEntity = Seat.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @NotNull
    @ColumnDefault(value = "0.0")
    private double checkedLuggagePrice;

    @NotNull
    @ColumnDefault(value = "0.0")
    private double additionalWeightPrice;


    public BoardingPass() {
        super();
    }


    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(BoardingPassStatus status) {
        this.status = status.toString();
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public double getCheckedLuggagePrice() {
        return checkedLuggagePrice;
    }

    public void setCheckedLuggagePrice(double checkedLuggagePrice) {
        this.checkedLuggagePrice = checkedLuggagePrice;
    }

    public double getAdditionalWeightPrice() {
        return additionalWeightPrice;
    }

    public void setAdditionalWeightPrice(double additionalWeightPrice) {
        this.additionalWeightPrice = additionalWeightPrice;
    }

    public double getTotalPrice() {
        return seat.getPrice()
            + checkedLuggagePrice
            + additionalWeightPrice
            + flight.getDestinationAirport().getTaxPrice()
            + flight.getOriginAirport().getTaxPrice();
    }

}

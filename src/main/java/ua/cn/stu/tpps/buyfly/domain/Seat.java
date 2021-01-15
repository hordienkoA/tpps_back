package ua.cn.stu.tpps.buyfly.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnDefault;
import ua.cn.stu.tpps.buyfly.values.SeatClass;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NamedQueries({

    //TODO Fix queries. Add aircraft id

    @NamedQuery(name = "getByClass", query = "SELECT s FROM Seat s WHERE s.seatClass = ?1"),
    @NamedQuery(name = "getFreeSeats", query = "SELECT s FROM Seat s WHERE NOT s.taken = TRUE"),
    @NamedQuery(name = "getFreeByPriceRange", query = "SELECT s FROM Seat s WHERE NOT s.taken = TRUE AND s.price BETWEEN ?1 AND ?2")
})
@JsonIgnoreProperties("aircraftId")
public class Seat extends DomainSuperClass implements Serializable {

    @NotNull
    @Size(max = 10)
    private String number;

    @NotNull
    @Size(max = 20)
    private String seatClass;

    @NotNull
    @ColumnDefault(value = "0")
    private boolean taken;

    @NotNull
    private double price;


    public Seat() {
        super();
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeatClass() {
        return SeatClass.valueOf(seatClass).getSeatClass();
    }

    @SuppressWarnings("SameParameterValue")
    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass.toString();
    }

    public String getSeatClassCode() {
        return seatClass;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

package ua.cn.stu.tpps.buyfly.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Superclass for all domain objects. Contains entity unique identifier.
 */
@MappedSuperclass
public abstract class DomainSuperClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public DomainSuperClass() {
    }

    public DomainSuperClass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

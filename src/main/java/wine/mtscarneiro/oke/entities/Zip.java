package wine.mtscarneiro.oke.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb.zip")
public class Zip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer startZip;
    private Integer endZip;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Zip(){}

    public Zip(Long id, Integer startZip, Integer endZip, Store store) {
        this.id = id;
        this.startZip = startZip;
        this.endZip = endZip;
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartZip() {
        return startZip;
    }

    public void setStartZip(Integer startZip) {
        this.startZip = startZip;
    }

    public Integer getEndZip() {
        return endZip;
    }

    public void setEndZip(Integer endZip) {
        this.endZip = endZip;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zip zip = (Zip) o;
        return Objects.equals(getId(), zip.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Zip{" +
                "id=" + id +
                ", startZip=" + startZip +
                ", endZip=" + endZip +
                ", store=" + store +
                '}';
    }
}



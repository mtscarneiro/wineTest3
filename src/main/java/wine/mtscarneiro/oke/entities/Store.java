package wine.mtscarneiro.oke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb.stores")
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String storeCode;

    @JsonIgnore
    @OneToMany(mappedBy = "store")
    private List<Zip> deliverRange = new ArrayList<>();

    public Store() {
    }

    public Store(Long id, String storeCode, List<Zip> deliverRange) {
        this.id = id;
        this.storeCode = storeCode;
        this.deliverRange = deliverRange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public List<Zip> getDeliverRange() {
        return deliverRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(getId(), store.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", storeCode= " + storeCode +
                '}';
    }
}

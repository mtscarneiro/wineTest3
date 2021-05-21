package wine.mtscarneiro.oke.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_near_store")
@NoArgsConstructor
public class NearestStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ownZip;
    @Lob
    private Store nearStore;

    public NearestStore(Long id, Integer ownZip, Store nearStore) {
        this.id = id;
        this.ownZip = ownZip;
        this.nearStore = nearStore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOwnZip() {
        return ownZip;
    }

    public void setOwnZip(Integer ownZip) {
        this.ownZip = ownZip;
    }

    public Store getNearStore() {
        return nearStore;
    }

    protected void findIt(Store s) {
        Integer start = s.getDeliverRange().get(0).getStartZip();
        Integer end = s.getDeliverRange().get(0).getEndZip();

        if (s.getDeliverRange() != null) {
            if (getOwnZip() >= start) {
                if (getOwnZip() <= end) {
                    setNearStore(s);
                } else {
                    setNearStore(null);
                }
            }
        }
    }

    public void setNearStore(Store s) {
        findIt(s);
        this.nearStore = s;
    }

























    @Override
    public String toString() {
        return "NearestStore{" +
                "id=" + id +
                ", ownZip=" + ownZip +
                ", nearStore=" + nearStore +
                '}';
    }
}
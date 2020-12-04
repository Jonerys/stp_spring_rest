package jonerys.test.springcrud.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "warehouses", schema = "goods")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false, length = 200)
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void addGood(Good good){
        goods.add(good);
    }

    @ManyToMany( cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Goodswh",
            joinColumns = { @JoinColumn(name = "id_wh", referencedColumnName="id") },
            inverseJoinColumns = { @JoinColumn(name = "id_gd", referencedColumnName="id") }
    )
    private List<Good> goods;
}

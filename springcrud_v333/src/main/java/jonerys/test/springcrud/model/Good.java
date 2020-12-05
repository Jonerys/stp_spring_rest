package jonerys.test.springcrud.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "goods_main", schema = "goods")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Good {
    @Id
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

    @ManyToMany( cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Goodswh",
            joinColumns = { @JoinColumn(name = "id_gd", referencedColumnName="id") },
            inverseJoinColumns = { @JoinColumn(name = "id_wh", referencedColumnName="id") }
    )
    @JsonIgnore
    List<Warehouse> warehouses;

    @Override
    public String toString() {
        return id + " " + name;
    }
}

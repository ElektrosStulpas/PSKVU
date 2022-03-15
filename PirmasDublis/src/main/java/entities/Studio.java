package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "STUDIO")
@Getter @Setter
public class Studio implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "CITY")
    private String city;

    @OneToMany(mappedBy = "studio")
    private List<Game> games = new ArrayList<>();

    public Studio() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studio studio = (Studio) o;
        return Objects.equals(id, studio.id) &&
                Objects.equals(name, studio.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

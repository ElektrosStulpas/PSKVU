package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PLAYER")
@Getter @Setter
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "HANDLE", unique = true)
    private String handle;

    @ManyToMany(mappedBy = "players")
    private List<Game> games = new ArrayList<>();

    public Player()
    {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(handle, player.handle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, handle);
    }
}

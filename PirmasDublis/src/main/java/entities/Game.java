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
@Table(name = "GAME")
@Getter @Setter
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name="STUDIO")
    private Studio studio;

    @ManyToMany
    @JoinTable(name="GAME_PLAYER")
    private List<Player> players = new ArrayList<>();

    public Game() {
    }

    public void addPlayer(Player player)
    {
        this.players.add(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

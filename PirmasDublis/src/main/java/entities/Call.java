package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CALL")
@Getter @Setter
public class Call implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ANSWERED")
    private boolean answered;

    @ManyToOne
    @JoinColumn(name="EMPLOYEE_ID")
    private Employee employee;

    public Call() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Call call = (Call) o;
        return Objects.equals(id, call.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

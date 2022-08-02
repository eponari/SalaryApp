package al.WeWebTask.Model.Entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="off_days")
@NoArgsConstructor
public class OffDay {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @Column(name="date")
    Date date;

    public OffDay(Date date) {
        this.date = date;
    }
}

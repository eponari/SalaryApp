package al.WeWebTask.Model.Entity;

import al.WeWebTask.Model.DTO.WorkingDayDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="WORKING_DAYS")
@NoArgsConstructor
@Getter
public class WorkingDay {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @Column(name="USER_ID")
    String user_id;

    @Column(name="DATE")
    Date date;

    @Column(name="HOURS")
    int hours;

    public WorkingDay(String user_id, Date date, int hours) {
        this.user_id = user_id;
        this.date = date;
        this.hours = hours;
    }

    public WorkingDay(WorkingDayDTO workingDayDTO) {
        this.user_id = workingDayDTO.getUser_id();
        this.date = workingDayDTO.getDate();
        this.hours = workingDayDTO.getHours();
    }
}

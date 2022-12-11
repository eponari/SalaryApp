package al.WeWebTask.Model.Entity;

import al.WeWebTask.Model.DTO.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
public class User {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @NotNull(message = "Name is empty.")
    @Column(name="full_name")
    String fullName;

    @NotNull(message = "Wage is empty.")
    @Column(name="total_paga")
    double totalPaga;

    @Column(name="created_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    Timestamp date;

    public User(UserDTO userDTO) {
        this.fullName = userDTO.getFullName();
        this.totalPaga = userDTO.getWage();
        this.date = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
    }

}

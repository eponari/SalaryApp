package al.WeWebTask.Model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
public class WorkingDayDTO {
    String user_id;
    Date date;
    int hours;
}

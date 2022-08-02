package al.WeWebTask.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WageReport {
    String fullName;
    int normalHours;
    int extraHours;
    int totalHours;
    double totalWage;
}

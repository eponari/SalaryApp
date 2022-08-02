package al.WeWebTask.Model.Repository;

import al.WeWebTask.Model.Entity.WorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface WorkingDaysRepository extends JpaRepository<WorkingDay,Integer> {
    @Query(value = "SELECT * FROM working_days where user_id=?1",nativeQuery = true)
    ArrayList<WorkingDay> getWorkingDayById(Integer id);
}

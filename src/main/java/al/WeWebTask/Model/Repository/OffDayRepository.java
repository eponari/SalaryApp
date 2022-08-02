package al.WeWebTask.Model.Repository;

import al.WeWebTask.Model.Entity.OffDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface OffDayRepository extends JpaRepository<OffDay,Integer> {
    @Query(value = "SELECT COUNT(*) from OFF_DAYS WHERE DATE=?1",nativeQuery = true)
    int isOffDay(Date date);
}

package al.WeWebTask.Controller;

import al.WeWebTask.Model.DTO.OffDayDTO;
import al.WeWebTask.Model.DTO.WorkingDayDTO;
import al.WeWebTask.Model.Entity.OffDay;
import al.WeWebTask.Model.Entity.WorkingDay;
import al.WeWebTask.Model.Repository.OffDayRepository;
import al.WeWebTask.Model.Repository.WorkingDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/manageDays")
public class DayController {

    OffDayRepository offDayRepository;
    WorkingDaysRepository workingDaysRepository;

    @Autowired
    public DayController(OffDayRepository offDayRepository, WorkingDaysRepository workingDaysRepository) {
        this.offDayRepository = offDayRepository;
        this.workingDaysRepository = workingDaysRepository;
    }

    @PostMapping("/createOffDay")
    ResponseEntity<OffDay> createOffDay(@RequestBody OffDayDTO offDayDTO){
        try{
            OffDay newOffDay = new OffDay(offDayDTO.getDate());
            offDayRepository.save(newOffDay);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertWorkingDay")
    ResponseEntity<WorkingDay> insertWorkingDay(@RequestBody WorkingDayDTO workingDayDTO){
        try{
            WorkingDay workingDay = new WorkingDay(workingDayDTO);

            workingDaysRepository.save(workingDay);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

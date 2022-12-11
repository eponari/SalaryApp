package al.WeWebTask.Controller;

import al.WeWebTask.Model.DTO.UserDTO;
import al.WeWebTask.Model.DTO.WageReport;
import al.WeWebTask.Model.Entity.User;
import al.WeWebTask.Model.Entity.WorkingDay;
import al.WeWebTask.Model.Repository.OffDayRepository;
import al.WeWebTask.Model.Repository.UserRepository;
import al.WeWebTask.Model.Repository.WorkingDaysRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;

@RestController
@RequestMapping("/manageUsers")
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);
    UserRepository userRepository;
    WorkingDaysRepository workingDaysRepository;
    OffDayRepository offDayRepository;

    @Autowired
    public UserController(UserRepository userRepository, WorkingDaysRepository workingDaysRepository, OffDayRepository offDayRepository) {
        this.userRepository = userRepository;
        this.workingDaysRepository = workingDaysRepository;
        this.offDayRepository = offDayRepository;
    }

    @PostMapping("/createUser")
    ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO){
        try{
            User user = new User(userDTO);
            userRepository.save(user);
            return new ResponseEntity<User>(user,HttpStatus.CREATED);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getReport")
    ResponseEntity<ArrayList<WageReport>> getReport(){
        try{
            ArrayList<WageReport> wageReports = new ArrayList<>();

            for(User user:userRepository.findAll()){
                WageReport wageReport = getWageReport(user);
                wageReports.add(wageReport);
            }

            return new ResponseEntity<>(wageReports, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    WageReport getWageReport(User user){
        Calendar calendar = Calendar.getInstance();

        int regularWorkingHours = 0;
        int extraWorkingHours = 0;
        double totalWage = 0;
        double dailyWage = user.getTotalPaga()/22.0;

        for(WorkingDay workingDay:workingDaysRepository.getWorkingDayById(user.getId())){
            int currentWorkingHours = (workingDay.getHours()>8?8:workingDay.getHours());
            int currentExtraWorkingHours = (workingDay.getHours()>8?workingDay.getHours()-8:0);

            regularWorkingHours+=currentWorkingHours;
            extraWorkingHours+=currentExtraWorkingHours;

            calendar.setTime(workingDay.getDate());

            System.out.println(workingDay.getDate().toString());

            if(offDayRepository.isOffDay(workingDay.getDate())>0){
                totalWage+= 1.5*currentWorkingHours + 2*currentExtraWorkingHours;
                log.info("HOLIDAY ");
            }else if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                totalWage+= 1.25*currentWorkingHours + 1.5*currentExtraWorkingHours;
                log.info("WEEKEND ");
            }else{
                totalWage+= currentWorkingHours + 1.25*currentExtraWorkingHours;
                log.info("WEEKDAY ");
            }
            log.info(user.getId()+" "+currentWorkingHours+" "+currentExtraWorkingHours);
        }

        return new WageReport(user.getFullName(),regularWorkingHours,extraWorkingHours,regularWorkingHours+extraWorkingHours,Math.round(totalWage*dailyWage*100)/100.0);
    }

}

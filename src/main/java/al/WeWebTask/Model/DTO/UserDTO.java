package al.WeWebTask.Model.DTO;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {
    @NotEmpty(message = "Name is empty.")
    String fullName;
    @NotNull
    double wage;
}

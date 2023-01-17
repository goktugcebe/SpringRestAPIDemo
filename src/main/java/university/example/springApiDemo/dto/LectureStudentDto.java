package university.example.springApiDemo.dto;

import lombok.*;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "NOTE < 101")
public class LectureStudentDto {

    private long id;

    private int absent;

    private int note;

}

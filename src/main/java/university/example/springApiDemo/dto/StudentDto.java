package university.example.springApiDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private long id;

    private String studentName;

    private long studentNumber;

    private long studentAge;

    private Set<LectureStudentDto> lectureStudentDtos;

}

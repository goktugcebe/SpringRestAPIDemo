package university.example.springApiDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LectureDto {

    private long id;

    private String lectureName;

    private Set<LectureSubjectDto> lectureSubjectDtos;

    private Set<LectureStudentDto> lectureStudentDtos;
}
package university.example.springApiDemo.dto;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDto {

    private long id;

    private String lecturerName;

    private int lecturerAge;

    private Set<LectureDto> lectureDtos;

}
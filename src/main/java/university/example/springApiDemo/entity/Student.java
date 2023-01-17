package university.example.springApiDemo.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 50)
    private String studentName;

    @Column(unique = true)
    private long studentNumber;

    @Column(length = 2)
    private long studentAge;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private Set<LectureStudent> lectureStudent;
}
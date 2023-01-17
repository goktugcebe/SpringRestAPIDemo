package university.example.springApiDemo.entity;



import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;


@Entity(name = "lecture_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
// note can't be greater than 100
@Check(constraints = "NOTE < 101")

public class LectureStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int absent;

    private int note;

    @ManyToOne
    // To give a name to foreign key
    @JoinColumn(foreignKey = @ForeignKey(name = "STUDENT_FK"))
    private Student student;

    @ManyToOne
    // To give a name to foreign key
    @JoinColumn(foreignKey = @ForeignKey(name = "LECTURE_FK"))
    private Lecture lecture;
}

package university.example.springApiDemo.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "lecturer")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50,nullable = false)
    private String lecturerName;

    @Column(nullable = false)
    private int lecturerAge;

    @OneToMany(mappedBy = "lecturer",cascade = CascadeType.ALL)
    private Set<Lecture> lectures;

}

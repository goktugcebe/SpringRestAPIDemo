package university.example.springApiDemo.entity;



import lombok.*;
import javax.persistence.*;
import java.util.Set;

    @Entity(name = "lecture")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Lecture {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(nullable = false,length = 20)
        private String lectureName;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(foreignKey = @ForeignKey(name = "LECTURER_FK"))
        @ToString.Exclude
        private Lecturer lecturer;

        @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
        @ToString.Exclude
        private Set<LectureSubject> lectureSubject;

        @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
        @ToString.Exclude
        private Set<LectureStudent> lectureStudent;


}

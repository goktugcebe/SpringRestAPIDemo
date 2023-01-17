package university.example.springApiDemo.entity;




import lombok.*;

import javax.persistence.*;

@Entity(name = "lecture_subject")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class LectureSubject {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(length = 200,nullable = false)
        private String subjectName;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(foreignKey = @ForeignKey(name = "LECTURE_FK"))
        private Lecture lecture;


}

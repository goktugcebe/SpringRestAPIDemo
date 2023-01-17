package university.example.springApiDemo.repository;

import university.example.springApiDemo.entity.LectureSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureSubjectRepository extends JpaRepository<LectureSubject,Long> {
}

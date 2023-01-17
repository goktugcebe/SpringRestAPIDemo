package university.example.springApiDemo.repository;

import university.example.springApiDemo.entity.LectureStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureStudentRepository extends JpaRepository<LectureStudent,Long> {
}

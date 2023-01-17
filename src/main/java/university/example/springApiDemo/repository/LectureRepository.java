package university.example.springApiDemo.repository;

import university.example.springApiDemo.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture,Long> {

}

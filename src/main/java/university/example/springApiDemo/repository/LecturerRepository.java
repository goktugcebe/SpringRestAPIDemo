package university.example.springApiDemo.repository;

import university.example.springApiDemo.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer,Long> {

}

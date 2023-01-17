package university.example.springApiDemo.controller;

import university.example.springApiDemo.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import university.example.springApiDemo.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getall")
    public List<StudentDto> getAll(){
        return studentService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public Optional<StudentDto> getById(@PathVariable long id){
        return studentService.getById(id);
    }

    @PostMapping("/add")
    public void add(StudentDto studentDto){
        studentService.add(studentDto);
    }

    @DeleteMapping("/delete")
    public void delete(StudentDto studentDto){
        studentService.delete(studentDto);
    }

    @PutMapping("/update")
    public void update(StudentDto studentDto){
        studentService.update(studentDto);
    }

}

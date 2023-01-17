package university.example.springApiDemo.controller;

import university.example.springApiDemo.dto.LectureSubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import university.example.springApiDemo.service.LectureSubjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lecturesubject")
public class LectureSubjectController {

    private LectureSubjectService lectureSubjectService;
    @Autowired
    public LectureSubjectController(LectureSubjectService lectureSubjectService) {
        this.lectureSubjectService = lectureSubjectService;
    }

    @GetMapping("/getall")
    public List<LectureSubjectDto> getAll(){
        return lectureSubjectService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public Optional<LectureSubjectDto> getById(@PathVariable long id){
        return lectureSubjectService.getById(id);
    }

    @PostMapping("/add")
    public void add(LectureSubjectDto lectureSubjectDto){
        lectureSubjectService.add(lectureSubjectDto);
    }

    @DeleteMapping("/delete")
    public void delete(LectureSubjectDto lectureSubjectDto){
        lectureSubjectService.delete(lectureSubjectDto);
    }

    @PutMapping("/update")
    public void update(LectureSubjectDto lectureSubjectDto){
        lectureSubjectService.update(lectureSubjectDto);
    }
}

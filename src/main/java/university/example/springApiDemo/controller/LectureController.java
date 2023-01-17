package university.example.springApiDemo.controller;

import university.example.springApiDemo.dto.LectureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import university.example.springApiDemo.service.LectureService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lecture")
public class LectureController {

    private LectureService lectureService;
    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/getall")
    public List<LectureDto> getAll() {
        return lectureService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public Optional<LectureDto> getById(@PathVariable long id) {
        return lectureService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody LectureDto lectureDto) {
        lectureService.add(lectureDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody LectureDto lectureDto) {
        lectureService.delete(lectureDto);
    }

    @PutMapping("/update")
    public void update(@RequestBody LectureDto lectureDto){
        lectureService.update(lectureDto);

    }

}

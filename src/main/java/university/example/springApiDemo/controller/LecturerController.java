package university.example.springApiDemo.controller;

import university.example.springApiDemo.dto.LecturerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import university.example.springApiDemo.service.LecturerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lecturer")
public class LecturerController {

    private LecturerService lecturerService;
    @Autowired
    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping("/getall")
    public List<LecturerDto> getAll(){
    return lecturerService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public Optional<LecturerDto> getById(@PathVariable long id){
        return lecturerService.getById(id);
    }

    @PostMapping("/add")
    public void add(LecturerDto lecturerDto){
        lecturerService.add(lecturerDto);
    }

    @DeleteMapping("/delete")
    public void delete(LecturerDto lecturerDto){
        lecturerService.delete(lecturerDto);
    }

    @PutMapping("/update")
    public void update(LecturerDto lecturerDto){
        lecturerService.update(lecturerDto);
    }

}

package university.example.springApiDemo.service.impl;

import university.example.springApiDemo.dto.LectureDto;
import university.example.springApiDemo.dto.LecturerDto;
import university.example.springApiDemo.entity.Lecture;
import university.example.springApiDemo.entity.Lecturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import university.example.springApiDemo.repository.LecturerRepository;
import university.example.springApiDemo.service.LecturerService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;
    private final LectureServiceImpl lectureService;
    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository, LectureServiceImpl lectureService) {
        this.lecturerRepository = lecturerRepository;
        this.lectureService = lectureService;
    }

    @Override
    public List<LecturerDto> getAll() {
        List<LecturerDto> lecturerDtosList = new ArrayList<>();
        lecturerRepository.findAll().forEach(lecturer -> {
            LecturerDto lecturerDto = new LecturerDto();
            entityToDto(lecturer, lecturerDto);
            lecturerDtosList.add(lecturerDto);
        });
        return lecturerDtosList;
    }

    @Override
    public Optional<LecturerDto> getById(long id) {
        Optional<LecturerDto> lecturerDto = Optional.of(new LecturerDto());
        Lecturer lecturer = lecturerRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecturer couldn't find!"));
        entityToDto(lecturer, lecturerDto.get());
        return lecturerDto;
    }

    @Override
    public LecturerDto add(LecturerDto lecturerDto) {
        Lecturer lecturer = new Lecturer();
        dtoToEntity(lecturerDto, lecturer);
        lecturer = lecturerRepository.save(lecturer);
        entityToDto(lecturer, lecturerDto);
        return lecturerDto;
    }

    @Override
    public void delete(LecturerDto lecturerDto) {
        Lecturer lecturer = new Lecturer();
        dtoToEntity(lecturerDto, lecturer);
        lecturerRepository.delete(lecturer);
    }

    @Override
    public LecturerDto update(LecturerDto lecturerDto) {
        Optional<LecturerDto> lecturerDtoOptional = Optional.of(getById(lecturerDto.getId()).orElseThrow());
        lecturerDtoOptional.get().setLecturerName(lecturerDto.getLecturerName());
        Lecturer lecturer = new Lecturer();
        dtoToEntity(lecturerDto, lecturer);
        entityToDto(lecturerRepository.save(lecturer), lecturerDto);
        return lecturerDto;
    }




    private void dtoToEntity(LecturerDto lecturerDto, Lecturer lecturer) {

        lecturer.setId(lecturerDto.getId());
        lecturer.setLecturerName(lecturerDto.getLecturerName());
        lecturer.setLecturerAge(lecturerDto.getLecturerAge());

        if (!CollectionUtils.isEmpty(lecturerDto.getLectureDtos())) {
            lecturerDto.getLectureDtos().forEach(lectureDto -> {
                Lecture lecture = new Lecture();
                lectureService.dtoToEntity(lectureDto, lecture);
                if (CollectionUtils.isEmpty(lecturer.getLectures()))
                    lecturer.setLectures(new HashSet<>());
                lecture.setLecturer(lecturer);
                lecturer.getLectures().add(lecture);
            });
        }

    }

    private void entityToDto(Lecturer lecturer, LecturerDto lecturerDto) {
        lecturerDto.setId(lecturer.getId());
        lecturerDto.setLecturerName(lecturer.getLecturerName());
        lecturerDto.setLecturerAge(lecturerDto.getLecturerAge());

        if (!CollectionUtils.isEmpty(lecturer.getLectures()))
            lecturer.getLectures().forEach(lecture -> {
                LectureDto lectureDto = new LectureDto();
                lectureService.
                        entityToDto(lecture, lectureDto);
                if (CollectionUtils.isEmpty(lecturerDto.getLectureDtos()))
                    lecturerDto.setLectureDtos(new HashSet<>());
                lecturerDto.getLectureDtos().add(lectureDto);
            });
    }


}

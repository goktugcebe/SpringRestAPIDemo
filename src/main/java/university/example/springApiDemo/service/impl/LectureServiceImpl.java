package university.example.springApiDemo.service.impl;

import university.example.springApiDemo.dto.LectureDto;
import university.example.springApiDemo.dto.LectureSubjectDto;
import university.example.springApiDemo.entity.Lecture;
import university.example.springApiDemo.entity.LectureSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import university.example.springApiDemo.repository.LectureRepository;
import university.example.springApiDemo.service.LectureService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class LectureServiceImpl implements LectureService {
    @Autowired
    private final LectureRepository lectureRepository;
    private final LectureSubjectServiceImpl lectureSubjectService;
    @Autowired
    public LectureServiceImpl(LectureRepository lectureRepository, LectureSubjectServiceImpl lectureSubjectService) {
        this.lectureRepository = lectureRepository;
        this.lectureSubjectService = lectureSubjectService;
    }


    @Override
    public List<LectureDto> getAll() {

        List<LectureDto> lectureDtoList = new ArrayList<>();
        lectureRepository.findAll().forEach(lecture -> {
            LectureDto lectureDto = new LectureDto();
            entityToDto(lecture, lectureDto);
            lectureDtoList.add(lectureDto);
        });

        return lectureDtoList;
    }

    @Override
    public Optional<LectureDto> getById(long id) {

        Optional<LectureDto> lectureDto = Optional.of(new LectureDto());
        Optional<Lecture> lecture = lectureRepository.findById(id);
        entityToDto(lecture.orElseGet(Lecture::new), lectureDto.get());
        return lectureDto;
    }

    @Override
    public LectureDto add(LectureDto lectureDto) {

        Lecture lecture = new Lecture();
        dtoToEntity(lectureDto, lecture);
        lectureRepository.save(lecture);
        entityToDto(lecture, lectureDto);
        return lectureDto;
    }

    @Override
    public void delete(LectureDto lectureDto) {

        Lecture lecture = new Lecture();
        dtoToEntity(lectureDto, lecture);
        lectureRepository.delete(lecture);
    }

    @Override
    public LectureDto update(LectureDto lectureDto) {

        Optional<LectureDto> optionalDers = Optional.of(getById(lectureDto.getId()).orElseThrow());
        Lecture lecture = new Lecture();
        dtoToEntity(optionalDers.get(), lecture);
        entityToDto(lectureRepository.save(lecture), lectureDto);
        return lectureDto;
    }

    protected void dtoToEntity(LectureDto lectureDto, Lecture lecture) {

        lecture.setId(lectureDto.getId());
        lecture.setLectureName(lectureDto.getLectureName());

        if (!CollectionUtils.isEmpty(lectureDto.getLectureSubjectDtos())) {
            lectureDto.getLectureSubjectDtos().forEach(lectureSubjectDto -> {
                LectureSubject lectureSubject = new LectureSubject();
                lectureSubjectService.dtoToEntity(lectureSubjectDto, lectureSubject);
                lectureSubject.setLecture(lecture);

                if (CollectionUtils.isEmpty(lecture.getLectureSubject()))
                    lecture.setLectureSubject(new HashSet<>());
                lecture.getLectureSubject().add(lectureSubject);
            });
        }
    }

    protected void entityToDto(Lecture lecture, LectureDto lectureDto) {

        lectureDto.setId(lecture.getId());
        lectureDto.setLectureName(lecture.getLectureName());

        if (!CollectionUtils.isEmpty(lecture.getLectureSubject())) {
            lecture.getLectureSubject().forEach(lectureSubject -> {
                LectureSubjectDto lectureSubjectDto = new LectureSubjectDto();
                lectureSubjectService.entityToDto(lectureSubject, lectureSubjectDto);
                if (CollectionUtils.isEmpty(lectureDto.getLectureSubjectDtos()))
                    lectureDto.setLectureSubjectDtos(new HashSet<>());
                lectureDto.getLectureSubjectDtos().add(lectureSubjectDto);
            });
        }
    }
}
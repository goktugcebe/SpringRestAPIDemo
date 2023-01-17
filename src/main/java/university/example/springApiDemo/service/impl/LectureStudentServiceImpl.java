package university.example.springApiDemo.service.impl;

import university.example.springApiDemo.dto.LectureStudentDto;
import university.example.springApiDemo.entity.LectureStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import university.example.springApiDemo.repository.LectureStudentRepository;
import university.example.springApiDemo.service.LectureStudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

    @Service
    public class LectureStudentServiceImpl implements LectureStudentService {

        private final LectureStudentRepository lectureStudentRepository;
        @Autowired
        public LectureStudentServiceImpl(LectureStudentRepository lectureStudentRepository) {
            this.lectureStudentRepository = lectureStudentRepository;
        }

        @Override
        public List<LectureStudentDto> getAll() {
            List<LectureStudentDto> lectureStudentDtoList = new ArrayList<>();
            lectureStudentRepository.findAll().forEach(LectureStudent -> {
                LectureStudentDto lectureStudentDto = new LectureStudentDto();
                entityToDto(LectureStudent, lectureStudentDto);
                lectureStudentDtoList.add(lectureStudentDto);
            });
            return lectureStudentDtoList;
        }

        @Override
        public Optional<LectureStudentDto> getById(long id) {

            Optional<LectureStudentDto> lectureStudentDto = Optional.of(new LectureStudentDto());
            Optional<LectureStudent> lectureStudent = lectureStudentRepository.findById(id);
            entityToDto(lectureStudent.orElseGet(LectureStudent::new), lectureStudentDto.get());
            return lectureStudentDto;
        }

        @Override
        public LectureStudentDto add(LectureStudentDto lectureStudentDto) {

            LectureStudent lectureStudent = new LectureStudent();
            dtoToEntity(lectureStudentDto,lectureStudent);
            lectureStudent = lectureStudentRepository.save(lectureStudent);
            entityToDto(lectureStudent, lectureStudentDto);
            return lectureStudentDto;
        }

        @Override
        public void delete(LectureStudentDto lectureStudentDto) {

            LectureStudent lectureStudent = new LectureStudent();
            dtoToEntity(lectureStudentDto,lectureStudent);
            lectureStudentRepository.delete(lectureStudent);
        }

        @Override
        public LectureStudentDto update(LectureStudentDto lectureStudentDto) {

            Optional<LectureStudentDto> optionalLectureStudent = Optional.of(getById(lectureStudentDto.getId()).orElseThrow());
            LectureStudent lectureStudent = new LectureStudent();
            dtoToEntity(optionalLectureStudent.get(), lectureStudent);
            entityToDto(lectureStudentRepository.save(lectureStudent), lectureStudentDto);
            return lectureStudentDto;
        }

        protected void dtoToEntity(LectureStudentDto lectureStudentDto, LectureStudent lectureStudent) {

            lectureStudent.setId(lectureStudentDto.getId());
            lectureStudent.setAbsent(lectureStudentDto.getAbsent());
            lectureStudent.setNote(lectureStudentDto.getNote());

        }

        protected void entityToDto(LectureStudent lectureStudent, LectureStudentDto lectureStudentDto) {

            lectureStudentDto.setId(lectureStudent.getId());
            lectureStudentDto.setAbsent(lectureStudent.getAbsent());
            lectureStudentDto.setNote(lectureStudent.getNote());

        }
    }
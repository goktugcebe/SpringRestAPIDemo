package university.example.springApiDemo.service.impl;

import university.example.springApiDemo.dto.LectureSubjectDto;
import university.example.springApiDemo.entity.LectureSubject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import university.example.springApiDemo.repository.LectureSubjectRepository;
import university.example.springApiDemo.service.LectureSubjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



    @Service
    public class LectureSubjectServiceImpl implements LectureSubjectService {

        private final LectureSubjectRepository lectureSubjectRepository;
        @Autowired
        public LectureSubjectServiceImpl(LectureSubjectRepository lectureSubjectRepository) {
            this.lectureSubjectRepository = lectureSubjectRepository;
        }

        @Override
        public List<LectureSubjectDto> getAll() {
            List<LectureSubjectDto> lectureSubjectDtoList = new ArrayList<>();
            lectureSubjectRepository.findAll().forEach(lectureSubject -> {
                LectureSubjectDto lectureSubjectDto = new LectureSubjectDto();
            BeanUtils.copyProperties(lectureSubject, lectureSubjectDto);
                entityToDto(lectureSubject, lectureSubjectDto);
                lectureSubjectDtoList.add(lectureSubjectDto);
            });
            return lectureSubjectDtoList;
        }

        @Override
        public Optional<LectureSubjectDto> getById(long id) {
            Optional<LectureSubjectDto> lectureSubjectDto = Optional.of(new LectureSubjectDto());
            LectureSubject lectureSubject = lectureSubjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecture Subject couldn't find!"));
//        BeanUtils.copyProperties(lectureSubject, lectureSubjectDto);
            entityToDto(lectureSubject, lectureSubjectDto.get());
            return lectureSubjectDto;
        }

        public LectureSubjectDto add(LectureSubjectDto lectureSubjectDto){
            LectureSubject lectureSubject=new LectureSubject();
            dtoToEntity(lectureSubjectDto,lectureSubject);
            lectureSubjectRepository.save(lectureSubject);
            entityToDto(lectureSubject,lectureSubjectDto);
            return lectureSubjectDto;
        }

        public void delete(LectureSubjectDto lectureSubjectDto){
            LectureSubject lectureSubject=new LectureSubject();
            dtoToEntity(lectureSubjectDto,lectureSubject);
            lectureSubjectRepository.save(lectureSubject);
        }

        public LectureSubjectDto update(LectureSubjectDto lectureSubjectDto){
            Optional<LectureSubjectDto> optionalLectureSubjectDto=Optional.of(getById(lectureSubjectDto.getId()).orElseThrow());
            optionalLectureSubjectDto.get().setSubjectName(lectureSubjectDto.getSubjectName());
            LectureSubject lectureSubject=new LectureSubject();
            dtoToEntity(lectureSubjectDto,lectureSubject);
            lectureSubject=lectureSubjectRepository.save(lectureSubject);
            entityToDto(lectureSubject,lectureSubjectDto);
            return lectureSubjectDto;
        }

        protected void dtoToEntity(LectureSubjectDto lectureSubjectDto, LectureSubject lectureSubject) {

            lectureSubject.setId(lectureSubjectDto.getId());
            lectureSubject.setSubjectName(lectureSubjectDto.getSubjectName());
        }

        protected void entityToDto(LectureSubject lectureSubject, LectureSubjectDto lectureSubjectDto) {

            lectureSubjectDto.setId(lectureSubject.getId());
            lectureSubjectDto.setSubjectName(lectureSubject.getSubjectName());
        }


}
package university.example.springApiDemo.service.impl;

import university.example.springApiDemo.dto.LectureStudentDto;
import university.example.springApiDemo.dto.StudentDto;
import university.example.springApiDemo.entity.LectureStudent;
import university.example.springApiDemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import university.example.springApiDemo.repository.StudentRepository;
import university.example.springApiDemo.service.StudentService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final LectureStudentServiceImpl lectureStudentService;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, LectureStudentServiceImpl lectureStudentService) {
        this.studentRepository = studentRepository;
        this.lectureStudentService = lectureStudentService;
    }

    @Override
    public List<StudentDto> getAll() {
        List<StudentDto> studentDtosList = new ArrayList<>();
        List<StudentDto> finalStudentDtosList = studentDtosList;
        studentRepository.findAll().forEach(student -> {
            StudentDto studentDto = new StudentDto();
            entityToDto(student, studentDto);
            finalStudentDtosList.add(studentDto);
        });

        /* Get the first registered student among the list withing number 1 and
         * if the registration can't be found throw and run time exception
         * */
        studentDtosList.stream().filter(studentDto -> studentDto.getStudentNumber() == 1).
                findFirst().orElseThrow((() -> new RuntimeException("Student couldn't find!")));

        /**
         * The filter has been set to the student list for name which is Alex,
         * after the first filter the second filter upon has been set according to the absent
         * , then the return values listed.
         */
        studentDtosList = studentDtosList.stream()
                .filter(studentDto -> studentDto.getStudentName().equalsIgnoreCase("Alex"))
                .filter(studentDto -> studentDto.getLectureStudentDtos().stream()
                        .filter(lectureStudentDto -> lectureStudentDto.getAbsent() > 0)
                        .allMatch(lectureStudentDto -> lectureStudentDto.getAbsent() > 0))
                .collect(Collectors.toList());

        return studentDtosList;
    }

    @Override
    public Optional<StudentDto> getById(long id) {
        StudentDto studentDto = new StudentDto();
        entityToDto(studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student coudln't be find!")), studentDto);
        return Optional.of(studentDto);
    }

    @Override
    public StudentDto add(StudentDto studentDto) {
        Student student = new Student();
        dtoToEntity(studentDto, student);
        student = studentRepository.save(student);
        entityToDto(student, studentDto);
        return studentDto;
    }

    @Override
    public void delete(StudentDto studentDto) {
        Student student = new Student();
        dtoToEntity(studentDto, student);
        studentRepository.delete(student);
    }

    @Override
    public StudentDto update(StudentDto studentDto) {
        Optional<StudentDto> studentDto1 = Optional.of(getById(studentDto.getId()).orElseThrow());
        studentDto.setId(studentDto1.get().getId());
        Student student = new Student();
        entityToDto(studentRepository.save(student), studentDto);
        return studentDto;
    }

    private void dtoToEntity(StudentDto studentDto, Student student) {
        student.setId(studentDto.getId());
        student.setStudentName(studentDto.getStudentName());
        student.setStudentAge(studentDto.getStudentAge());
        student.setStudentNumber(studentDto.getStudentNumber());

        if (!CollectionUtils.isEmpty(studentDto.getLectureStudentDtos())) {
            studentDto.getLectureStudentDtos().forEach(lectureStudentDto -> {
                LectureStudent lectureStudent = new LectureStudent();
                lectureStudentService.dtoToEntity(lectureStudentDto, lectureStudent);

                if (CollectionUtils.isEmpty(student.getLectureStudent()))
                    student.setLectureStudent(new HashSet<>());
                lectureStudent.setStudent(student);
                student.getLectureStudent().add(lectureStudent);
            });
        }
    }

    private void entityToDto(Student student, StudentDto studentDto) {
        studentDto.setId(studentDto.getId());
        studentDto.setStudentName(student.getStudentName());
        studentDto.setStudentAge(student.getStudentAge());
        studentDto.setStudentNumber(student.getStudentNumber());

        if (!CollectionUtils.isEmpty(student.getLectureStudent()))
            student.getLectureStudent().forEach(lectureStudent -> {
                LectureStudentDto lectureStudentDto = new LectureStudentDto();
                lectureStudentService.entityToDto(lectureStudent, lectureStudentDto);

                if (CollectionUtils.isEmpty(studentDto.getLectureStudentDtos()))
                    studentDto.setLectureStudentDtos(new HashSet<>());
                studentDto.getLectureStudentDtos().add(lectureStudentDto);
            });
    }
}

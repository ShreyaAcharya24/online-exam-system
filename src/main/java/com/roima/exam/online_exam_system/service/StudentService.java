package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.dto.StudentRequestDTO;
import com.roima.exam.online_exam_system.dto.StudentResponseDTO;
import com.roima.exam.online_exam_system.enums.Role;
import com.roima.exam.online_exam_system.model.Institute;
import com.roima.exam.online_exam_system.model.Student;
import com.roima.exam.online_exam_system.model.User;
import com.roima.exam.online_exam_system.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final InstituteService instituteService;
    private UserService userService;
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, UserService userService, InstituteService instituteService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.instituteService = instituteService;
    }

    // ****  Add Student *****
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {

//      Save User First
        User newUser = new User();
        newUser.setRole(Role.STUDENT);
        newUser.setEmail(studentRequestDTO.email());
        newUser.setPassword(studentRequestDTO.password());
        userService.addUser(newUser);

//       Get selected institute
        Institute institute = instituteService.findById(studentRequestDTO.instituteid());


        Student student = new Student();
        student.setFirstname(studentRequestDTO.firstname());
        student.setLastname(studentRequestDTO.lastname());
        student.setContact(String.valueOf(studentRequestDTO.contact()));
//
//      Save User & Institute in Student
        student.setUser(newUser);
        student.setInstitute(institute);

        Student createdStudent = studentRepository.save(student);

//        Prepare Response
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
                createdStudent.getFirstname(),
                createdStudent.getLastname(),
                createdStudent.getUser().getEmail(),
                createdStudent.getContact(),
                createdStudent.getInstitute().getInstituteName()
        );

        return studentResponseDTO;
    }


    // **** Get All Students ****
    public List<StudentResponseDTO> getAllStudents() {
//        Get the list of all students
        List<Student> students = studentRepository.findAll();

//        Convert each student into DTO
        List<StudentResponseDTO> responseStudents = students.stream().map(student -> new StudentResponseDTO(
                student.getFirstname(),
                student.getLastname(),
                student.getUser().getEmail(),
                student.getContact(),
                student.getInstitute().getInstituteName()
        )).collect(Collectors.toList());

        return responseStudents;
    }

    //    **** Delete Student *****
    public boolean deleteStudent(int sid) {
        Optional<Student> student = studentRepository.findById(sid);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return true;
        }
        return false;
    }

    //    ****** Update Student Details *****
    @Transactional
    public StudentResponseDTO updateStudent(StudentRequestDTO studentRequestDTO, int sid) {

        Student student = studentRepository.findById(sid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not found"));

//      Convert DTO to Student for Updating fields
        student.setFirstname(studentRequestDTO.firstname());
        student.setLastname(studentRequestDTO.lastname());
        student.setContact(String.valueOf(studentRequestDTO.contact()));

        User user = student.getUser();
        user.setEmail(studentRequestDTO.email());
//       #### DTO doesnt contain Role --> SHow role in Update form --> get the id & pass it in request
//        user.setRole(user.getRole());
        userService.updateUser(user);


        Institute institute = instituteService.findById(studentRequestDTO.instituteid());
        student.setInstitute(institute);
        Student updatedStudent = studentRepository.save(student);
        StudentResponseDTO responseDTO = new StudentResponseDTO(updatedStudent.getFirstname(), updatedStudent.getLastname(), updatedStudent.getUser().getEmail(), updatedStudent.getContact(), updatedStudent.getInstitute().getInstituteName());

        return responseDTO;


    }


//   ************* Get Student by Id ***************

    public Student getByID(int id){

        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }


}

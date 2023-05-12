package com.student.Result;

import com.student.Result.Student.Course;
import com.student.Result.Student.CourseRepo;
import com.student.Result.Student.Result;
import com.student.Result.Student.ResultRepo;
import com.student.Result.Student.Student;
import com.student.Result.Student.StudentNameDto;
import com.student.Result.Student.StudentRepo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
public class StudentResultController {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    ResultRepo resultRepo;
    @Autowired
    CourseRepo courseRepo;

    @PostMapping(value = "/saveStudent")
    public void saveStudent(@RequestBody Student student) throws Exception {
        try{
            if(studentRepo.findByEmail(student.getEmail()).size()>=1){
                throw new DuplicateRecordException("Email");
            }
        studentRepo.save(student);}
        catch (Exception e){
            throw e;
        }
    }

    @PostMapping(value = "/saveResult")
    public void saveResult(@RequestBody Result result) throws Exception {
        resultRepo.save(result);
    }

    @PostMapping(value = "/saveCourse")
    public void saveCourse(@RequestBody Course course) throws Exception {
        try{
            if(courseRepo.findByCourse(course.getCourse()).size()>=1){
                throw new DuplicateRecordException("Course");
            }
            courseRepo.save(course);}
        catch (Exception e){
            throw e;
        }
    }

    @GetMapping(value = "/getStudent")
    public List<StudentNameDto> getStudent() throws Exception{
        return studentRepo.findAll().stream().map(student -> new StudentNameDto(student.getFirstName().concat(" ").concat(student.getFamilyName()), student.getEmail(), student.getDateOfBirth(), student.getId())).collect(Collectors.toList());
    }

    @GetMapping(value = "/getResult")
    public List<Result> getResult()  throws Exception{
        return resultRepo.findAll();
    }

    @GetMapping(value = "/getCourse")
    public List<Course> saveCourse() throws Exception {
        return courseRepo.findAll();
    }

    @DeleteMapping(value = "/deleteStudent")
    public void deleteStudent(@RequestParam Long id) throws Exception {
        studentRepo.deleteById(id);
    }

    @DeleteMapping(value = "/deleteResult")
    public void deleteResult(@RequestParam Long id) throws Exception {
        try {
            resultRepo.deleteById(id);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @DeleteMapping(value = "/deleteCourse")
    public void deleteCourse(@RequestParam Long id) throws Exception {
        courseRepo.deleteById(id);
    }

}

package in.kumar.controller;

import in.kumar.entity.Student;
import in.kumar.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<Student> createStudent(@RequestBody Student studentReq){
        Student studentResp=studentService.createStudent(studentReq);
        return ResponseEntity.ok(studentResp);
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Student> findById(@PathVariable("id") Long id){
        Student studentResp=studentService.getStudent(id);

        if (studentResp==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Student>> findAll(){
        List<Student> list=studentService.getAllStudent();
        return ResponseEntity.ok(list);
    }


}

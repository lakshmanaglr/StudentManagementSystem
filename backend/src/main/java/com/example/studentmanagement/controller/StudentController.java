package com.example.studentmanagement.controller;
import com.example.studentmanagement.model.Student; import com.example.studentmanagement.service.StudentService; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/students") @CrossOrigin(origins="http://localhost:5173") public class StudentController {private final StudentService service; public StudentController(StudentService service){this.service=service;}
 @GetMapping public List<Student> all(@RequestParam(required=false) String search){return search==null||search.isBlank()?service.all():service.search(search);}
 @GetMapping("/{id}") public ResponseEntity<Student> one(@PathVariable int id){Student s=service.one(id);return s==null?ResponseEntity.notFound().build():ResponseEntity.ok(s);}
 @PostMapping public ResponseEntity<Student> add(@RequestBody Student s){return ResponseEntity.status(HttpStatus.CREATED).body(service.add(s));}
 @PutMapping("/{id}") public ResponseEntity<Void> update(@PathVariable int id,@RequestBody Student s){service.update(id,s);return ResponseEntity.noContent().build();}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable int id){service.delete(id);return ResponseEntity.noContent().build();}}

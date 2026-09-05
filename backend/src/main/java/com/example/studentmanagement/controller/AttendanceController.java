package com.example.studentmanagement.controller;
import com.example.studentmanagement.model.Attendance; import com.example.studentmanagement.service.AttendanceService; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/attendance") @CrossOrigin(origins="http://localhost:5173") public class AttendanceController {private final AttendanceService service;public AttendanceController(AttendanceService service){this.service=service;}
 @GetMapping("/student/{studentId}") public List<Attendance> byStudent(@PathVariable int studentId){return service.byStudent(studentId);}
 @PostMapping public ResponseEntity<Attendance> add(@RequestBody Attendance a){return ResponseEntity.status(HttpStatus.CREATED).body(service.add(a));}
 @PutMapping("/{id}") public ResponseEntity<Void> update(@PathVariable int id,@RequestBody Attendance a){service.update(id,a);return ResponseEntity.noContent().build();}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable int id){service.delete(id);return ResponseEntity.noContent().build();}}

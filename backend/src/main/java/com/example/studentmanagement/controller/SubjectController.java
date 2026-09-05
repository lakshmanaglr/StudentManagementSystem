package com.example.studentmanagement.controller;
import com.example.studentmanagement.dao.SubjectDAO; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/subjects") @CrossOrigin(origins="http://localhost:5173") public class SubjectController {private final SubjectDAO dao;public SubjectController(SubjectDAO dao){this.dao=dao;} @GetMapping public List<Map<String,Object>> all(){return dao.findAll();}}

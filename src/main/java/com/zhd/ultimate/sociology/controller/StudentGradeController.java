package com.zhd.ultimate.sociology.controller;

import com.zhd.ultimate.sociology.entity.StudentGrade;
import com.zhd.ultimate.sociology.service.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/studentGrade")
public class StudentGradeController {

    @Autowired
    private StudentGradeService studentGradeService;


    @InitBinder("studentGrade")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("studentGrade.");
    }

    @RequestMapping("/queryAllStudentGrade")
    public String queryAllStudentGrade(Model model) {
        List<StudentGrade> studentGradeList = studentGradeService.queryAllStudentGrade();
        model.addAttribute("studentGradeList", studentGradeList);
        return "studentGrade/studentGrade-query";
    }

    @RequestMapping("/showAdd")
    public String showAdd() {
        return "studentGrade/studentGrade-add";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("guid") String guid, Model model) {
        StudentGrade studentGrade = studentGradeService.queryStudentGrade(guid);
        model.addAttribute("studentGrade", studentGrade);
        return "studentGrade/studentGrade-update";
    }

    @RequestMapping("/add")
    public String add(StudentGrade studentGrade, Model model) {
        studentGradeService.add(studentGrade);
        return queryAllStudentGrade(model);
    }

    @RequestMapping("/update")
    public String update(StudentGrade studentGrade, Model model) {
        studentGradeService.update(studentGrade);
        return queryAllStudentGrade(model);
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("guid") String guid, Model model) {
        studentGradeService.delete(guid);
        return queryAllStudentGrade(model);
    }

}

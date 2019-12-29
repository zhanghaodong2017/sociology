package com.zhd.ultimate.sociology.service;

import com.zhd.ultimate.sociology.entity.StudentGrade;

import java.util.List;


public interface StudentGradeService {

    List<StudentGrade> queryAllStudentGrade();

    int add(StudentGrade studentGrade);

    int update(StudentGrade studentGrade);

    StudentGrade queryStudentGrade(String guid);

    int delete(String guid);
}

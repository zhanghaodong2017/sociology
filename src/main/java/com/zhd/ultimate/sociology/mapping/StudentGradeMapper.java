package com.zhd.ultimate.sociology.mapping;

import com.zhd.ultimate.sociology.entity.StudentGrade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentGradeMapper {
    int deleteByPrimaryKey(String guid);

    int insert(StudentGrade record);

    int insertSelective(StudentGrade record);

    StudentGrade selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(StudentGrade record);

    int updateByPrimaryKey(StudentGrade record);

    List<StudentGrade> queryAllStudentGrade();
}
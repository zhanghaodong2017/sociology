package com.zhd.ultimate.sociology.service.impl;

import com.zhd.ultimate.sociology.entity.StudentGrade;
import com.zhd.ultimate.sociology.mapping.StudentGradeMapper;
import com.zhd.ultimate.sociology.service.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentGradeServiceImpl implements StudentGradeService {

    @Autowired
    private StudentGradeMapper studentGradeMapper;

    @Override
    public List<StudentGrade> queryAllStudentGrade() {
        return studentGradeMapper.queryAllStudentGrade();
    }

    @Override
    public int add(StudentGrade studentGrade) {
        //TODO
        return studentGradeMapper.insert(studentGrade);
    }

    @Override
    public int update(StudentGrade studentGrade) {
        //TODO
        return studentGradeMapper.updateByPrimaryKeySelective(studentGrade);
    }

    @Override
    public StudentGrade queryStudentGrade(String guid) {
        return studentGradeMapper.selectByPrimaryKey(guid);
    }

    @Override
    public int delete(String guid) {
        return studentGradeMapper.deleteByPrimaryKey(guid);
    }
}

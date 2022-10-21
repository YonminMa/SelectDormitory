package com.pku.dormitory.controller;

import com.pku.dormitory.domain.Student;
import com.pku.dormitory.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


// @RestController表示@Controller + @ResponseBody
// @Controller默认返回html或者其他网页
// @ResponseBody表示返回json字符串
@RestController
@RequestMapping("/user")
public class StudentController {

    @Autowired
    StudentService studentService;

    // @GetMapping是@RequestMapping(method = RequestMethod.GET)的缩写
    @GetMapping("/{uid}")
    public String getStudentInfo(@PathVariable("uid") String uid) {
        Student student = studentService.getStudent(uid);
        System.out.println(student);
        if (student == null) {
            return "未查询到该学生";
        } else {
            return "学号："+student.getUid() + "\n姓名：" + student.getName() +"\n性别：" + student.getGender();
        }
    }

    @PostMapping("/save")
    public String saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return "添加用户" + student.getName() + "成功";
    }

    @PutMapping("/update")
    public String changePassword(String uid, String password) {
        studentService.changePassword(uid, password);
        return "密码更改成功";
    }

    @DeleteMapping("/{uid}")
    public String deleteStudent(@PathVariable("uid") String uid) {
        studentService.deleteStudent(uid);
        return "删除学生" + uid + "成功";
    }
}

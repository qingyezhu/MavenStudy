package com.qingyezhu.springmvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingyezhu.common.model.Student;
import com.qingyezhu.common.util.JsonUtils;
import com.qingyezhu.common.vo.PaginationVo;

@RequestMapping(value = "/hello")
@Controller
public class HelloController {

	@RequestMapping(value = "/index")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response){
		return "index";
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public String list(HttpServletRequest request, HttpServletResponse response){
		
		List<Student> list = new ArrayList<Student>();
		Student stu = null;
		for(int i = 0;i < 10;i ++){
			stu = new Student();
			stu.setId(i);
			stu.setName("stu-" + i);
			stu.setAge(new Random().nextInt(20));
			stu.setClazz("class-" + i/4);
			stu.setScore(new Random().nextInt(100)*1.0);
			list.add(stu);
		}
		PaginationVo<Student> pv = new PaginationVo<Student>(10L, list);
		String ret = JsonUtils.objectToString(pv);
		return ret;
	}
	
}

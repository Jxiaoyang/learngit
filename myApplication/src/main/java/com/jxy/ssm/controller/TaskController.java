package com.jxy.ssm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxy.ssm.po.St;
import com.jxy.ssm.po.Student;
import com.jxy.ssm.po.Task;
import com.jxy.ssm.service.TaskService;
import com.jxy.ssm.vo.TaskPeople;
import com.jxy.ssm.vo.WhoReceive;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/addTask")
	@ResponseBody
	public String addTask(@RequestBody Map<String,String> map,HttpServletRequest request) {
		Task task = new Task();
		
		Student student = (Student)request.getSession().getAttribute("student");
		System.out.println(student.toString());
		task.setTask_taskTime(new Date());
		task.setTask_putID(student.getStu_id());
		task.setTask_type(Integer.parseInt(map.get("taskType")));
		task.setTask_name(map.get("taskName"));
		task.setTask_content(map.get("taskContent"));
		task.setTask_people(Integer.parseInt(map.get("taskPeople")));
		task.setTask_money(Integer.parseInt(map.get("taskMoney")));
		task.setTask_pricontent(map.get("taskPricontent"));
		
		if(taskService.addTask(task)) {
			return "success";
			
		}else {
			return "fail";
		}
	}
	//根据类型获得任务
	@RequestMapping("/getTask")
	public @ResponseBody Map getTask() {
		
		Map<String,List<TaskPeople>> map = taskService.getTaskByType();
		return map;
	}
	
	

	//获得所有自己发布的任务
	@RequestMapping("/getMyPut")
	public @ResponseBody Map getMyPut(HttpServletRequest request) {
		Student student =(Student)request.getSession().getAttribute("student");
		
		Map<String,List<TaskPeople>> map = taskService.getTaskByStatus(student.getStu_id());
		return map;
	}
	@RequestMapping("/getDescription")
	public @ResponseBody TaskPeople getDescription(@RequestParam("demId") String task_id) {
		TaskPeople taskPeople = taskService.getTaskById(task_id);
		return taskPeople;
	}
	@RequestMapping("/receive")
	public @ResponseBody String receive(HttpServletRequest request) {
		
		Student student = (Student)request.getSession().getAttribute("student");
		String demPutId = request.getParameter("demPutId");
		String demId = request.getParameter("demId");
		if(student.getStu_id().equals(demPutId)) {
			return "-1";
		}else {
			St st = new St(demId,student.getStu_id(),new Date());
			
			int type = taskService.receiveTask(st);
			String str = type+"";
			return str;
		}
	
		
	}
	@RequestMapping("/getPeople")
	@ResponseBody
	public  List<WhoReceive> getPeople(@RequestParam("demId") String task_id){
		
		
		return  taskService.getPeople(task_id);
		
	}
	@RequestMapping("/getMyTask")
	@ResponseBody
	public List<TaskPeople> getMyReceive(HttpServletRequest request){
		Student stu = (Student)request.getSession().getAttribute("student");
		return taskService.getMyReceive(stu.getStu_id());
	}
	@RequestMapping("/retractTask")
	@ResponseBody
	public int  retractTask(@RequestParam("demId") String task_id) {
		return taskService.retractTask(task_id)? 1:0;
		
		
	}
	@RequestMapping("/finishTask")
	@ResponseBody
	public int  finishTask(@RequestParam("demId") String task_id) {
		return taskService.finishTask(task_id) ?1:0;
		
		
	}
	@RequestMapping("/deleteTask")
	@ResponseBody
	public int  deleteTask(@RequestParam("demId") String task_id) {
		return taskService.deleteTask(task_id) ?1:0;
		
		
	}
	@RequestMapping("/abandonTask")
	@ResponseBody
	public int  abandonTask(@RequestParam("demId") String task_id,HttpServletRequest request) {
		Student student =(Student) request.getSession().getAttribute("student");
		System.out.println(student.toString());
		//放弃任务
		return taskService.abandonTask(task_id,student.getStu_id()) ?1:0;
		
		
	}
	@RequestMapping("/amputateTask")
	@ResponseBody
	public int  amputateTask(@RequestParam("demId") String task_id) {
		return taskService.amputateTask(task_id) ?1:0;
		
		
	}
	

}

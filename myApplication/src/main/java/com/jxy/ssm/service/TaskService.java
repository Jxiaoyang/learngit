package com.jxy.ssm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxy.ssm.mapper.TaskMapper;
import com.jxy.ssm.po.St;
import com.jxy.ssm.po.Task;
import com.jxy.ssm.vo.TaskPeople;
import com.jxy.ssm.vo.WhoReceive;

@Service
public class TaskService {
	
	@Autowired
	TaskMapper taskMapper ;
	@Transactional
	public boolean addTask(Task task){
		
		if(taskMapper.addTask(task)>0) {
			return true;
		}else {
			return false;
		}
		
		
	}
	@Transactional
	public List<Task> getTask() {
		List<Task> list = taskMapper.getTask();
		return list;
		
		
	}
	//完成分为三组的逻辑
	//这个 带有 每个任务 已接受的人数
	@Transactional
	public Map<String,List<TaskPeople>> getTaskByType(){
		Map<String,List<TaskPeople>> map = new HashMap<String,List<TaskPeople>>();
		List<TaskPeople> list0 = taskMapper.getTaskByType(0);
		List<TaskPeople> list1 = taskMapper.getTaskByType(1);
		List<TaskPeople> list2 = taskMapper.getTaskByType(2);
		map.put("0", list0);
		map.put("1", list1);
		map.put("2", list2);
		
		return map;
		
	}
	//我发布的任务
	@Transactional
	public Map<String,List<TaskPeople>> getTaskByStatus(String student_id){
		Map<String,List<TaskPeople>> map = new HashMap<String,List<TaskPeople>>();
		List<TaskPeople> list0 = taskMapper.getTaskByStatus(student_id,0,0);
		List<TaskPeople> list1 = taskMapper.getTaskByStatus(student_id,1,0);
		List<TaskPeople> list2 = taskMapper.getTaskByStatus(student_id,1,1);
		map.put("0", list0);
		map.put("1", list1);
		map.put("2", list2);
		
		return map;
	}
	//查看详情
	@Transactional
	public TaskPeople getTaskById(String task_id) {
		TaskPeople taskPeople =taskMapper.getTaskById(task_id);
		return taskPeople;
	}
	//接受任务
	@Transactional
	public int receiveTask(St st) {
		if(taskMapper.getReceiveById(st)>0) {  //如果已经接受过则退出并提醒
			return 0;
		}
		TaskPeople taskPeople = taskMapper.getTaskById(st.getSt_id());//获取添加后的people 人数与task_people比较
		if(taskPeople.getTask_people()==taskPeople.getPeople()) {  //人数已经满
			return -3;  //该任务人已满
		}
		///判断任务是否撤销
		if(taskMapper.if_retract(st.getSt_id()) == 2) {
			return -2;  //该任务已被撤销
		}
		
		taskMapper.addReceive(st);
		//taskPeople = taskMapper.getTaskById(st.getSt_id());//获取添加后的people 人数与task_people比较
		if(taskPeople.getTask_people()==(taskPeople.getPeople()+1)) {
			taskMapper.setReceive(1, st.getSt_id());//表示人满
			return 1; //表示接受成功
			
		}else {
			return 1;  //表示接受成功
		}
	}
	/*查找指定任务的所有接受人*/
	@Transactional
	public List<WhoReceive> getPeople(String task_id){
		List<WhoReceive> whoReceive= taskMapper.getPeople(task_id);
		
		return whoReceive;
	}
	
	/*我接受的任务*/
	@Transactional
	public List<TaskPeople> getMyReceive(String st_receiveid){
		List<TaskPeople>  tp = taskMapper.getMyReceive(st_receiveid);
		return tp;
		
	}
	/*撤销任务*/
	@Transactional
	public boolean retractTask(String task_id) {
		//判断任务是否接受
		if(taskMapper.if_receive(task_id) >0) {
			return false; //已被接受不能撤销
		}
		return taskMapper.retractTask(task_id) >0? true:false;

	}
	
	/*确认完成任务*/
	@Transactional
	public boolean finishTask(String task_id) {
		//如果任务已经放弃，判断任务是否已经放弃
		if(taskMapper.if_abandon(task_id) ==0) {
			return false;// 该任务已被放弃
		}
		
		 return taskMapper.finishTask(task_id) >0? true:false;

	}
	
	/*删除记录*/
	@Transactional
	public boolean deleteTask(String task_id) {
		 return taskMapper.deleteTask(task_id) >0? true:false;

	}
	
	/*放弃任务*/
	@Transactional
	public boolean abandonTask(String task_id,String receiveid) {
		if(taskMapper.if_finish(task_id) ==1) {
			return false;//该任务已被确认完成，不能放弃请刷新页面
		}
		 taskMapper.abandonTask(task_id,receiveid);
		 taskMapper.setReceive(0,task_id);
		 return true;
		 

	}
	
	/*删除记录*/
	@Transactional
	public boolean amputateTask(String task_id) {
		 return taskMapper.amputateTask(task_id) >0? true:false;

	}
	

}

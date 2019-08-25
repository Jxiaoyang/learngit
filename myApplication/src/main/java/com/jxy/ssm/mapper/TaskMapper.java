package com.jxy.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jxy.ssm.po.St;
import com.jxy.ssm.po.Task;
import com.jxy.ssm.vo.TaskPeople;
import com.jxy.ssm.vo.WhoReceive;

@Repository
public interface TaskMapper {
	public int addTask(Task task);
	public List<Task> getTask();
	/*根据任务类型获取资源*/
	public List<TaskPeople> getTaskByType(int type);
	/*根据任务状态获取资源*/
	public List<TaskPeople>
	getTaskByStatus(@Param("student_id")String student_id,@Param("status") int status,@Param("task_accomplish") int task_accomplish);
	
	/*查看详情*/
	public TaskPeople getTaskById(@Param("task_id") String task_id);
	
	/*判断是否接受过*/
	public int getReceiveById(St st);
	
	/*设置任务是否接受完*/
	
	public int setReceive(@Param("task_receive") int receive,@Param("task_id") String task_id);

	/*添加接受任务者*/
	public int addReceive(St st);
	
	/*查找指定任务的所有接受人*/
	public List<WhoReceive> getPeople (@Param("task_id") String task_id);
	
	/*getMyReceive  获得我接受的任务*/
	public  List<TaskPeople> getMyReceive(@Param("st_receiveid") String st_receiveid);
	
	/*retractTask 撤销任务*/
	public  int  retractTask(@Param("task_id") String task_id);
	
	/*确认完成任务*/
	public int finishTask(@Param("task_id") String task_id);
	
	/*删除记录*/
	
	public int deleteTask(@Param("task_id") String task_id);
	
	/*放弃任务*/
	
	public int abandonTask(@Param("task_id") String task_id,@Param("st_receiveid") String st_received);
	
	/*删除我的任务已完成的记录*/
	
	public int amputateTask(@Param("task_id") String task_id);
	
	/*判断是否撤销*/
	
	public  int  if_retract(@Param("task_id") String task_id);
	/*判断是否放弃*/
	
	public int if_abandon(@Param("task_id") String task_id);
	
	/*判断是否完成*/
	
	public int if_finish(@Param("task_id") String task_id);
	/*判断是否接受*/
	public int if_receive(@Param("task_id") String task_id);
	
	
	
}

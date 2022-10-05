package com.dodo.dodobirdWorld.board.vo;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeVO {
	private String employee_id;
	private String first_name;
	private String last_name;
	private Date hire_date;
}

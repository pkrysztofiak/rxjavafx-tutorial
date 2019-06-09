package com.github.pkrysztofiak.rxjavafxtutorial.tasks.task011;

import com.github.pkrysztofiak.rxjavafxtutorial.solutions.solution011.Employee;

public class ExternalSystem {

	public void updateDescription(String description) {
		System.out.println("[ExternalSystem] Description updated=" + description);
	}

	public void updateDescription(Employee employee) {
		System.out.println("[ExternalSystem] Description updated for employee=" + employee);
	}
}
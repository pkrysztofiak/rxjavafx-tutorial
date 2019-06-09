package com.github.pkrysztofiak.rxjavafxtutorial.solutions.solution011;

public class ExternalSystem {

	public void updateDescription(String description) {
		System.out.println("[ExternalSystem] Description updated=" + description);
	}

	public void updateDescription(Employee employee) {
		System.out.println("[ExternalSystem] Description updated for employee=" + employee);
	}
}
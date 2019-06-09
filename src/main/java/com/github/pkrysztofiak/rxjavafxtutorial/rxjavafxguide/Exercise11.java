package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide;

import java.net.URL;
import java.util.Scanner;

public class Exercise11 {

	public static void main(String[] args) {
		System.out.println(request("https://api.github.com/users/pkrysztofiak"));
	}

	public static String request(String path) {
		try (Scanner scanner = new Scanner(new URL(path).openStream(), "UTF-8");) {
			return scanner.useDelimiter("\\A").next();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}

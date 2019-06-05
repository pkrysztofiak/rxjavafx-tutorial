package com.github.pkrysztofiak.rxjavafxtutorial.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Route {
	private final List<Client> clients = new ArrayList<>();

	public Route(Client... clients) {
		this.clients.addAll(Arrays.asList(clients));
	}
	public List<Client> getClients() {
		return clients;
	}
}
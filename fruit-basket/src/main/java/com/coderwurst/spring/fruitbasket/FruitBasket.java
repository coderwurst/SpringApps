package com.coderwurst.spring.fruitbasket;

import java.util.List;

public class FruitBasket {

	private String name;
	private List <String> fruits;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(name);
		sb.append(" contains: \n");
		
		for (String fruit : fruits) {
			sb.append(fruit);
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public FruitBasket(List<String> fruits) {
		this.fruits = fruits;
	}

	public FruitBasket(String name, List<String> fruits) {
		super();
		this.name = name;
		this.fruits = fruits;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

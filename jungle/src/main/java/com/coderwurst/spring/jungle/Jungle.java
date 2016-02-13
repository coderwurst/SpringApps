package com.coderwurst.spring.jungle;

import java.util.List;

public class Jungle {

	private Animal largest;
	private List<Animal> animals;

	public void setLargest(Animal animal) {
		this.largest = animal;
	}
	
	public Animal getLargest() {
		return largest;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Largest: " + largest);
		sb.append("\n");
		sb.append("Others: \n");

		for (Animal animal : animals) {
			sb.append(animal);
			sb.append("\n");
		}
		return sb.toString();
	}
}

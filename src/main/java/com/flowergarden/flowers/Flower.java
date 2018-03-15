package com.flowergarden.flowers;

import com.flowergarden.properties.Freshness;
import com.flowergarden.properties.FreshnessInteger;

public interface Flower<T> {
	Freshness<T> getFreshness();
	float getPrice();
	int getLenght();
	public void setFreshness(FreshnessInteger fr);
	void setPrice(float price);
	void setLenght(int lenght);
}

package com.app.controller.study.practice.practice14;

import lombok.Data;

@Data
public class CupBean {
	CoffeeBean coffeeBean;
	
	public CupBean(CoffeeBean coffeeBean) {
		this.coffeeBean = coffeeBean;
	}
}

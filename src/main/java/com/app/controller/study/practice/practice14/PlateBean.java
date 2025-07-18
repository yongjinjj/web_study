package com.app.controller.study.practice.practice14;

import lombok.Data;

@Data
public class PlateBean {
	DessertBean dessertBean;
	
	public void setDessertBean(DessertBean dessertBean) {
		this.dessertBean = dessertBean;
	}
}

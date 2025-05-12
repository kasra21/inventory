package com.task.inventory.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "hardware")
public class Hardware extends Item {

    private String manufacturer;

    private String serialNumber;

    private LocalDate purchaseDate;

    public Hardware() {}

    public Hardware(String manufacturer, String serialNumber, LocalDate purchaseDate) {
        this.setManufacturer(manufacturer);
        this.setSerialNumber(serialNumber);
        this.setPurchaseDate(purchaseDate);
    }

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}


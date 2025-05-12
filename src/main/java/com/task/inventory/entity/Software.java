package com.task.inventory.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "software")
public class Software extends Item {

    private String version;

    private String licenseKey;

    private LocalDate purchaseDate;

    public Software() {}

    public Software(String version, String licenseKey, LocalDate purchaseDate) {
        this.setVersion(version);
        this.setLicenseKey(licenseKey);
        this.setPurchaseDate(purchaseDate);
    }

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}


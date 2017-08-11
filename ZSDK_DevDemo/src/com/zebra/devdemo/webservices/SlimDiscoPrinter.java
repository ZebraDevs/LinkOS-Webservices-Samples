package com.zebra.devdemo.webservices;

import com.zebra.sdk.printer.discovery.DiscoveredPrinter;

public class SlimDiscoPrinter {
	public String address;
	public String dnsName;
	public String firmwareVer;
	public String productName;
	public String serialNumber;

	public SlimDiscoPrinter(DiscoveredPrinter printer) {
		this.setAddress(printer.getDiscoveryDataMap().get("ADDRESS"));
		this.setProductName(printer.getDiscoveryDataMap().get("PRODUCT_NAME"));
		this.setDnsName(printer.getDiscoveryDataMap().get("DNS_NAME"));
		this.setFirmwareVer(printer.getDiscoveryDataMap().get("FIRMWARE_VER"));
		this.setSerialNumber(printer.getDiscoveryDataMap().get("SERIAL_NUMBER"));
	}

	public String getDnsName() {
		return this.dnsName;
	}

	public void setDnsName(String dnsName) {
		this.dnsName = dnsName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getFirmwareVer() {
		return this.firmwareVer;
	}

	public void setFirmwareVer(String firmwareVer) {
		this.firmwareVer = firmwareVer;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
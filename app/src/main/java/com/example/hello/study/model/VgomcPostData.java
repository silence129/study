package com.example.hello.study.model;

import java.util.LinkedList;
import java.util.List;

public class VgomcPostData {

	String companyName;
	String deviceId;
	String deviceName;
	String devicePointx;
	String devicePointy;
	List<VgomcPostDataValue> dataValues = new LinkedList<>();

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDevicePointx() {
		return devicePointx;
	}

	public void setDevicePointx(String devicePointx) {
		this.devicePointx = devicePointx;
	}

	public String getDevicePointy() {
		return devicePointy;
	}

	public void setDevicePointy(String devicePointy) {
		this.devicePointy = devicePointy;
	}

	public List<VgomcPostDataValue> getDataValues() {
		return dataValues;
	}

	public void setDataValues(List<VgomcPostDataValue> dataValues) {
		this.dataValues = dataValues;
	}

	@Override
	public String toString() {
		return "DeviceDataValue [companyName=" + companyName + ", deviceId=" + deviceId + ", deviceName=" + deviceName
				+ ", dataValues=" + dataValues + "]";
	}

	// --------------------------------------------------------------------------------------------------------------------------------//

}
package com.example.hello.study.model;

public class VgomcPostDataValue {

	String varId;
	String varName;
	String channelId;
	String varUnit;
	String value;
	String formatValue;
	String varDate;

	public String getVarId() {
		return varId;
	}

	public void setVarId(String varId) {
		this.varId = varId;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getVarUnit() {
		return varUnit;
	}

	public void setVarUnit(String varUnit) {
		this.varUnit = varUnit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFormatValue() {
		return formatValue;
	}

	public void setFormatValue(String formatValue) {
		this.formatValue = formatValue;
	}

	public String getVarDate() {
		return varDate;
	}

	public void setVarDate(String varDate) {
		this.varDate = varDate;
	}

	@Override
	public String toString() {
		return "DataValue [varId=" + varId + ", varName=" + varName + ", channelId=" + channelId + ", varUnit="
				+ varUnit + ", value=" + value + ", formatValue=" + formatValue + ", varDate=" + varDate + "]";
	}

}
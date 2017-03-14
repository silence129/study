package com.example.hello.study.model;

public class Factor {

	// Fields

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String alias;
	private String englishName;
	private String unit;
	private String icon;
	private Float maxValue = 100.0f;
	private Float minValue = 0.0f;
	private Float pricision = 0.0f;
	private String standard;
	private String dataType;
	private String category;
	private String note;

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnglishName() {
		return this.englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Float getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(Float maxValue) {
		this.maxValue = maxValue;
	}

	public Float getMinValue() {
		return this.minValue;
	}

	public void setMinValue(Float minValue) {
		this.minValue = minValue;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Float getPricision() {
		return this.pricision;
	}

	public void setPricision(Float pricision) {
		this.pricision = pricision;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
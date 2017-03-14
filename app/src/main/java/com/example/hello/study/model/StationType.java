package com.example.hello.study.model;

public class StationType {

	// Fields

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String category;
	private String icon;
	private String images;
	private String company;
	private String version;
	private Integer count = 0;
	private Boolean isDevice = false;
	private Boolean isMasterDevice = false;
	private String dataTable;
	private String tags;
	private String description;
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

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Boolean getIsDevice() {
		return isDevice;
	}

	public void setIsDevice(Boolean isDevice) {
		this.isDevice = isDevice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDataTable() {
		return dataTable;
	}

	public void setDataTable(String dataTable) {
		this.dataTable = dataTable;
	}

	public Boolean getIsMasterDevice() {
		return isMasterDevice;
	}

	public void setIsMasterDevice(Boolean isMasterDevice) {
		this.isMasterDevice = isMasterDevice;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
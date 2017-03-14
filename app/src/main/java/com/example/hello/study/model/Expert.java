package com.example.hello.study.model;

import java.util.Date;

public class Expert {

    // Fields

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String unitName;
    private Long unitId;
    private String description;
    private String tags;
    private String images;
    private String attachments;
    private Date createDate = new Date();
    private String remark;
    private Integer sortOrder = 1;
    private Integer gender = 0; // 0男 1女
    private String major;
    private String majorCategory;
    private String job; // 职称
    private String belief; // 信仰
    private String birthday; // 生日
    private String experience; // 个人经历

    private String address; // 地址
    private String province;
    private String email; // 邮箱
    private String phoneNumber; // 电话

    private String degree; // 学历
    private String school; // 毕业学校
    private Integer gradeYear;// 毕业年份

    private String workCompany; // 工作单位
    private String workPosition; // 工作职位

    private String researchDirection;// 研究方向
    private String researchAchievement;// 研究成果
    private String researchProject;// 科研项目
    private String researchThesis;// 论文成果
    private String researchField;// 领域名称
    private Long accountId;//用户id

    private Integer level; // 0普通专家，1认证专家，2知名专家
    private Integer score; // 评分

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getImages() {
        if (images != null && !"".equals(images)) {
            if (images.endsWith(",")) {
                return images.substring(0, images.length() - 1);
            }
        }
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    // Property accessors

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajorCategory() {
        return majorCategory;
    }

    public void setMajorCategory(String majorCategory) {
        this.majorCategory = majorCategory;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBelief() {
        return belief;
    }

    public void setBelief(String belief) {
        this.belief = belief;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getGradeYear() {
        return gradeYear;
    }

    public void setGradeYear(Integer gradeYear) {
        this.gradeYear = gradeYear;
    }

    public String getWorkCompany() {
        return workCompany;
    }

    public void setWorkCompany(String workCompany) {
        this.workCompany = workCompany;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    public String getResearchAchievement() {
        return researchAchievement;
    }

    public void setResearchAchievement(String researchAchievement) {
        this.researchAchievement = researchAchievement;
    }

    public String getResearchProject() {
        return researchProject;
    }

    public void setResearchProject(String researchProject) {
        this.researchProject = researchProject;
    }

    public String getResearchThesis() {
        return researchThesis;
    }

    public void setResearchThesis(String researchThesis) {
        this.researchThesis = researchThesis;
    }

    public String getResearchField() {
        return researchField;
    }

    public void setResearchField(String researchField) {
        this.researchField = researchField;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
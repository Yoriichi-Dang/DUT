package com.laptrinhjavaWeb.model;

import java.util.Date;

public class StudentModel extends AbstractModel<StudentModel> {
    private String name;
    private String cccd;
    private String sex;
    private Date birthday;
    private String address;
    private String district;
    private String city;
    private String phone;
    private String linkFacebook;
    private String email;
    private String image;
    private String yearStudy;
    private String classCode;//khóa ngoại
    private AcademicModel academicModel;
    private String educationCode;//khóa ngoại
    private EducationModel educationModel;
    private FacultyModel facultyModel;
    private int enrollmentYear;

    public FacultyModel getFacultyModel() {
        return facultyModel;
    }

    public void setFacultyModel(FacultyModel facultyModel) {
        this.facultyModel = facultyModel;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public EducationModel getEducationModel() {
        return educationModel;
    }

    public void setEducationModel(EducationModel educationModel) {
        this.educationModel = educationModel;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public AcademicModel getAcademicModel() {
        return academicModel;
    }

    public void setAcademicModel(AcademicModel academicModel) {
        this.academicModel = academicModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkFacebook() {
        return linkFacebook;
    }

    public void setLinkFacebook(String linkFacebook) {
        this.linkFacebook = linkFacebook;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYearStudy() {
        return yearStudy;
    }

    public void setYearStudy(String yearStudy) {
        this.yearStudy = yearStudy;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getEducationCode() {
        return educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }
}

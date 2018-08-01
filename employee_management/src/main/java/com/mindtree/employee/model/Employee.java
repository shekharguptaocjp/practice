package com.mindtree.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="employeedetails")
public class Employee {
	
		@Id
		@Column(name="emp_id_n")
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long empId;
		
		@Column(name="username_v")
		private String userName;
		
		@Column(name="password_v")
		private String password;
		
		@Column(name="fullName_v")
		private String fullName;
		
		@Column(name="emailId_v")
		private String emailId;
		
		@Column(name="date_of_birthday_v")
		private String dateOfBirth;
		
		@Column(name="gender_v")
		private String gender;
		
		@Column(name="securityQuestion_v")
		private String securityQuestion;
		
		@Column(name="securityAnswer_v")
		private String securityAnswer;
		
		
		public long getEmpId() {
			return empId;
		}
		public void setEmpId(long empId) {
			this.empId = empId;
		}
		
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getSecurityQuestion() {
			return securityQuestion;
		}
		public void setSecurityQuestion(String securityQuestion) {
			this.securityQuestion = securityQuestion;
		}
		public String getSecurityAnswer() {
			return securityAnswer;
		}
		public void setSecurityAnswer(String securityAnswer) {
			this.securityAnswer = securityAnswer;
		}
		public Employee(long empId, String userName, String password, String fullName, String emailId,
				String dateOfBirth, String gender, String securityQuestion, String securityAnswer) {
			super();
			this.empId = empId;
			this.userName = userName;
			this.password = password;
			this.fullName = fullName;
			this.emailId = emailId;
			this.dateOfBirth = dateOfBirth;
			this.gender = gender;
			this.securityQuestion = securityQuestion;
			this.securityAnswer = securityAnswer;
		}
		
		public Employee()
		{
			
		}
}

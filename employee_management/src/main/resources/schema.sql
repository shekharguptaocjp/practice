drop table employeedetails;
CREATE TABLE employeedetails (
  emp_id_n INT NOT NULL,
  username_v VARCHAR(45) NOT NULL,
  password_v VARCHAR(45) NOT NULL,
  full_name_v VARCHAR(45) NULL,
  email_id_v VARCHAR(45) NULL,
  date_of_birthday_v VARCHAR(45) NULL,
  gender_v VARCHAR(45) NULL,
  security_question_v VARCHAR(45) NULL,
  security_answer_v VARCHAR(45) NULL,
  PRIMARY KEY (emp_id_n));
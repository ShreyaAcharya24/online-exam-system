# **Project Overview**

The Online Examination System is a web-based application to provide an efficient platform for conducting online exams, including features for admins to manage exams and students, and for students to take exams.

## Features
 ### Admin functionality: 
 - Manage Student Profile
 - Manage Types, Category and Difficulty Level of Questions.
 - Manage Questions in the QuestionBank Table
 - Create and Manage Exams
 - Enroll Students in Exam
 - Monitor exam participation and completion status
   
### Student functionality: 
- Login
- View and Attempt Exam if they are enrolled and eligible
-  Submit answers

## Technologies Used
### Backend:
- Spring Boot :- For Creating REST APIs
- Spring Data JPA :- For database interaction

### Frontend:
- HTML/CSS :- for StudentLogin page, Exam Instructions Page and Attempting Exam
- JavaScript :- To fetch APIs and provide data dynamically

### Database:
- SQL Server Express: For Storing User, Exam and Question Data

## **Structure**
### Enrollment:
- Admin can add institutes and students 
- Admin can create student profile and assign them institute to which they belong

### Question Module
- Questions are of 2 Types MCQ and Programming
- Questions can be categorised into  Logical, Technical and Coding 
- Difficulty leve Easy, Medium and Hard can be assigned to all the questions
- All the questions are stored in QuestionBank table
- MCQs store options, while programming questions store code submissions

## Exam Module
- Admin can create exams by selecting questions from QuestionBank
- The ExamQuestion table maps Questions with a particular exam
- Students can take exams only if:
            1) They are enrolled in the exam 
            2) Exam status is available

      
- Business logic such as exam eligibility, status checks, and result calculations is handled in the service layer.


## Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/online-examination-system.git
   cd online-examination-system
   ```

2. Set up the database:
   - Create an SQL Express database and update the `application.properties` file with your database credentials.

3. Run the application:
   - Build and run the project using Spring Boot:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the Student login:
   - Visit `http://localhost:8080/login` in your browser.














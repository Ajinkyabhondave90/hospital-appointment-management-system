# 🏥 Hospital Appointment & Patient Management System

A full-stack **Hospital Appointment & Patient Management System** built with **JSP, Servlets, JDBC, and PostgreSQL**, following an MVC-style layered architecture. Supports three roles — **Admin**, **Receptionist**, and **Doctor** — with end-to-end workflows from patient registration through appointment booking, consultation, and prescription.

## ✨ Features

- **Role-based authentication & sessions** — login/logout, session-based `AuthFilter` enforcing access per role
- **Admin module** — manage departments and doctors, dashboard with live appointment/patient stats
- **Receptionist module** — register/search/view patients, book appointments with automatic doctor double-booking prevention, cancel/reschedule, filter + paginate the appointment list
- **Doctor module** — daily appointment queue, consultation form (symptoms/diagnosis/notes) flowing into a multi-medicine prescription form, auto-marks appointments completed
- **Secure data layer** — all SQL via `PreparedStatement`, soft-deletes to preserve historical records
- **Clean, responsive UI** — shared navbar, consistent design system, client-side form validation

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java, Servlets, JSP |
| Database | PostgreSQL (JDBC) |
| Frontend | HTML5, CSS3, Vanilla JavaScript |
| Build | Maven |
| Server | Apache Tomcat |

## 📁 Project Structure

├── src/main/java/com/techcloud/
│   ├── model/              
│   ├── dao/               
│   ├── controller/        
│   └── util/                
└── src/main/webapp/
    ├── *.jsp              
    ├── css/ · js/
    └── WEB-INF/web.xml

## 🚀 Getting Started

### 1. Choose your branch
This repo version depending on your environment:

| Branch | Namespace | Server | JDK |
|---|---|---|---|

| tomcat9-jdk8 | javax.* | **Tomcat 9** | Java 8 |

Pick whichever matches your setup — the features and database are identical, only the servlet/JSTL namespace and a few config files differ.

### 3. Configure the connection
Edit `src/main/java/com/techcloud/util/DBConnection.java` with your local PostgreSQL credentials.

### 4. Build & run
```bash
mvn clean package
```
Deploy the resulting `.war` from `target/` to your Tomcat `webapps/` folder, or import the project directly into Eclipse as a Dynamic Web Project / Existing Maven Project and **Run As → Run on Server**.

### 5. Log in
| Role | Username | Password |
|---|---|---|
| Admin | admin | admin123 |
| Receptionist | reception1 | recep123 |
| Doctor | dr.sharma | doctor123 |

## 🙋 Notes
Passwords are stored in plain text in `sample_data.sql` for simplicity in this training/demo project. In a production deployment, always hash passwords (e.g., BCrypt) before storing or comparing them.

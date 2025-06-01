🏗 Project: Enterprise Asset Monitoring System (EAMS)
🏭 Domain: Manufacturing/Energy
=======================================================

  EAMS is a backend solution designed for manufacturing and energy sectors to efficiently manage factory assets, monitor real-time IoT sensor data, and maintain operational health. It enables registration and tracking of assets, simulates sensor data ingestion, triggers alerts on critical conditions, and logs maintenance and uptime/downtime activities. With role-based access control for Operators and Managers, the system streamlines asset management, maintenance scheduling, and alert handling to ensure optimal factory performance and safety.


🎯 Objective
Create a backend system to:
======================================================
●	Register/manage factory assets (machines, equipment)

●	Simulate and store IoT sensor data (temperature, pressure, etc.)

●	Trigger alerts when sensor values cross thresholds

●	Maintain uptime/downtime logs

●	Enable role-based access for Operators and Managers

●	Schedule maintenance and track logs

🧱 Tech Stack (Backend)
====================================================
Framework	     :   Spring Boot
Persistence	   :   Spring Data JPA
Database	     :   MySQL
Build Tool	   :   Maven / Gradle
Dev Tools	     :   Spring Boot DevTools, Lombok (Optional)
Testing	       :   Junit 
Optional	     :   Swagger UI

 

🔐 Roles & Access
====================================================

Operator : 	View assigned assets, update asset status, log maintenance
Manager	 : Add/edit assets, assign assets, view reports, receive alerts


🧩 Key Modules
====================================================
1.	User & Role Management
   ------------------------------
●	User registration/login

●	Role assignment (Operator, Manager)

●	validation 

2.	Asset Management
    ------------------------------
●	CRUD on assets

●	Assign assets to users

●	Track asset location, type, and threshold limits


3.	Sensor Data Ingestion (Simulation)
    ------------------------------
●	Simulated API to push sensor data

●	Store readings with timestamp

●	Trigger alerts if data > threshold


4.	Alert Management
    ------------------------------
 
●	Create alerts based on rules

●	Store active/resolved alerts

●	Notification simulation (email/log console)


5.	Maintenance & Uptime Logs
    ------------------------------
●	Record scheduled maintenance

●	Auto-calculate uptime/downtime

●	Generate daily/weekly reports




🗃 Entity Design (ERD)
==========================================
User
│── id
│── name
│── email
│── password
│── role (ENUM: MANAGER, OPERATOR)

Asset
│── id
│── name
│── type
│── location
│── thresholdTemp
│── thresholdPressure
│── assignedTo (User)

SensorData
│── id
│── assetId (FK)
│── temperature
│── pressure
 
│── timestamp

Alert
│── id
│── assetId (FK)
│── type (TEMP_HIGH / PRESSURE_HIGH)
│── message
│── status (ACTIVE / RESOLVED)
│── triggeredAt

MaintenanceLog
│── id
│── assetId (FK)
│── scheduledDate
│── completedDate
│── remarks

UptimeLog
│── id
│── assetId (FK)
│── startTime
│── endTime
│── status (UP / DOWN)



🔁 REST API Endpoints
==========================================
📌 AuthController
POST /api/auth/register POST /api/auth/login

📌 UserController (Manager only)
GET /api/users
PUT /api/users/{id}/role
 
📌 AssetController
POST /api/assets	// Manager
GET /api/assets	// Operator/Manager GET /api/assets/{id}
PUT /api/assets/{id} DELETE /api/assets/{id}

📌 SensorDataController
POST /api/sensors/send-data	// Simulated API GET /api/sensors/asset/{id}

📌 AlertController
GET /api/alerts	// All alerts PUT /api/alerts/{id}/resolve // Mark resolved

📌 MaintenanceController
POST /api/maintenance
GET /api/maintenance/asset/{id}


📌 UptimeLogController
GET /api/uptime/asset/{id}



🔐 Spring Security 
=======================================================
Auth Flow
●	User Registers – Bcrypt Password Hashing

 
 

🧪 Sample Use Case
=========================================================
Scenario: A temperature sensor sends data 85°C for Asset#101.

●	If the threshold is 80°C → System creates an Alert

●	Log is stored in SensorData + Alert

●	Operator is notified to schedule maintenance





🗂 Folder Structure
=======================================================
com.eams
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
├── util
└── EamsApplication.java



📆 Sprint Breakdown
===================================================
Week	Deliverable
1	Setup project, implement user/login
2	Add asset management + CRUD
3	Simulate and store sensor data
4	Implement alert logic; view alert history
5	Maintenance + Uptime logging
6	Role-based dashboard view, testing & finalization


------------------------------------------------------------------------------------------------
▶ How To run the project

===================================================

- Clone the repository:  
  git clone https://github.com/pxnchxm1/Enterprise-Asset-Monitoring-System.git

- Configure MySQL and update application.properties with your DB credentials.

- Build and run using Maven:  
  ./mvnw clean install  
  ./mvnw spring-boot:run

- Access Swagger UI at:  
  http://localhost:8080/swagger-ui/index.html

- Register users and start using API endpoints.

--------------------------------------------------

👥 Authors And Contributors
==================================================

See GitHub contributors list for full details.
- [Panchami P Kumar](https://github.com/pxnchxm1) – User Authentication Module
- [Sasanka Poturi] (https://github.com/sasaanka) – User  Module
- [Badri Sai Siddartha Reddy] (https://github.com/SaiSiddartha0906) - Asset Module
- [Mudaliar Saurabh](https://github.com/MudaliarSaurabh) - MaintenanceLog Module
- [A K Harikesh ](https://github.com/akharikesh)- Alert Module
- [Shrinath M ](https://github.com/Shrinath-M) - Uptime Log Module
- [Yogeshwarran S K ](https://github.com/YogeshwarranSK)  - Sensor Data Module

Thanks to all team members and contributors for their collaboration, feedback, and testing.

------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------

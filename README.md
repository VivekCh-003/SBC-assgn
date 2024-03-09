# SBC-assgn

H2 username = sa
H2 password = password

Run the spring application on port "8081"

The default port is "8080" you can change it by updating in application.properties
sever.port = 8081

The application will be accessible at - http://localhost:8081/swagger-ui/index.html

API Endpoints:
1. Get all projects:
	Endpoint: GET /projects

2. Get project by Id:
	Endpoint : GET /project/{id}

3. Upload new project:
	Endpoint: POST /project

4. Delete project by Id:
	Endpoint: DELETE /deleteProject/{id}

5. Update project by id:
	Endpoint : PUT /updateProject/{id}

H2-DATABSE CONSOLE ENDPOINT - http://localhost:8081/h2-console

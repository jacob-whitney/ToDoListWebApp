
#!/bin/bash
cd /home/jacobw/java-projects/ToDoListWebApp
mvn clean compile && mvn package &&
cp /home/jacobw/java-projects/ToDoListWebApp/target/ToDoListWebApp-1.0-SNAPSHOT.war /opt/tomcat/webapps/tdl.war && 
systemctl restart tomcat

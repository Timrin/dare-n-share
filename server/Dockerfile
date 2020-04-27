FROM tomcat:9.0.34-jdk14-openjdk-oracle

#Copy the compiled class files to the container, maintaining the file structure
COPY out/production/server /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

#Copy the web.xml file to the container
COPY conf/web.xml /usr/local/tomcat/webapps/ROOT/WEB-INF/

#Copy libraries from project to tomcat lib folder in the container
COPY resources/lib/json-simple-1.1.1.jar /usr/local/tomcat/lib/

CMD ["catalina.sh", "run"]
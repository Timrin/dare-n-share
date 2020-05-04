        ____                              _____ __
       / __ \____ _________     ____     / ___// /_  ____ _________  
      / / / / __ `/ ___/ _ \   / __ \    \__ \/ __ \/ __ `/ ___/ _ \  
     / /_/ / /_/ / /  /  __/  / / / /   ___/ / / / / /_/ / /  /  __/
    /_____/\__,_/_/   \___/  /_/ /_/   /____/_/ /_/\__,_/_/   \___/ 

## Description
Repository for the Dare n Share backend.  
Dare n Share is an app developed as part of a software projects course.  
For the mobile app see: https://github.com/Timrin/dare_n_share_app. 

## Install and setup
* Install docker if you haven't got it  
https://www.docker.com/get-started

* Download or clone this project  
* Import the server folder as a project in IntelliJ  
  >_Make sure that you import the server folder as a project_  
  >_Also make sure that the src folder is marked as source root_  
* Build the project  

#### Optional
* Install the docker plugin for IntelliJ  
  >_The docker plugin can be used to build docker containers from IntelliJ instead of the command line._

## Start the server
You can launch the server from IntelliJ using the docker plugin or you can choose to launch it from the command line.  
**You only need to do one**

#### Start from within IntelliJ (Requires the docker plugin):
_If you get stuck this might help, https://www.jetbrains.com/help/idea/2016.3/docker.html_
  * In IntelliJ, right click the file Dockerfile in the project root and select "Run 'Dockerfile'"
  * You should now see the "Edit Run Configuration" window. If not manually open it by right clicking 'Dockerfile' in the project view, and selecting 'Edit Dockerfile'
  * Under 'run options' enter `-p 8080:8080` and press 'OK' or 'Run'
  
#### Start from the command line:  
  * _Navigate to the project root (The folder that contains Dockerfile)_  
  * run `docker build -t darenshare:1.0 .`  
  * and then `docker run -p 8080:8080 darenshare:1.0` 
  
You should now be able to access the server on http://localhost:8080  
Try sending an http request using Insomnia or another REST client
  
#### Done
You should now be able to access the server on http://localhost:8080  
Try sending an http request using Insomnia or another REST client

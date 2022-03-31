# TestiniumCaseProject

####Step 1: Select driver (Selenium Grid)
>###### You can decide the which driver you are going to use by changing the "selenium grid" property from the test properties file.
>````
>selenium grid: true
####Step 2: Execute your tests
>######Selenium Grid needs to be running first, turn on Docker, then in project directory start Selenium Grid & Nodes by using the following command:
>````
>docker-compose up -d
####Example execution command:
>In order to execute a specific feature file , add tags to the first line of your feature file & use:
>````
>mvn clean test -Dcucumber.options="--tags @TestiniumCase"
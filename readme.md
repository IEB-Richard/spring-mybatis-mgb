## About this project

A small demo project on how to setup mybatis generator in Spring frame work.

this demo project is created by reference of the resources I found from the internet, It's for delopment note purpose. If you think it's valuable to you, please feel free to fork it.



## How to start up this project

to keep the project simple, the project only contains the `mode` and `mapper` layer development objects, no `service` and `controller` layer classes. But you can test the result with Junit5 test cases.



### Create MySQL Database and Tables

Run the script located at `src/main/resources/sql/dabase_setup.sql` with MySQL workbench or any other client you like. The tables and the testing data are all enclosed in this script file.



### Run the test cases

Go to folder `tk.mybatis.simple.mapper` and open the test java file `CoutryMapperTest.java` and right click -> run as … -> Junit Test


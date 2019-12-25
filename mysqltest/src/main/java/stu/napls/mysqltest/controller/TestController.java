package stu.napls.mysqltest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.napls.mysqltest.util.MySQLConnector;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    @PostMapping("/hash")
    public Integer hash() {
        return 200;
    }

    @PostMapping("/write")
    public Integer write(String statement) {
        MySQLConnector mySQLConnector = new MySQLConnector(DB_URL, USER, PASS);
        mySQLConnector.write(statement);
        return 200;
    }

    @PostMapping("/read")
    public Integer read(String statement) {
        MySQLConnector mySQLConnector = new MySQLConnector(DB_URL, USER, PASS);
        mySQLConnector.read(statement);
        return 200;
    }
}

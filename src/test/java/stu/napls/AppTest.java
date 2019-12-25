package stu.napls;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest 
{
    static int TEST_NUM = 1000;
    static String READ_STATEMENT = "SELECT * FROM person";
    static String WRITE_STATEMENT = "INSERT INTO person (name, age) VALUES ('Michael', 24)";

    @Test
    public void writingTest()
    {
        long start = System.currentTimeMillis();
//        System.out.println(start);
        for (int i = 0; i < TEST_NUM; i++) {
            MySQLConnector mySQLConnector = new MySQLConnector();
            mySQLConnector.write(WRITE_STATEMENT);
        }
        MySQLConnector mySQLConnector = new MySQLConnector();
        mySQLConnector.write("truncate table person;");
        long end = System.currentTimeMillis();
//        System.out.println(end);
        long tps = (long) (TEST_NUM / (double) (end - start) * 1000);
        System.out.println(tps);
    }

    @Test
    public void readingTest()
    {
        long start = System.currentTimeMillis();
//        System.out.println(start);
        for (int i = 0; i < TEST_NUM; i++) {
            MySQLConnector mySQLConnector = new MySQLConnector();
            mySQLConnector.read(READ_STATEMENT);
        }
        long end = System.currentTimeMillis();
//        System.out.println(end);
        long tps = (long) (TEST_NUM / (double) (end - start) * 1000);
        System.out.println(tps);
    }

}

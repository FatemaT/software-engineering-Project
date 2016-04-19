package com.cs.se.ta.db;

import java.sql.*;

public class DBConnectionMgr {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL =
        "jdbc:mysql://stiwari5-cluster.cluster-cltssb30vxlo.us-east-1.rds.amazonaws.com:3306/tourist_assist";

    //  Database credentials
    static final String USER = "ta_user";
    static final String PASS = "welcome1";
    
    private static DBConnectionMgr dbConnectionMgr = new DBConnectionMgr();
    private DBConnectionMgr() {
        //Private constructor for connection manager
        }
    public static DBConnectionMgr getInstnace() {
        return dbConnectionMgr;
        }


    public static SQLAggregatedConnectionObjects fetchResultSetFromQuery(String query, SQLAggregatedConnectionObjects sqlObjects) {
        try {
            Class.forName(JDBC_DRIVER);
            
            sqlObjects.setConnection(DriverManager.getConnection(DB_URL, USER, PASS));
            sqlObjects.setStatement(sqlObjects.getConnection().createStatement());
            sqlObjects.setResultSet(sqlObjects.getStatement().executeQuery(query));
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sqlObjects;
    }
    
    public static void closeConnectionObjects(SQLAggregatedConnectionObjects sqlObjects) throws SQLException {
        sqlObjects.getConnection().close();
        sqlObjects.getResultSet().close();
        sqlObjects.getStatement().close();
    }

}

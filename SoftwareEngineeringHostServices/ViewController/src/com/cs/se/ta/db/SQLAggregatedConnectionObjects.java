package com.cs.se.ta.db;
import java.sql.*;

public class SQLAggregatedConnectionObjects {
    private ResultSet resultSet;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public SQLAggregatedConnectionObjects() {
        super();
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }
}

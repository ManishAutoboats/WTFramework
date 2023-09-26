package com.salmon.test.framework.helpers;

import com.jcraft.jsch.Session;
import com.salmon.test.framework.helpers.utils.DBTunnel;
import com.salmon.test.framework.helpers.utils.EncryptUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.util.SocketUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static com.salmon.test.framework.helpers.UrlBuilder.profilePropsMap;
import static java.lang.String.format;

@Slf4j
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class BatDBHelper {
    String dbUrl;
    String dbDriver;
    String dbUserName;
    String dbPassword;
    String schema;
    Connection connection;
    final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    List<Session> sshSessions;
    static int portNumber;
    static final String RUN_CONFIG_PROPERTIES = "/environment.properties";

    public BatDBHelper() {
        String localPort = String.valueOf(getFreePortNumber());
        dbUrl = profilePropsMap().get("bat.datasource.url").replace("<localPort>", localPort);
        dbUserName = profilePropsMap().get("bat.datasource.username");
        dbPassword = profilePropsMap().get("bat.datasource.password"+"_"+UrlBuilder.getEnv());
    }

    public Connection setUpConnection() {
        Properties connectionParams = new Properties();
        connectionParams.setProperty("user", dbUserName);
        connectionParams.setProperty("password", EncryptUtils.decrypt(dbPassword, EncryptUtils.generateSecretKey(UrlBuilder.getSecret())));

        DbUtils.loadDriver(DRIVER_CLASS_NAME);
        try {
            sshSessions = new DBTunnel().sshToRemoteDB();
            connection = DriverManager.getConnection(dbUrl, connectionParams);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AssertionError("Error while making connection with DB");
        }
        return connection;
    }

    public <T> List<T> executeQueryToObject(Class<T> c, String query) {
        connection = setUpConnection();
        try {
            return getQueryRunner().query(
                    connection, query, new BeanListHandler<T>(c));
        } catch (SQLException e) {
            log.error("Error while executing query > "+ e);
        } finally {
            closeTunnelAndDBConnection(connection, getSshSessions());
        }
        return null;
    }

    public List<Map<String, Object>> executeQueryToMap(String query) {
        connection = setUpConnection();
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            result = getQueryRunner().query(connection, query, new MapListHandler());
        } catch (SQLException e) {
            log.error("Error while executing query > "+ e);
        } finally {
            closeTunnelAndDBConnection(connection, getSshSessions());
        }
        return result;
    }


    public <T> List<T> executeParamQueryToObject(Class<T> c, String query, String... params) {
        connection = setUpConnection();
        try {
            return getQueryRunner().query(
                    connection, query, new BeanListHandler<T>(c), params);
        } catch (SQLException e) {
            log.error("Error while executing query > "+ e);
        } finally {
            closeTunnelAndDBConnection(connection, getSshSessions());
        }
        return null;
    }

    public int deleteOrUpdate(String query) {
        Connection connection = setUpConnection();
        int count = 0;
        try {
            count = getQueryRunner().update(connection, query);
        } catch (SQLException e) {
            if (!e.getMessage().startsWith("No results were returned")) {
                log.error("Error while updating/deleting the data > "+ e);
            }
        } finally {
            closeTunnelAndDBConnection(connection, getSshSessions());
        }
        return count;
    }

    public void insert(String query) {
        Connection connection = setUpConnection();
        try {
            getQueryRunner().insert(connection, query, new ScalarHandler<>());
        } catch (SQLException e) {
            if (!e.getMessage().startsWith("No records were inserted")) {
                log.error("Error while inserting the data > "+ e);
            }
        } finally {
            closeTunnelAndDBConnection(connection, getSshSessions());
        }
    }

    public static QueryRunner getQueryRunner() {
        return new QueryRunner();
    }

    @Override
    public void finalize() {
        closeTunnelAndDBConnection(connection, getSshSessions());
    }


    //---------- DB utils
    public static int getFreePortNumber() {
        return Optional.of(portNumber)
                .filter(p -> 0 != p)
                .orElseGet(() -> {
                    portNumber = SocketUtils.findAvailableTcpPort();
                    return portNumber;
                });
    }

    public static void closeTunnelAndDBConnection(Connection connection, List<Session> sessions) {
        try {
            if (null != connection && !connection.isClosed()) {
                connection.close();
            }

            Optional.ofNullable(sessions)
                    .ifPresent(s -> {
                        s.stream()
                                .filter(session -> null != session && session.isConnected())
                                .forEach(Session::disconnect);
                    });
        } catch (Exception e) {
            throw new RuntimeException(format("Error while closing DB connection and SSH session", e.getCause()));
        }
    }

}

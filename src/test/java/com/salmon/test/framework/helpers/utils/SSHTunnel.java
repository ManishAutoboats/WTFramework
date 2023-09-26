package com.salmon.test.framework.helpers.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.Data;

import java.util.Properties;

@Data
public class SSHTunnel {

    public static Session sshToRemoteServer(JSch jSch, String strSshUser, String strSshPassword, String bastionServer, int sshPort) throws JSchException {
        Session session = jSch.getSession(strSshUser, bastionServer, sshPort);
        session.setPassword(strSshPassword);
        session.setConfig(getConfig());
        session.connect();
        return session;
    }

    private static Properties getConfig() {
        final Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        return config;
    }

}

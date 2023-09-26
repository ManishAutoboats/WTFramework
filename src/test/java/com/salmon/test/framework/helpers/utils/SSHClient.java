package com.salmon.test.framework.helpers.utils;



import com.jcraft.jsch.*;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.InputStream;

import java.util.Random;

public class SSHClient {

    static final Logger LOG = LoggerFactory.getLogger(SSHClient.class);
    private JSch jsch = new JSch();
    private Session session;
    private Session session1;
    private Session session2;
    private Channel channel = null;
    private ChannelSftp channelSftp = null;
    private Session jmpHostSession;
    private int exitCode = 999;

    //Connect to unix machine
    public void connect(String machineNameOrIpAddress, String user, String password) throws IOException {
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session = jsch.getSession(user, machineNameOrIpAddress, 22);
            LOG.info("default timeout on jsch session ----> " + session.getTimeout());
            session.setTimeout(20000);
            LOG.info("timeout now set on jsch session ----> " + session.getTimeout());
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            LOG.info("Connected to Unix machine");
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    //connect to unix with autotest
    public void connectViaBastion1() throws IOException {
        try {

            java.util.Properties config = new java.util.Properties();
            String bastion= UrlBuilder.readValueFromConfig("Bastion.location");
            String devsystem= UrlBuilder.readValueFromConfig("Devsystem");
            String sshkey= UrlBuilder.readValueFromConfig("sshkey");
           // Random rand = new Random();
            //String RandomNumber = String.format("%4d", rand.nextInt(10000));
            int tport= (int)(Math.random()*9000)+1000;
            LOG.info("tport is :::::::::::::"+tport);

            config.put("StrictHostKeyChecking", "no");
            config.put("GSSAPIAuthentication", "no");
            config.put("ChallengeResponseAuthentication", "no");
            config.put("PasswordAuthentication", "no");
            //Session session1;
            //Session session2;
            Channel channel = null;
            ChannelSftp sftp = null;
            jsch.addIdentity(sshkey);

            // Step 1 is to establish a connection to bastion and create a tunnel
            session1 = jsch.getSession("jenkins", bastion, 22);
            session1.setConfig(config);
            session1.setTimeout(4000);
            session1.connect();
            session1.setPortForwardingL(tport, devsystem, 22);
            LOG.info("System Name" + "Host name" + devsystem);
            LOG.info("Have created session 1 jenkins"+ "Host name" + session1.getHost());

            // Step 2 is to establish a connection through the tunnel
            session2 = jsch.getSession("autotest1", "127.0.0.1", tport);
            session2.setConfig(config);
            session2.setTimeout(4000);
            session2.connect();
            LOG.info("Have created session 2 autotest1 and isConnedted()=" + session2.isConnected() + "and ID=" + System.identityHashCode(session2));
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    //connect to unix with int user
    public void connectViaBastion2(String brand) throws IOException {
        try {

            java.util.Properties config = new java.util.Properties();
            String bastion= UrlBuilder.readValueFromConfig("Bastion.location");
            String devsystem= UrlBuilder.readValueFromConfig("Devsystem");
            String sshkey= UrlBuilder.readValueFromConfig("sshkey");
          //  Random rand = new Random();
           // String RandomNumber = String.format("%4d", rand.nextInt(10000));
            int tport= (int)(Math.random()*9000)+1000;
            LOG.info("tport is :::::::::::::"+tport);

            config.put("StrictHostKeyChecking", "no");
            config.put("GSSAPIAuthentication", "no");
            config.put("ChallengeResponseAuthentication", "no");
            config.put("PasswordAuthentication", "no");
            //Session session1;
            //Session session2;
            Channel channel = null;
            ChannelSftp sftp = null;
            jsch.addIdentity(sshkey);

            // Step 1 is to establish a connection to bastion and create a tunnel
            session1 = jsch.getSession("jenkins", bastion, 22);
            session1.setConfig(config);
            session1.setTimeout(4000);
            session1.connect();
            session1.setPortForwardingL(tport, devsystem, 22);
            LOG.info("System Name" + "Host name" + devsystem);
            LOG.info("Have created session 1 jenkins" + "Host name" + session1.getHost());

            // Step 2 is to establish a connection through the tunnel
            session2 = jsch.getSession("int" + brand.toLowerCase(), "127.0.0.1", tport);
            session2.setConfig(config);
            session2.setTimeout(4000);
            session2.connect();
            LOG.info("Have created session 2 int" + brand.toLowerCase() + " and isConnedted()=" + session2.isConnected() + "and ID=" + System.identityHashCode(session2)+ "Host name" + session2.getHost());
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
//Connect to MQ
    public void connectViaBastion3() throws IOException {
        try {

            java.util.Properties config = new java.util.Properties();
            java.util.Properties config1 = new java.util.Properties();
            String bastion= UrlBuilder.readValueFromConfig("Bastion.location");
            String devsystem= UrlBuilder.readValueFromConfig("Devsystem");
            String sshkey= UrlBuilder.readValueFromConfig("sshkey");
            String sshkey1= UrlBuilder.readValueFromConfig("sshkey1");

            int tport= (int)(Math.random()*9000)+1000;
            LOG.info("tport is :::::::::::::"+tport);

            config.put("StrictHostKeyChecking", "no");
            config.put("GSSAPIAuthentication", "no");
            config.put("ChallengeResponseAuthentication", "no");
            config.put("PasswordAuthentication", "no");

            config1.put("StrictHostKeyChecking", "no");
            config1.put("GSSAPIAuthentication", "no");
            config1.put("ChallengeResponseAuthentication", "yes");
            config1.put("PasswordAuthentication", "yes");
            //Session session1;
            //Session session2;
            Channel channel = null;
            ChannelSftp sftp = null;
            jsch.addIdentity(sshkey);

            // Step 1 is to establish a connection to bastion and create a tunnel
            session1 = jsch.getSession("jenkins", bastion, 22);
            session1.setConfig(config);
            session1.setTimeout(4000);
            session1.connect();
            session1.setPortForwardingL(tport, devsystem, 22);
            LOG.info("System Name" + "Host name" + devsystem);
            LOG.info("Session1 created session 1 jenkins" + "Host name" + session1.getHost());

            // Step 2 is to establish a connection to localhost and create a tunnel
            //jsch.addIdentity(sshkey1);
            session2 = jsch.getSession("devandranm@ynapdev.lcl", "127.0.0.1", tport);
            session2.setPassword("June@123");
            session2.setConfig(config1);
            session2.setTimeout(4000);
            session2.connect();
            session2.setPortForwardingL(2415, "127.0.0.1", 1415);
            LOG.info("Session2 created session 2 localhost" + "Host name" + session2.getHost());

            LOG.info("Have created MQ session in jenkins" + "Host name" + session2.getHost());

        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    //connect to systest with proxy
    public void connectSystestWithProxy(String machineNameOrIpAddress, String userName) throws IOException {
        try {
            LOG.info("Started connecting to Unix machine");
//            String proxyCommand = "ssh -W %h:%p 52.58.246.16";
//            java.util.Properties config = new java.util.Properties();
//            config.put("StrictHostKeyChecking", "no");
//            jsch.addIdentity("~/.ssh/wsclnx-dev.pem");
//            session = jsch.getSession(user, machineNameOrIpAddress, 22);
//            session.setPassword(password);
//            session.setConfig(config);
//            session.setConfig("GSSAPIAuthentication", "no");
//            //session.setProxy(new ProxySOCKS5("52.58.246.16",22));
//            // create port from 2233 on local system to port 22 on tunnelRemoteHost
//            session.setPortForwardingL(2233, "52.58.246.16", 22);
//            session.connect();

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            config.put("GSSAPIAuthentication", "no");
            config.put("ChallengeResponseAuthentication", "no");
            config.put("PasswordAuthentication", "no");
//            String privateKey = UrlBuilder.readValueFromConfig("bastion.privateKey");
            String privateKey = "C:\\Users\\gandhamp\\.ssh\\id_rsa";
            jsch.addIdentity(privateKey);
            Session session = jsch.getSession(userName, machineNameOrIpAddress, 22);
            session.setConfig(config);
            LOG.info("session created.............");

            session.setPortForwardingL(15211, UrlBuilder.readValueFromConfig("db.machineName"), 1521);
            session.connect();

            LOG.info("Tunneling created with proxy");
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

//    //connect to systest with proxy
//    public void connectSystestWithbastion(String machineNameOrIpAddress, String userName) throws IOException {
//        try {
//            LOG.info("Started connecting to Unix machine");
////            String proxyCommand = "ssh -W %h:%p 52.58.246.16";
////            java.util.Properties config = new java.util.Properties();
////            config.put("StrictHostKeyChecking", "no");
////            jsch.addIdentity("~/.ssh/wsclnx-dev.pem");
////            session = jsch.getSession(user, machineNameOrIpAddress, 22);
////            session.setPassword(password);
////            session.setConfig(config);
////            session.setConfig("GSSAPIAuthentication", "no");
////            //session.setProxy(new ProxySOCKS5("52.58.246.16",22));
////            // create port from 2233 on local system to port 22 on tunnelRemoteHost
////            session.setPortForwardingL(2233, "52.58.246.16", 22);
////            session.connect();
//
//            java.util.Properties config = new java.util.Properties();
//            config.put("StrictHostKeyChecking", "no");
//            config.put("GSSAPIAuthentication", "no");
//            config.put("ChallengeResponseAuthentication", "no");
//            config.put("PasswordAuthentication", "no");
//            String privateKey = UrlBuilder.readValueFromConfig("bastion.privateKey");
//            jsch.addIdentity(privateKey);
////            Session session = jsch.getSession(userName, machineNameOrIpAddress, 22);
//            session = jsch.getSession(userName, machineNameOrIpAddress, 22);
//            session.setConfig(config);
//            LOG.info("session created.............");
//            session.connect();
//
////            --session.setPortForwardingL(15211, UrlBuilder.readValueFromConfig("db.machineName"), 1521);
////            session.setPortForwardingL(2200, UrlBuilder.readValueFromConfig("host.ip"), 22);
////
////            this.session = jsch.getSession(userName, machineNameOrIpAddress, 2200);
////            this.session.setConfig(config);
////            this.session.connect();
//
//            LOG.info("Tunneling created with proxy");
//        } catch (JSchException e) {
//            e.printStackTrace();
//        }
//    }

    public void connectSystestWithbastion(String machineNameOrIpAddress, String userName) throws IOException {
        String bastionMachineNameOrIpAddress = UrlBuilder.readValueFromConfig("bastion.ip");
        String bastionUserName =  UrlBuilder.readValueFromConfig("bastion.user");
        int tport=2201;
        Random random = new Random();
//        int tport = random.nextInt(10000);
        try {
            LOG.info("Started connecting to Unix machine");
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            config.put("GSSAPIAuthentication", "no");
            config.put("ChallengeResponseAuthentication", "no");
            config.put("PasswordAuthentication", "no");
            String privateKey = UrlBuilder.readValueFromConfig("bastion.privateKey");
            jsch.addIdentity(privateKey);
//            Session session = jsch.getSession(userName, machineNameOrIpAddress, 22);
            Session session = jsch.getSession(bastionUserName, bastionMachineNameOrIpAddress, 22);
            session.setConfig(config);
            LOG.info("session created.............");
            session.connect();
            session.setPortForwardingL(tport, machineNameOrIpAddress, 22);
            LOG.info("Have created session 1 to bastion");

            // Step 2 is to establish a connection through the tunnel
            Session session2 = jsch.getSession(userName, "127.0.0.1", tport);
            session2.setConfig(config);
            session2.setTimeout(4000);
            session2.connect();
            LOG.info("Have created session 2 to the destinatio host "+machineNameOrIpAddress);

            this.session = session2;
            this.jmpHostSession = session;

            LOG.info("Tunneling created with proxy");
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void sshTunneling() throws JSchException {
        LOG.info("Tunneling to the machine");
        session.setPortForwardingL(15211, "yxdwsc01.cujykt1jlonk.eu-central-1.rds.amazonaws.com", 1521);
        LOG.info("Tunneling created");
    }

    //Disconnect the machine
    public void disconnect() {
        channel.disconnect();

        if (session != null) {
            session.disconnect();
        }

        if (session1 != null) {
            session1.disconnect();
        }
        if (session2 != null){
            session2.disconnect();
        }


        if(jmpHostSession != null){
            jmpHostSession.disconnect();
        }

        LOG.info("DisConnected to Unix machine");
    }

    //Copy the file into Unix machine
    public void copyFileToUnix(String srcLocation, String destLocation) throws JSchException, SftpException, IOException {
        channel = session.openChannel("sftp");
        channel.connect();
        channelSftp = (ChannelSftp) channel;
        channelSftp.put(srcLocation, destLocation);
        channelSftp.disconnect();
    }

    public void copyFileToUnixBastion(String srcLocation, String destLocation) throws JSchException, SftpException, IOException {
        channel = session2.openChannel("sftp");
        channel.connect();
        channelSftp = (ChannelSftp) channel;
        channelSftp.put(srcLocation, destLocation);
        channelSftp.disconnect();
    }


    //Remov the file into Unix machine
    public void removeFileToUnix(String fileName) throws JSchException, SftpException, IOException {
        channel = session.openChannel("sftp");
        channel.connect();
        channelSftp = (ChannelSftp) channel;
        channelSftp.rm(fileName);
        channelSftp.disconnect();
    }


    //Copy file from Unix into windows machine
    public void copyFileToWindows(String srcLocation, String destLocation) throws JSchException, SftpException, IOException {
        channel = session.openChannel("sftp");
        channel.connect();
        channelSftp = (ChannelSftp) channel;
        channelSftp.get(srcLocation, destLocation);
        channelSftp.disconnect();
    }


    public void copyFileToWindowsbastion(String srcLocation, String destLocation) throws JSchException, SftpException, IOException {
        channel = session2.openChannel("sftp");
        channel.connect();
        channelSftp = (ChannelSftp) channel;
        channelSftp.get(srcLocation, destLocation);
        channelSftp.disconnect();
    }



        //Execute the command into unix machine
    public void executeCommand(String cmd) throws JSchException, IOException {
        channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(cmd);
        ((ChannelExec) channel).setPty(true);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);

        InputStream in = channel.getInputStream();
        channel.connect();
        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0)
                    break;
                LOG.info(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                LOG.info("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
            }
        }
    }

    //Execute the command into unix machine
    public void executeCommandBastion(String cmd) throws JSchException, IOException {
        LOG.info("Trying to use session 2 and isConnedted()=" + session2.isConnected() + "and ID=" + System.identityHashCode(session2));
        channel = session2.openChannel("exec");
        ((ChannelExec) channel).setCommand(cmd);
        ((ChannelExec) channel).setPty(true);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);

        InputStream in = channel.getInputStream();
        channel.connect();
        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0)
                    break;
                LOG.info(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                LOG.info("exit-status: " + channel.getExitStatus());
                setExitCode(channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
            }
        }
    }


    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }
}

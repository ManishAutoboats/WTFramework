package com.salmon.test.framework.helpers.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.salmon.test.framework.helpers.UrlBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.salmon.test.framework.helpers.BatDBHelper.getFreePortNumber;
import static com.salmon.test.framework.helpers.Props.getProp;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

@Slf4j
@FieldDefaults(level=AccessLevel.PRIVATE)
public class DBTunnel {
    String sshUserName = getProp("ssh.user.name");
    String sshPassword = getProp("ssh.user.password");
    String sshJumpHost = getProp("ssh.server.bastion.host"+"_"+UrlBuilder.ENVIRONMENT);
    int sshServerPort = parseInt(getProp("ssh.server.port"));
    int dbLocalPort = getFreePortNumber();
    int dbRemotePort = parseInt(getProp("bat.db.remote.port"));
    String dbRemoteHost = getProp("bat.db.host");
    String sshUserKey = getProp("ssh.user.key");
    String sshUserPassphrase = getProp("ssh.user.passphrase");

    public List<Session> sshToRemoteDB() {
        List<Session> sessions = new ArrayList<>();
        Session session;
        try {
            final JSch jSch = new JSch();
            if(null != sshUserKey && null != sshUserPassphrase) {
                jSch.addIdentity(sshUserKey, EncryptUtils.decrypt(sshUserPassphrase, EncryptUtils.generateSecretKey(UrlBuilder.getSecret())));
            } else if(null != sshUserKey) {
                jSch.addIdentity(sshUserKey);
            }
            session = SSHTunnel.sshToRemoteServer(jSch, sshUserName, EncryptUtils.decrypt(sshPassword, EncryptUtils.generateSecretKey(UrlBuilder.getSecret())), sshJumpHost, sshServerPort);
            Optional.ofNullable(session)
                    .filter(Session::isConnected)
                    .orElseThrow(() -> new AssertionError("SSH to remote host couldn't be established"));
            session.setPortForwardingL("127.0.0.1", dbLocalPort, dbRemoteHost, dbRemotePort);
            sessions.add(session);
        } catch (JSchException e) {
            throw new RuntimeException(format("Error creating SSH session %s", e.getCause()));
        }
        return sessions;
    }
}

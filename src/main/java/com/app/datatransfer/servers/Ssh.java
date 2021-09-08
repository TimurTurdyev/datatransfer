package com.app.datatransfer.servers;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Ssh {
    private String user;
    private String host;
    private String password;

    private ChannelSftp channelSftp;

    public Ssh(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public void connect() {
        int port = 22;

        String remoteFile = "/home/???";

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);

            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");

            session.connect();
            System.out.println("Connection established.");
            System.out.println("Crating SFTP Channel.");
            channelSftp = (ChannelSftp) session.openChannel("sftp");

            channelSftp.connect();
            System.out.println("SFTP Channel created.");

        } catch (Exception e) {
            System.err.print(e);
        }
    }
}

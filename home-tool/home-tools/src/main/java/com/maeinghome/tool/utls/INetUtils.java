package com.maeinghome.tool.utls;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class INetUtils {
    public static final String LOCAL_HOST = "127.0.0.1";

    public INetUtils() {
    }

    public static String getHostName() {
        String hostname;
        try {
            InetAddress address = InetAddress.getLocalHost();
            hostname = address.getHostName();
            if (hostname == null || "".equals(hostname)) {
                hostname = address.toString();
            }
        } catch (UnknownHostException var2) {
            hostname = LOCAL_HOST;
        }

        return hostname;
    }

    public static String getHostIp() {
        String hostAddress;
        try {
            InetAddress address = getLocalHostLANAddress();
            hostAddress = address.getHostAddress();
            if (hostAddress == null || "".equals(hostAddress)) {
                hostAddress = address.toString();
            }
        } catch (UnknownHostException var2) {
            hostAddress = LOCAL_HOST;
        }

        return hostAddress;
    }

    private static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            Enumeration ifaces = NetworkInterface.getNetworkInterfaces();

            while(ifaces.hasMoreElements()) {
                NetworkInterface iface = (NetworkInterface)ifaces.nextElement();
                Enumeration inetAddrs = iface.getInetAddresses();

                while(inetAddrs.hasMoreElements()) {
                    InetAddress inetAddr = (InetAddress)inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            return inetAddr;
                        }

                        if (candidateAddress == null) {
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }

            if (candidateAddress != null) {
                return candidateAddress;
            } else {
                InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
                if (jdkSuppliedAddress == null) {
                    throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
                } else {
                    return jdkSuppliedAddress;
                }
            }
        } catch (Exception var5) {
            UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + var5);
            unknownHostException.initCause(var5);
            throw unknownHostException;
        }
    }

    public static boolean tryPort(int port) {
        try {
            ServerSocket ignore = new ServerSocket(port);
            Throwable var2 = null;

            boolean var3;
            try {
                var3 = true;
            } catch (Throwable var13) {
                var2 = var13;
                throw var13;
            } finally {
                if (ignore != null) {
                    if (var2 != null) {
                        try {
                            ignore.close();
                        } catch (Throwable var12) {
                            var2.addSuppressed(var12);
                        }
                    } else {
                        ignore.close();
                    }
                }

            }

            return var3;
        } catch (Exception var15) {
            return false;
        }
    }
}


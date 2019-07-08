/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.App;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amir
 */
public class Global {
///////////////////////////////////////////////////////////////////////////////////////

    public final static boolean ON = true;
    public final static boolean OFF = false;

///////////////////////////////////////////////////////////////////////////////////////
    public static class ShowDialog {

        public static boolean IsON = OFF;

    }
///////////////////////////////////////////////////////////////////////////////////////

    public static class ElasticConfiguration {

        public static boolean IsON = ON;
        public static final String IndexName = "jmachinelearning_persianword";
        public static final String Type = "type";
        public static final String IP = "localhost";
        public static final int PORT = 9200;

    }
///////////////////////////////////////////////////////////////////////////////////////

    public static class AppThreads {

        public static final boolean IS_ON = ON;
        public static List<Thread> THREADS;

        public static final boolean HoldThreads = ON;
        public static int MaximumRunningThread = 6000;

        public static boolean IsWait() {

            if (HoldThreads
                    && getRunningThreads() > 5000) {
                return true;
            }
            return false;
        }

        public static int getRunningThreads() {
            return java.lang.Thread.activeCount();
        }
    }
///////////////////////////////////////////////////////////////////////////////////////

    public static boolean Init() {
        AppThreads.THREADS = new ArrayList<Thread>();
        AppAutomatic._startTime = new Date();

        return true;
    }
///////////////////////////////////////////////////////////////////////////////////////

    public static class AppAutomatic {

        public final static boolean IsON = OFF;
        private static Date _startTime;
        private static final double StopAfter_hr = 3 * 3600000;
        private static final boolean CallEndFunction = false;

        public static boolean IsEnd() {
            double now = new Date().getTime();
            System.out.println("Time to end: " + ((_startTime.getTime() + StopAfter_hr - now) / 3600000) + " hr(s)");
            if (now - _startTime.getTime() < StopAfter_hr) {
                return false;
            }
            Global.AppLog.Log("End at " + new java.util.Date());
            if (CallEndFunction) {
                Ending();
            }
            return true;
        }

        public static Date getStart() {
            return AppAutomatic._startTime;
        }

        private static boolean Ending() {

            try {

                System.out.println("Ending function started ....");

                Runtime runtime = Runtime.getRuntime();
                Process proc = runtime.exec("shutdown -h +1");
                System.exit(0);

            } catch (IOException ex) {
                Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }
///////////////////////////////////////////////////////////////////////////////////////

    public static class AppLog {

        private static String logFileName;
        public final static String LogPath = "/home/amir/NetBeansProjects/PersianWordCollector/Logs";
        public static String logfile = LogPath + "/" + logFileName;

        private static String ErrorLogFileName;
        private final static String ErrorLogPath = "/home/amir/NetBeansProjects/PersianWordCollector/ErrorLogs";
        private static String Errorlogfile = ErrorLogPath + "/" + ErrorLogFileName;

        private static boolean logInited = false;

        public final static boolean IS_ON = ON;

        private static boolean Init() {

            File file = null;
            if (AppLog.LogPath != null) {
                file = new File(AppLog.LogPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            if (AppLog.ErrorLogPath != null) {
                file = new File(AppLog.ErrorLogPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            double temp = new Date().getTime();
            if (AppLog.logFileName == null) {
                AppLog.logFileName = "LogVer" + temp + ".txt";
            }
            if (AppLog.ErrorLogFileName == null) {
                AppLog.ErrorLogFileName = "erLogVer" + temp + ".txt";
            }
            logfile = LogPath + "/" + logFileName;
            return logInited = true;
        }

        public static boolean Log(String log) {
            try {
                if (!AppLog.IS_ON) {
                    return false;
                }
                if (!logInited) {
                    Init();
                }

                File file = new File(logfile);
                if (!file.exists()) {
                    file.createNewFile();
                }
                Files.write(Paths.get(AppLog.LogPath + "/" + AppLog.logFileName),
                        (log + "\n").getBytes(),
                        StandardOpenOption.APPEND);
            } catch (IOException ex) {
                Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

        public static boolean LogError(String log) {
            try {
                if (!logInited) {
                    Init();
                }

                logfile = LogPath + "/" + logFileName;

                File file = new File(logfile);
                if (!file.exists()) {
                    file.createNewFile();
                }
                Files.write(Paths.get(AppLog.LogPath + "/" + AppLog.logFileName),
                        (log + "\n").getBytes(),
                        StandardOpenOption.APPEND);
            } catch (IOException ex) {
                Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }
    ///////////////////////////////////////////////////////////////////////////////////////
}

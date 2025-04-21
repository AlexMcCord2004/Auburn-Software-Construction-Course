package edu.au.cpsc.module6;

import java.io.*;

public class Db {

    public static final File DEFAULT_FILE = new File("flights.dat");

    private static AirlineDatabase airlineDatabase = null;

    public static AirlineDatabase getDatabase() {
        if (airlineDatabase == null) {
            airlineDatabase = loadDatabase();
        }
        return airlineDatabase;
    }

    private static AirlineDatabase loadDatabase() {
        try (InputStream is = new FileInputStream(DEFAULT_FILE)) {
            return AirlineDatabaseIO.load(is);
        } catch (IOException | ClassNotFoundException ex) {
            return new AirlineDatabase();
        }
    }

    public static void saveDatabase() {
        try (OutputStream os = new FileOutputStream(DEFAULT_FILE)) {
            AirlineDatabaseIO.save(getDatabase(), os);
        } catch (IOException ex) {
            System.err.println("Error saving database: " + DEFAULT_FILE);
            ex.printStackTrace();
            System.exit(-1); // Fail hard if we can’t save — or customize
        }
    }
}

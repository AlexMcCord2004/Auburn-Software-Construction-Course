package edu.au.cpsc.module6;

import java.io.*;

public class AirlineDatabaseIO {

    public static void save(AirlineDatabase ad, OutputStream stream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(ad);
        out.close();
    }

    public static AirlineDatabase load(InputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(stream);
        AirlineDatabase db = (AirlineDatabase) in.readObject();
        in.close();
        return db;
    }
}

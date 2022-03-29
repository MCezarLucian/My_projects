package data;

import presentation.DeliveryServiceProcesing;

import java.io.*;

public  class Serialization implements Serializable {

    public Object read(String path){
        Object object = null;

        try {
            FileInputStream inputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            if (inputStream.getChannel().size() != 0) {
                object = objectInputStream.readObject();
                objectInputStream.close();
                inputStream.close();
            }
            else{
                DeliveryServiceProcesing d = new DeliveryServiceProcesing();
                object = d;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public void update(Object object, String path) {

        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream fileOut = new ObjectOutputStream(file);

            fileOut.writeObject(object);

            //fileOut.flush();
            fileOut.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

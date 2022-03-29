package dao;


import connection.ConnectionDB;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {

    protected static final Logger log = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private List<T> createObjects(ResultSet resultSet){
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;
        for( int i = 0; i < constructors.length; i++){
            constructor = constructors[i];
            if(constructor.getGenericParameterTypes().length == 0){
                break;
            }
        }
        try{
            while(resultSet.next()){
                constructor.setAccessible(true);
                T instance = (T)constructor.newInstance();
                for(Field field : type.getDeclaredFields()){
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        } catch (SecurityException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<T> findAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String s = "SELECT * FROM " + type.getSimpleName();
        System.out.println(s);
        try{
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(s);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);

        } catch (SQLException e) {
            log.log(Level.WARNING, type.getName() + "DAO : findAll " + e.getMessage());
        }finally {
            ConnectionDB.close(resultSet);
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
        return null;
    }

    public void insert(T ob) {
        Connection connection = null;
        PreparedStatement statement = null;
        String s = "INSERT INTO " + type.getSimpleName() + " VALUES(";
        try {
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(ob);
                if (obj instanceof String) {
                    s = s + "'" + obj.toString() + "',";
                } else {
                    s = s + obj.toString() + ",";
                }
            }
            s = s.substring(0, s.length() - 1);
            s = s + ")";
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(s);
            System.out.println(statement);
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            log.log(Level.WARNING, type.getName() + "DAO : insert " + e.getMessage());
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
    }

    public void update(T ob){
        Connection connection = null;
        PreparedStatement statement = null;
        String s = "UPDATE " + type.getSimpleName() + " set ";
        int id = 0;
        try{
            for(Field field : type.getDeclaredFields()){
                field.setAccessible(true);
                Object obj = field.get(ob);
                if(field.getName().equals("id")){
                    id = (int) obj;
                }
                else{

                    if(obj instanceof String){
                        s = s + field.getName() + "='" + obj.toString() + "',";
                    }
                    else{
                        s = s + field.getName() + "=" + obj.toString() + ",";
                    }
                }
            }
            s = s.substring(0, s.length() - 1);
            s = s + " where id=" + id;
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(s);
            System.out.println(statement);
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            log.log(Level.WARNING, type.getName() + "DAO : update " + e.getMessage());
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
    }

    public void delete(T ob){
        Connection connection = null;
        PreparedStatement statement = null;
        String s = "DELETE FROM " + type.getSimpleName() + " WHERE id=";
        int id = 0;
        try{
            for(Field field : type.getDeclaredFields()){
                field.setAccessible(true);
                Object obj = field.get(ob);
                if(field.getName().equals("id")) {
                    id = (int) obj;
                }
            }
            s = s + id;
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(s);
            System.out.println(statement);
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            log.log(Level.WARNING, type.getName() + "DAO : DELETE " + e.getMessage());
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
    }

    public Object[] getTableHeader(){
        Field[] fields = type.getDeclaredFields();
        Object[] header = new Object[fields.length];
        for(int i = 0 ; i < fields.length; i++){
            String s = fields[i].getName();
            header[i] = (String.valueOf((s.charAt(0)))).toUpperCase() + s.substring(1, s.length());
        }
        return header;
    }

    public Object[][] getTable(List<T> dataList){
        Field[] fields = type.getDeclaredFields();
        Object[][] objects = new Object[dataList.size()][fields.length];
        try{
            for(int i = 0; i < dataList.size(); i++){
                for(int j = 0; j < fields.length; j++){
                    fields[j].setAccessible(true);
                    objects[i][j] = fields[j].get(dataList.get(i));                }
            }
            return objects;
        } catch (IllegalAccessException e) {
            log.log(Level.WARNING, type.getName() + "DAO : getTable " + e.getMessage());
        }
        return null;
    }

    public T findByID(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String s = "SELECT  *  FROM " + type.getSimpleName() +
                " WHERE id = ?";
        try{
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(s);
            statement.setInt(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            log.log(Level.WARNING, type.getName() + "DAO : findByID " + e.getMessage());
        } finally {
            ConnectionDB.close(resultSet);
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
        return null;
    }

}

package bll;

import java.util.List;
import java.util.NoSuchElementException;
import dao.OrderDAO;
import model.Orders;

public class OrderBll {

    private OrderDAO order;
    public OrderBll(){
        order = new OrderDAO();
    }

    public  List<Orders> SelectQuery(){
        return order.findAll();
    }

    public Orders findByID(int id){
        Orders o = order.findByID(id);
        if(o == null){
            throw new NoSuchElementException("The order with id = " + id + " was not found!");
        }
        return o;
    }

    public void insert(Orders o){
        order.insert(o);
    }

    public void update(Orders o){
        order.update(o);
    }

    public void delete(Orders o){
        order.delete(o);
    }

    public Object[][] getTable(){
        return order.getTable(SelectQuery());
    }

    public Object[] getHeader(){
        return order.getTableHeader();
    }
}

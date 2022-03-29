package controller;
import bll.*;
import model.*;
import view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private View view;
    private ClientBll clientBll;
    private OrderBll orderBll;
    private ProductBll productBll;

    public Controller(View v){
        this.view = v;
        this.clientBll = new ClientBll();
        this.orderBll = new OrderBll();
        this.productBll = new ProductBll();

        view.ClientAddListener(new ClientAddActionListener());
        view.ClientDeleteListener(new ClientDeleteActionListener());
        view.ClientEditListener(new ClientEditActionListener());
        view.ClientViewListener(new ClientViewActionListener());

        view.ProductAddListener(new ProductAddActionListener());
        view.ProductDeleteListener(new ProductDeleteActionListener());
        view.ProductEditListener(new ProductEditActionListener());
        view.ProductViewListener(new ProductViewActionListener());

        view.OrderAddListener(new OrderAddActionListener());
        view.OrderDeleteListener(new OrderDeleteActionListener());
        view.OrderEditListener(new OrderEditActionListener());
        view.OrderViewListener(new OrderViewActionListener());
    }

    public class ClientAddActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int id = Integer.parseInt(view.getIdClientTf());
            String fn = view.getFnClientTf();
            String ln = view.getLnClientTf();
            String adress = view.getAddressClientTf();
            String phone = view.getPhoneClientTf();

            Client c = new Client(id, ln, fn, adress, phone);
            clientBll.insert(c);
        }
    }

    public class ClientEditActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int id = Integer.parseInt(view.getIdClientTf());
            String fn = view.getFnClientTf();
            String ln = view.getLnClientTf();
            String adress = view.getAddressClientTf();
            String phone = view.getPhoneClientTf();

            Client c = new Client(id, ln, fn, adress, phone);
            clientBll.update(c);
        }
    }

    public class ClientDeleteActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int id = Integer.parseInt(view.getIdClientTf());
            String fn = view.getFnClientTf();
            String ln = view.getLnClientTf();
            String adress = view.getAddressClientTf();
            String phone = view.getPhoneClientTf();

            Client c = new Client(id, ln, fn, adress, phone);
            clientBll.delete(c);
        }
    }

    public class ClientViewActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Object[] objects = clientBll.getHeader();
            Object[][] objects1 = clientBll.getTable();
            view.newFrameForView(objects1, objects);
        }
    }

    public class ProductAddActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int id = Integer.parseInt(view.getIdProductTf());
            String name = view.getNameProductTf();;
            double price = Double.parseDouble(view.getPriceProductTf());
            int quantity = Integer.parseInt(view.getQuantityProductTf());

            Product product = new Product(id, name, price, quantity);
            productBll.insert(product);
        }
    }

    public class ProductDeleteActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int id = Integer.parseInt(view.getIdProductTf());
            String name = view.getNameProductTf();;
            double price = Double.parseDouble(view.getPriceProductTf());
            int quantity = Integer.parseInt(view.getQuantityProductTf());

            Product product = new Product(id, name, price, quantity);
            productBll.delete(product);
        }
    }

    public class ProductEditActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int id = Integer.parseInt(view.getIdProductTf());
            String name = view.getNameProductTf();;
            double price = Double.parseDouble(view.getPriceProductTf());
            int quantity = Integer.parseInt(view.getQuantityProductTf());

            Product product = new Product(id, name, price, quantity);
            productBll.update(product);
        }
    }

    public class ProductViewActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Object[] objects = productBll.getHeader();
            Object[][] objects1 = productBll.getTable();
            view.newFrameForView(objects1, objects);
        }
    }

    public class OrderAddActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int id = Integer.parseInt(view.getIdOrderTf());
            int clientID = Integer.parseInt(view.getIdClientOrderTf());
            int productID = Integer.parseInt(view.getIdProductOrderTf());
            int quantity = Integer.parseInt(view.getQuantityOrderTf());



            Object[][] objects = orderBll.getTable();
            int s = 0;
            int i, j;
            for(i = 0; i<objects.length; i++){
                for(j = 0; j<objects[i].length; j++){
                    if(productID == (int)objects[i][2] && j == 3){
                        s = s + (int)objects[i][j];
                    }
                }
            }
            s += quantity;
            Product product = productBll.findBtID(productID);
            int q = product.getQuantity();
            if(s < q){
                Orders orders = new Orders(id, clientID, productID, quantity);
                orderBll.insert(orders);
            }
            else{
                throw new IllegalArgumentException("Nu se poate introduce! Cantitatea prea mare!");
            }
        }
    }

    public class OrderEditActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(view.getIdOrderTf());
            int clientID = Integer.parseInt(view.getIdClientOrderTf());
            int productID = Integer.parseInt(view.getIdProductOrderTf());
            int quantity = Integer.parseInt(view.getQuantityOrderTf());

            Orders orders = new Orders(id, clientID, productID, quantity);
            orderBll.update(orders);
        }
    }

    public class OrderDeleteActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(view.getIdOrderTf());
            int clientID = Integer.parseInt(view.getIdClientOrderTf());
            int productID = Integer.parseInt(view.getIdProductOrderTf());
            int quantity = Integer.parseInt(view.getQuantityOrderTf());

            Orders orders = new Orders(id, clientID, productID, quantity);
            orderBll.delete(orders);
        }
    }

    public class OrderViewActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Object[] objects = orderBll.getHeader();
            Object[][] objects1 = orderBll.getTable();
            view.newFrameForView(objects1, objects);
        }
    }


}

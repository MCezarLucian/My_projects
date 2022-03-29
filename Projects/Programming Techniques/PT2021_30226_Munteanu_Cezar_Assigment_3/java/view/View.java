package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class View extends JFrame {

    private JPanel mainPanel = new JPanel();
    private JPanel clientPanel = new JPanel();
    private JPanel productPanel = new JPanel();
    private JPanel orderPanel = new JPanel();

    private JButton clientButton = new JButton("Client");
    private JButton productButton = new JButton("Product");
    private JButton orderButton = new JButton("Order");

    private JButton addClientButton = new JButton("Add");
    private JButton deleteClientButton = new JButton("Delete");
    private JButton editClientButton = new JButton("Edit");
    private JButton viewClientButton = new JButton("View");
    private JButton backClientButton = new JButton("Back");

    private TextField idClientTf = new TextField();
    private TextField fnClientTf = new TextField();
    private TextField lmClientTf = new TextField();
    private TextField adressCleintTf = new TextField();
    private TextField phoneClientTf = new TextField();

    private JButton addProductButton = new JButton("Add");;
    private JButton deleteProductButton = new JButton("Delete");
    private JButton editProductButton = new JButton("Edit");
    private JButton viewProductButton = new JButton("View");
    private JButton backProductButton = new JButton("Back");

    private TextField idProductTf = new TextField();
    private TextField nameProductTf = new TextField();
    private TextField priceProductTf = new TextField();
    private TextField quantityProductTf = new TextField();

    private JButton addOrderButton = new JButton("Add");;
    private JButton deleteOrderButton = new JButton("Delete");
    private JButton editOrderButton = new JButton("Edit");
    private JButton viewOrderButton = new JButton("View");
    private JButton backOrderButton = new JButton("Back");

    private TextField idOrderTf = new TextField();
    private TextField idCOrderTf = new TextField();
    private TextField idPOrderTf = new TextField();
    private TextField quantityOrderTf = new TextField();

    public View(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,700,500);
        this.setTitle("Main Menu");

        mainPanel.setBounds(0,0,700,600);
        this.add(mainPanel);
        mainPanel.setLayout(null);

        clientButton.setBounds(40,200, 100, 50);
        mainPanel.add(clientButton);
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                clientPanel.setVisible(true);
            }
        });
        productButton.setBounds(270, 200, 100, 50);
        mainPanel.add(productButton);
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                productPanel.setVisible(true);
            }
        });
        orderButton.setBounds(500, 200, 100, 50);
        mainPanel.add(orderButton);
        this.setVisible(true);
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                orderPanel.setVisible(true);
            }
        });

        clientPanel.setBounds(0, 0 ,700, 450);
        this.add(clientPanel);
        clientPanel.setLayout(new GridLayout(2,0));
        clientPanel.setVisible(false);
        JPanel clientTf = new JPanel();
        clientTf.setLayout(new GridLayout(5, 2));
        clientPanel.add(clientTf);
        clientTf.add(new JLabel(" ID : "));
        clientTf.add(idClientTf);
        clientTf.add(new JLabel(" First Name : "));
        clientTf.add(fnClientTf);
        clientTf.add(new JLabel(" Last Name : "));
        clientTf.add(lmClientTf);
        clientTf.add(new JLabel(" Adress : "));
        clientTf.add(adressCleintTf);
        clientTf.add(new JLabel(" Phone Number : "));
        clientTf.add(phoneClientTf);

        JPanel btnClientTf = new JPanel(new GridLayout(3, 2));
        btnClientTf.add(addClientButton);
        btnClientTf.add(deleteClientButton);
        btnClientTf.add(editClientButton);
        btnClientTf.add(viewClientButton);
        btnClientTf.add(backClientButton);
        backClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientPanel.setVisible(false);
                productPanel.setVisible(false);
                orderPanel.setVisible(false);
                mainPanel.setVisible(true);
            }
        });
        clientPanel.add(btnClientTf);

        productPanel.setBounds(0, 0, 700, 450);
        this.add(productPanel);
        productPanel.setLayout(new GridLayout(2, 0));
        productPanel.setVisible(false);
        JPanel tfProductPanel = new JPanel();
        tfProductPanel.setLayout(new GridLayout(4,2));
        tfProductPanel.add(new JLabel(" ID : "));
        tfProductPanel.add(idProductTf);
        tfProductPanel.add(new JLabel(" Product Name : "));
        tfProductPanel.add(nameProductTf);
        tfProductPanel.add(new JLabel(" Price : "));
        tfProductPanel.add(priceProductTf);
        tfProductPanel.add(new JLabel(" Quantity :"));
        tfProductPanel.add(quantityProductTf);
        productPanel.add(tfProductPanel);

        productPanel.add(tfProductPanel);

        JPanel btnProductPanel = new JPanel(new GridLayout(3,2));
        btnProductPanel.add(addProductButton);
        btnProductPanel.add(deleteProductButton);
        btnProductPanel.add(editProductButton);
        btnProductPanel.add(viewProductButton);
        btnProductPanel.add(backProductButton);
        backProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientPanel.setVisible(false);
                productPanel.setVisible(false);
                orderPanel.setVisible(false);
                mainPanel.setVisible(true);
            }
        });
        productPanel.add(btnProductPanel);

        orderPanel.setBounds(0,0,700,500);
        this.add(orderPanel);
        orderPanel.setLayout(new GridLayout(2,0));
        orderPanel.setVisible(false);
        JPanel tfOrderPanel = new JPanel();
        tfOrderPanel.setLayout(new GridLayout(4,2));
        tfOrderPanel.add(new JLabel("   ID : "));
        tfOrderPanel.add(idOrderTf);
        tfOrderPanel.add(new JLabel("   Client ID : "));
        tfOrderPanel.add(idCOrderTf);
        tfOrderPanel.add(new JLabel("   Product ID : "));
        tfOrderPanel.add(idPOrderTf);
        tfOrderPanel.add(new JLabel("   Quantity : "));
        tfOrderPanel.add(quantityOrderTf);
        orderPanel.add(tfOrderPanel);

        JPanel btnOrderPanel = new JPanel(new GridLayout(3, 2));
        btnOrderPanel.add(addOrderButton);
        btnOrderPanel.add(deleteOrderButton);
        btnOrderPanel.add(editOrderButton);
        btnOrderPanel.add(viewOrderButton);
        btnOrderPanel.add(backOrderButton);
        backOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientPanel.setVisible(false);
                productPanel.setVisible(false);
                orderPanel.setVisible(false);
                mainPanel.setVisible(true);
            }
        });
        orderPanel.add(btnOrderPanel);

    }

    public void newFrameForView(Object[][] obj, Object[] col){
        JFrame viewFrame = new JFrame();
        viewFrame.setTitle("View");
        JTable table = new JTable(obj, col);
        JScrollPane scrollPane = new JScrollPane(table);
        viewFrame.add(scrollPane);

        viewFrame.setBounds(0,0,700,500);
        viewFrame.setVisible(true);
    }

    public String getIdClientTf(){return idClientTf.getText();}
    public String getFnClientTf(){return fnClientTf.getText();}
    public String getLnClientTf(){return lmClientTf.getText();}
    public String getAddressClientTf(){return adressCleintTf.getText();}
    public String getPhoneClientTf(){return phoneClientTf.getText();}
    public String getIdProductTf(){return idProductTf.getText();}
    public String getNameProductTf(){return nameProductTf.getText();}
    public String getPriceProductTf(){return priceProductTf.getText();}
    public String getQuantityProductTf(){return quantityProductTf.getText();}
    public String getIdOrderTf(){return idOrderTf.getText();}
    public String getIdClientOrderTf(){return idCOrderTf.getText();}
    public String getIdProductOrderTf(){return idPOrderTf.getText();}
    public String getQuantityOrderTf(){return quantityOrderTf.getText();}
    public void ClientAddListener(ActionListener c){this.addClientButton.addActionListener(c);}
    public void ClientDeleteListener(ActionListener c){this.deleteClientButton.addActionListener(c);}
    public void ClientEditListener(ActionListener c){this.editClientButton.addActionListener(c);}
    public void ClientViewListener(ActionListener c){this.viewClientButton.addActionListener(c);}
    public void ProductAddListener(ActionListener c){this.addProductButton.addActionListener(c);}
    public void ProductDeleteListener(ActionListener c){this.deleteProductButton.addActionListener(c);}
    public void ProductEditListener(ActionListener c){this.editProductButton.addActionListener(c);}
    public void ProductViewListener(ActionListener c){this.viewProductButton.addActionListener(c);}
    public void OrderAddListener(ActionListener c){this.addOrderButton.addActionListener(c);}
    public void OrderDeleteListener(ActionListener c){this.deleteOrderButton.addActionListener(c);}
    public void OrderEditListener(ActionListener c){this.editOrderButton.addActionListener(c);}
    public void OrderViewListener(ActionListener c){this.viewOrderButton.addActionListener(c);}

}

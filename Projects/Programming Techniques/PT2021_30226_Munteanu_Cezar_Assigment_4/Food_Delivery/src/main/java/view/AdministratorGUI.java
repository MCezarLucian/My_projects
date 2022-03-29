package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AdministratorGUI extends JFrame {

    private JPanel adminPanel = new JPanel();
    private JButton importCSV = new JButton("Import Products");
    private JButton addProduct = new JButton("Add Product");
    private JButton removeProduct = new JButton("Remove Product");
    private JButton editProduct = new JButton("Edit Product");
    private JButton createProduct = new JButton("Create Product");
    private JButton report = new JButton("Make a report");
    private JButton view = new JButton(("View"));

    private JTextField name = new JTextField();
    private JTextField rating =new JTextField();
    private JTextField calories = new JTextField();
    private JTextField protein = new JTextField();
    private JTextField fat = new JTextField();
    private JTextField sodium = new JTextField();
    private JTextField price = new JTextField();
    private JTextField newName = new JTextField();

    private JLabel nm = new JLabel("Name:");
    private JLabel rt = new JLabel("Rating:");
    private JLabel cal = new JLabel("Calories:");
    private JLabel pr = new JLabel("Proteins:");
    private JLabel ft= new JLabel("Fat:");
    private JLabel sd = new JLabel("Sodium:");
    private JLabel ps = new JLabel("Price:");
    private JLabel nn = new JLabel("New name:");
    private JTextArea menu = new JTextArea();
    private JScrollPane sp = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);



    public AdministratorGUI(){
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,800,800);
        this.setTitle("Administrator Panel");

        adminPanel.setBounds(0,0,800,800);
        this.add(adminPanel);
        adminPanel.setLayout(null);
        importCSV.setBounds(20,20,150,25);
        addProduct.setBounds(20,55,150,25);
        removeProduct.setBounds(20,90,150,25);
        editProduct.setBounds(20,125,150,25);
        createProduct.setBounds(20,160,150,25);
        report.setBounds(20,195,150,25);
        view.setBounds(20,230,150,25);
        adminPanel.add(importCSV);
        adminPanel.add(addProduct);
        adminPanel.add(removeProduct);
        adminPanel.add(editProduct);
        adminPanel.add(createProduct);
        adminPanel.add(report);
        adminPanel.add(view);

        nm.setBounds(250, 20, 50, 25);
        name.setBounds(250,55,200,25);
        cal.setBounds(250, 90, 50, 25);
        calories.setBounds(250,125,200,25);
        ft.setBounds(250,160,50,25);
        fat.setBounds(250,195,200,25);
        ps.setBounds(250,230,50,25);
        price.setBounds(250,265,200,25);
        rt.setBounds(500,20,50,25);
        rating.setBounds(500,55,200,25);
        pr.setBounds(500, 90,60,25);
        protein.setBounds(500,125,200,25);
        sd.setBounds(500,160,50,25);
        sodium.setBounds(500,195,200,25);
        nn.setBounds(500,230,70,25);
        newName.setBounds(500,265,200,25);
        menu.setBounds(20, 300, 740, 400);

        adminPanel.add(nm);
        adminPanel.add(rt);
        adminPanel.add(rating);
        adminPanel.add(name);
        adminPanel.add(cal);
        adminPanel.add(calories);
        adminPanel.add(pr);
        adminPanel.add(protein);
        adminPanel.add(ft);
        adminPanel.add(fat);
        adminPanel.add(sd);
        adminPanel.add(sodium);
        adminPanel.add(ps);
        adminPanel.add(price);
        adminPanel.add(nn);
        adminPanel.add(newName);
        adminPanel.add(menu);
        menu.add(sp);
        //menu.setLineWrap(true);

        adminPanel.setVisible(true);
        this.setVisible(true);
    }

    public String getName(){return name.getText();}
    public String getRating(){return rating.getText();}
    public String getCalories(){return calories.getText();}
    public String getProteins(){return protein.getText();}
    public String getFat(){return fat.getText();}
    public String getSodium(){return sodium.getText();}
    public String getPrice(){return price.getText();}
    public String getNewName(){return newName.getText();}

    public void ImportListener(ActionListener c){this.importCSV.addActionListener(c);}
    public void AddListener(ActionListener c){this.addProduct.addActionListener(c);}
    public void RemoveListener(ActionListener c){this.removeProduct.addActionListener(c);}
    public void EditListener(ActionListener c){this.editProduct.addActionListener(c);}
    public void CreateListener(ActionListener c){this.createProduct.addActionListener(c);}
    public void ReportListener(ActionListener c){this.report.addActionListener(c);}
    public void ViewListener(ActionListener c){this.view.addActionListener(c);}
    public void MenuSet(String s){menu.setText(s);}



}

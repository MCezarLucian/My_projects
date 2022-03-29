package bll;

import java.util.List;
import java.util.NoSuchElementException;
import dao.ProductDAO;
import model.Product;

public class ProductBll {

    private ProductDAO product;

    public ProductBll(){
        product = new ProductDAO();
    }

    public List<Product> SelectQuery(){
        return product.findAll();
    }

    public  Product findBtID(int id){
        Product p = product.findByID(id);
        if(p == null){
            throw new NoSuchElementException("The product with id = " + id + " was not found!");
        }
        return p;
    }

    public void insert(Product p){
        product.insert(p);
    }

    public void update(Product p){
        product.update(p);
    }

    public void delete(Product p){
        product.delete(p);
    }

    public Object[][] getTable(){
        return product.getTable(SelectQuery());
    }

    public Object[] getHeader(){
        return product.getTableHeader();
    }
}

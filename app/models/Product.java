package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity

public class Product extends Model{
        @Id
        private Long id;
        @Constraints.Required//save to database via ebeanRequired
        private String name;
        
        @Constraints.Required
        private String description;
        
        @Constraints.Required
        private int stock;
        
        @Constraints.Required
        private double price;
        
        public Product(){
        }
        
        public Product(Long id, String name, String description, int stock, double price){
                this.id = id;
                this.name = name;
                this.description = description;
                this.stock = stock;
                this.price = price;
        }
        
        public Long getId() {
        return id;
        }

        public String getName() {
                return name;
        }

        public String getDescription() {
                return description;
        }

        public int getStock() {
                return stock;
        }

        public double getPrice() {
                return price;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public void setStock(int stock) {
                this.stock = stock;
        }

        public void setPrice(double price) {
                this.price = price;
        }
        
        public static Finder<Long, Product> find = new Finder<Long, Product>(Product.class);
        
        public static List<Product> findAll() {
            return Product.find.all();
        }
}

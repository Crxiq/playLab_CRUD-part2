package controllers;

import play.api.Environment;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.*;
import models.*;

public class HomeController extends Controller {
    //Declare a private FormFactory instance
    private FormFactory formFactory;

    //Inject an instance of FormFactory it into the controller via its construtor
    @Inject
    public HomeController(FormFactory f){
        this.formFactory = f;
    }

    public Result index(String name) {
        return ok(index.render("Welcome to the Home page", name));
    }

    public Result about() {
        return ok(about.render());
    }

    public Result products() {
    
        List<Product> productsList = Product.findAll();
        
        return ok(products.render(productsList));
    }

    //Render and return new the add new product page with empty form
    public Result addProduct() {
        //Create a form by wrapping the Product class in a formfactory form instance
        Form<Product> addProductForm = formFactory.form(Product.class);
        //Render the Add Product View, passing the form object
        return ok(addProduct.render(addProductForm));
    }

    public Result addProductSubmit() {

        //create a product form object (to hold submitted data)
        //'bind' the object to submitted form (this copies the filled form)
        Form<Product> newProductForm = formFactory.form(Product.class).bindFromRequest();

        //Check for errors based on Product class
        if(newProductForm.hasErrors()) {
            //display form again
            return badRequest(addProduct.render(newProductForm));
        }
        //
        Product newProduct = newProductForm.get();
        //save to database via ebean
        newProduct.save();
        //
        flash("success", "Product " + newProduct.getName() + " has been created");
        //redirect to home
        return redirect(controllers.routes.HomeController.products());
    }

}



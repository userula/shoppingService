package kz.edu.astanait.proxy;

import kz.edu.astanait.classes.Product;

import java.util.ArrayList;
import java.util.LinkedList;

public class RealProductAccess implements ProductAccess{
    private ArrayList<Product> items;

    public RealProductAccess(ArrayList<Product> cart) {
        items = cart;
    }

    @Override
    public String productCounter() {
        return "";
    }

    public ArrayList<Product> getItems() {
        return items;
    }
}

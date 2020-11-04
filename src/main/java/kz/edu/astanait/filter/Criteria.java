package kz.edu.astanait.filter;

import kz.edu.astanait.classes.Product;

import java.util.LinkedList;

public interface Criteria {
    public LinkedList<Product> getList(LinkedList<Product> p);
}

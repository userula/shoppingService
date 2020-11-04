package kz.edu.astanait.filter;

import kz.edu.astanait.classes.Product;

import java.util.LinkedList;

public class CriteriaSport implements Criteria {
    @Override
    public LinkedList<Product> getList(LinkedList<Product> p) {
        LinkedList<Product> newP = new LinkedList<>();
        for (Product pp : p)
        {
            if(pp.getCategory().equals("sport"))
            {
                newP.add(pp);
            }
        }
        return newP;
    }
}

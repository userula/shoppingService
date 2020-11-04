package kz.edu.astanait.proxy;

import kz.edu.astanait.classes.Product;
import kz.edu.astanait.mediator.Messages;

import java.util.ArrayList;
import java.util.LinkedList;

public class ProxyProductAccess extends RealProductAccess implements ProductAccess {
    private RealProductAccess rpa;

    public ProxyProductAccess(ArrayList<Product> cart)
    {
        super(cart);
    }

    @Override
    public String productCounter() {
        if(getItems().size() < 5)
        {
            rpa = new RealProductAccess(getItems());
            return rpa.productCounter();
        }
        return Messages.showMsg();
    }
}

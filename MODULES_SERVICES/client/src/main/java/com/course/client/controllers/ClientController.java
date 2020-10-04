package com.course.client.controllers;

import com.course.client.beans.*;
import com.course.client.proxies.MsCartProxy;
import com.course.client.proxies.MsMyOrderProxy;
import com.course.client.proxies.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private MsProductProxy msProductProxy;

    @Autowired
    private MsCartProxy msCartProxy;

    @Autowired
    private MsMyOrderProxy msMyOrderProxy;

    @RequestMapping("/")
    public String index(Model model) {

        List<ProductBean> products =  msProductProxy.list();

        model.addAttribute("products", products);

        return "index";
    }

    // détails du produit
    @CrossOrigin
    @RequestMapping("/product-detail/{id}")
    public String detail(@PathVariable Long id, Model model) {

        Optional<ProductBean> product = msProductProxy.get(id);

        model.addAttribute("product", product);

        return "product-detail";
    }


    @CrossOrigin
    @PostMapping(value = "/neworder")
    public void addNewOrder () throws IOException {

        Long id = Long.valueOf(1);
        Optional<CartBean> cartBean = msCartProxy.getCart(id);

       List<CartItemBean>  cartItemBean= cartBean.get().getProducts();


   for (int i=0; i<cartItemBean.size(); i++){

           MyOrderItemBean myOrderItemBean = new MyOrderItemBean();

           myOrderItemBean.setId(cartItemBean.get(i).getId());
           myOrderItemBean.setQuantity(cartItemBean.get(i).getQuantity());
           myOrderItemBean.setProductId(cartItemBean.get(i).getProductId());

          msMyOrderProxy.addProductToMyOrder(Long.valueOf(1),myOrderItemBean);
     }
        String s= "";
    }

    @RequestMapping("/myorders")
    public String listOrders(Model model) {

        List<MyOrderBean> orders = msMyOrderProxy.list();

        model.addAttribute("orders", orders);

        return "orders";
    }

    // détails de la commande
    @RequestMapping("/order-detail/{id}")
    public String detailOrder(@PathVariable Long id, Model model) {

        Optional<MyOrderBean> order = msMyOrderProxy.getMyOrder(id);

        model.addAttribute("order", order);

        return "order-detail";
    }

    @RequestMapping("/show-cart")
    public String cart(Model model) {

        //Optional<CartBean> cart = msCartProxy.getCart(Long.valueOf(1));


        Double total=Double.valueOf(0);
        List<Optional<ProductBean>> productBeans = new ArrayList<>();
        Optional<CartBean> cartBean = msCartProxy.getCart(Long.valueOf(1));
        List<CartItemBean>  cartItemBeans= cartBean.get().getProducts();
        String s = "";

        for(int i=0;i<cartItemBeans.size();i++)
        {

          productBeans.add(msProductProxy.get(cartItemBeans.get(i).getProductId()));
          total=total+(productBeans.get(i).get().getPrice());
        }


        model.addAttribute("cartBean", cartBean);
        model.addAttribute("cartItemBeans", cartItemBeans);
        model.addAttribute("productBeans", productBeans);
        model.addAttribute("total", total);

        return "show-cart";
    }
}

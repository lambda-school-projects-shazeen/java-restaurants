package com.lambdaschool.restaurants;

import com.lambdaschool.restaurants.models.Menu;
import com.lambdaschool.restaurants.models.Payment;
import com.lambdaschool.restaurants.models.Restaurant;
import com.lambdaschool.restaurants.models.RestaurantPayments;
import com.lambdaschool.restaurants.repos.PaymentRepository;
import com.lambdaschool.restaurants.repos.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    private PaymentRepository payrepos;

    private RestaurantRepository restaurantrepos;

    public SeedData(
        PaymentRepository payrepos,
        RestaurantRepository restaurantrepos)
    {
        this.payrepos = payrepos;
        this.restaurantrepos = restaurantrepos;
    }

    @Override
    public void run(String[] args) throws
                                   Exception
    {
        Payment payType1 = new Payment("Credit Card");
        Payment payType2 = new Payment("Cash");
        Payment payType3 = new Payment("Bitcoins");

        payrepos.save(payType1);
        payrepos.save(payType2);
        payrepos.save(payType3);

        // Restaurant String name, String address, String city, String state, String telephone
        String rest1Name = "Test Apple";
        Restaurant r1 = new Restaurant(rest1Name,
            "123 Main Street",
            "City",
            "ST",
            "555-555-1234");
        r1.getPayments()
            .add(new RestaurantPayments(r1,
                payType1));
        r1.getPayments()
            .add(new RestaurantPayments(r1,
                payType2));
        r1.getPayments()
            .add(new RestaurantPayments(r1,
                payType3));
        r1.getMenus()
            .add(new Menu("Mac and Cheese",
                6.95,
                r1));
        r1.getMenus()
            .add(new Menu("Lasagna",
                8.50,
                r1));
        r1.getMenus()
            .add(new Menu("Meatloaf",
                7.77,
                r1));
        r1.getMenus()
            .add(new Menu("Tacos",
                8.49,
                r1));
        r1.getMenus()
            .add(new Menu("Chef Salad",
                12.50,
                r1));

        restaurantrepos.save(r1);

        String rest2Name = "Test Eagle Cafe";
        Restaurant r2 = new Restaurant(rest2Name,
            "321 Uptown Drive",
            "Town",
            "ST",
            "555-555-5555");
        r2.getPayments()
            .add(new RestaurantPayments(r2,
                payType2));
        r2.getMenus()
            .add(new Menu("Tacos",
                10.49,
                r2));
        r2.getMenus()
            .add(new Menu("Barbacoa",
                12.75,
                r2));

        restaurantrepos.save(r2);

        String rest3Name = "Test Number 1 Eats";
        Restaurant r3 = new Restaurant(rest3Name,
            "565 Side Avenue",
            "Village",
            "ST",
            "555-123-1555");
        r3.getPayments()
            .add(new RestaurantPayments(r3,
                payType1));
        r3.getPayments()
            .add(new RestaurantPayments(r3,
                payType3));
        r3.getMenus()
            .add(new Menu("Pizza",
                15.15,
                r3));

        restaurantrepos.save(r3);
    }
}

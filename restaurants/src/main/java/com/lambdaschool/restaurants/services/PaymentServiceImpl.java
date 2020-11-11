package com.lambdaschool.restaurants.services;

import com.lambdaschool.restaurants.models.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService
{
    @Override
    public List<Payment> findAll()
    {
        return null;
    }

    @Override
    public Payment findPaymentById(long id)
    {
        return null;
    }

    @Override
    public Payment save(Payment payment)
    {
        return null;
    }

    @Override
    public Payment findByName(String name)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {

    }

    @Override
    public Payment update(
        long id,
        Payment payment)
    {
        return null;
    }
}

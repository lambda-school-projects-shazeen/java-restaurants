package com.lambdaschool.restaurants.services;

import com.lambdaschool.restaurants.RestaurantsApplicationTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantsApplicationTests.class,
properties = {"command.line.runner.enabled=false"})
public class RestaurantServiceImplUnitTestNoDB
{
    //mocks -> create fake data
    //stubs -> create fake method

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findRestaurantByNameLike()
    {
    }

    @Test
    public void findNameCity()
    {
    }

    @Test
    public void findAll()
    {
    }

    @Test
    public void findRestaurantById()
    {
    }

    @Test
    public void findRestaurantByName()
    {
    }

    @Test
    public void delete()
    {
    }

    @Test
    public void save()
    {
    }

    @Test
    public void update()
    {
    }
}
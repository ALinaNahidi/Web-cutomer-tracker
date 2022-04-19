package com.springdemo.dao;

import com.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {


        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //create a query
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName ", Customer.class);

        //execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        //return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theCustomerId) {

        Session session = sessionFactory.getCurrentSession();

        Customer theCustomer = session.get(Customer.class,theCustomerId);

        return theCustomer;


    }

    @Override
    public void deleteCustomer(int theId) {
        Session session = sessionFactory.getCurrentSession();

        Customer customer = session.get(Customer.class,theId);

        session.delete(customer);

    }
}

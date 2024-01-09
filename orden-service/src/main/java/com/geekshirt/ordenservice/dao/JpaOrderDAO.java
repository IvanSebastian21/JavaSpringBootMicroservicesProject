package com.geekshirt.ordenservice.dao;

import com.geekshirt.ordenservice.entities.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrderDAO implements OrderDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("select o from Order o", Order.class).getResultList();
    }

    @Override
    public Optional<Order> findByOrderId(String orderId) {
        String jpqlQuery = "SELECT o FROM Order o WHERE o.orderId = :orderId";
        TypedQuery<Order> orderTypedQuery = entityManager.createQuery(jpqlQuery, Order.class)
                .setParameter("orderId", orderId);
        return Optional.ofNullable(orderTypedQuery.getSingleResult());
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public Order save(Order order) {
        entityManager.persist(order);
        return order;
    }
}

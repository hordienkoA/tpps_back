package ua.cn.stu.tpps.buyfly.services;

import ua.cn.stu.tpps.buyfly.exceptions.ServiceException;

import java.util.Collection;

/**
 * Contains similar actions like save, remove, getById, getAll ...
 */
public interface GenericService<T> {
    Integer save(T entity) throws ServiceException;

    void remove(Integer id) throws ServiceException;

    Collection<T> getAll() throws ServiceException;

    T getById(Integer id) throws ServiceException;
}

package com.switchfully.domain.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepositoryD extends CrudRepository<Item, Long> {

    @Override
    public List<Item> findAll();

    public Item findByName(String name);
}

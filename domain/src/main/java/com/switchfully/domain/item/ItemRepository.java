package com.switchfully.domain.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    @Override
    public List<Item> findAll();
}

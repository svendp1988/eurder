package com.switchfully.domain.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Map;

import static com.switchfully.domain.testbuilders.TestItemBuilder.testItemBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan(basePackages = "com.switchfully.domain")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    void repoIsThere() {
        assertThat(itemRepository).isNotNull();
    }

    @Test
    void createItem() {
        Item expected = testItemBuilder().build();
        itemRepository.save(expected);
        Item actual = itemRepository.findById(1L).get();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findAll() {
        List<Item> items = List.of(testItemBuilder().build(), testItemBuilder().withName("anotherItem").build());
        itemRepository.saveAll(items);
        List<Item> actual = itemRepository.findAll();
        assertThat(actual).hasSameElementsAs(items);
    }

}
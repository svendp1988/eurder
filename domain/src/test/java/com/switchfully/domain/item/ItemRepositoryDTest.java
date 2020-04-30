package com.switchfully.domain.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.switchfully.domain.testbuilders.TestItemBuilder.testItemBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DataJpaTest
@AutoConfigureTestDatabase
@ComponentScan(basePackages = "com.switchfully")
class ItemRepositoryDTest {

    @Autowired
    private ItemRepositoryD itemRepositoryD;

    @Test
    void weExist() {
        assertThat(itemRepositoryD).isNotNull();
    }

    @Test
    void createItem() {
        Item item = testItemBuilder().build();

        itemRepositoryD.save(item);

        Item actual = itemRepositoryD.findById(1L).get();

        assertThat(actual).isEqualTo(item);
    }

    @Test
    @Sql("createItems.sql")
    void getAllItems() {
        List<Item> actual = itemRepositoryD.findAll();
        Item actualItem = itemRepositoryD.findById(3L).get();
        assertThat(actual).hasSize(5);
        assertThat(actual).contains(actualItem);
    }

    @Test
    void findByName() {
        Item expected = itemRepositoryD.save(testItemBuilder().withName("name").build());
        Item actual = itemRepositoryD.findByName("name");
        assertThat(actual).isEqualTo(expected);
    }
}
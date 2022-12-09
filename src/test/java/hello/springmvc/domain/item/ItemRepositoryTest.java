package hello.springmvc.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    public void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll(){
        //given

        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        //when
        List<Item> findAllItem = itemRepository.findAll();

        //then
        assertThat(findAllItem.size()).isEqualTo(2);
        assertThat(findAllItem).contains(itemA, itemB);
    }

    @Test
    void updateItem(){
        //given
//        Item itemA = new Item("itemA", 10000, 10);
//        Item itemAId = itemRepository.findById(itemA.getId());
//
//        //when
//        itemRepository.update(itemAId, );

        //then
    }
}

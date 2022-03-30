package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; // alt + enter해서 스테틱으로 만듦

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach // 매 테스트 끝날 때마다 초기화해줘야 다음 테스트에 영향x
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("TestItemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll(){
        //given
        Item item1 = new Item("TestItem1", 10000, 10);
        Item item2 = new Item("TestItem2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem(){
        //given
        Item item = new Item("TestItem", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();
        //when

        Item updateParams = new Item("item2", 20000, 20);
        itemRepository.update(itemId, updateParams);

        //then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(savedItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(savedItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(savedItem.getQuantity());
    }
}

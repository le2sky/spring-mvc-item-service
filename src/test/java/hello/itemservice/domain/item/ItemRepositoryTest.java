package hello.itemservice.domain.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item saved = itemRepository.save(item);

        // then
        Item find = itemRepository.findById(item.getId());
        assertThat(find).isEqualTo(saved);
    }

    @Test
    void findAll() {
        // given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("item", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        // then
        Item find = itemRepository.findById(itemId);
        assertThat(find.getItemName()).isEqualTo("item2");
        assertThat(find.getPrice()).isEqualTo(20000);
        assertThat(find.getQuantity()).isEqualTo(30);
    }
}

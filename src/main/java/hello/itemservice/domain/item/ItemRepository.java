package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository { //ctrl + shift + t 하면 자동으로 테스트 생성

    private static final Map<Long, Item> store = new HashMap<>(); // 실무에서는 해쉬맵 쓰면 안된다.. 멀티쓰레드 환경이기 때문에 꼬일 수 있음
                                                                    // 실무에서는 concurrentHashmap?
    private static Long sequence = 0L; // 이것도 실무에서는 오토믹 롱 같은 거로 써줘야함

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) { // 실무에서는 update용 DTO(ex.ItemParamDTO)를 만들어야함. 중복 제거보단 명확성!
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();

    }
}

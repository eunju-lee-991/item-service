package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final이 붙은 객체를 의존성 주입하는 생성자를 만들어준다
public class BasicItemController {

    private final ItemRepository itemRepository;

//    //생성자 하나만 있으면 @autowired 생략 가능 / @RequiredArgsConstructor 쓰면 생성자도 생략 가능
//   public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("ItemA", 1000, 10));
        itemRepository.save(new Item("ItemB", 2000, 20));
    }
}

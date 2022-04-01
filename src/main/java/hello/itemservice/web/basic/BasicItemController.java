package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
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

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        log.debug("머더라", item.getItemName());
        System.out.println("moyo");
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String save(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
//        model.addAttribute("item", item);
//        ModelAttribute("") 괄호 안의 이름이 addAttribute할 때의 name이 됨. ("name") 생략하면 객체의 클래스이름의 첫 글자를 name으로 넣어줌
        return "basic/item";
    }

    //@PostMapping("/add")
    public String save2(Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String save3(Item item) {
        itemRepository.save(item);

        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String save4(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", "광광");
        // 경로의 {itemId}는 redirectAttributes.addAttribute("itemId"... 이 값
        // 나머지 attribute(ex.status)는 쿼리파라미터 형식으로 들어감
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct //해당 빈의 의존관계가 모두 주입되고 나면 초기화 용도로 호출된다.
    public void init(){
        itemRepository.save(new Item("ItemA", 1500, 10));
        itemRepository.save(new Item("ItemB", 2500, 20));
    }
}

package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter // lombok의 Data는 실제 상황에서 위험하다 게터 세터 정도만 사용
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // int 대신 Integer를 쓰는 이유! 값이 null으로 들어갈 상황도 고려해서
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    // alt+insert!! 생성자나 게터 세터
}

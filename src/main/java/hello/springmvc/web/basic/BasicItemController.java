package hello.springmvc.web.basic;

import hello.springmvc.domain.item.Item;
import hello.springmvc.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String saveV1(@RequestParam String itemName,
                        @RequestParam int price,
                       @RequestParam Integer quantity,
                        Model model)
    {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
//        model.addAttribute("item", item);

        return "basic/item";
    }


//    @PostMapping("/add")
    public String saveV3(Item item, Model model) {
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveV4(@ModelAttribute Item item) {
        itemRepository.save(item);
//        model.addAttribute("item", item);

        return "basic/item";
    }

    @PostMapping("/add")
    public String saveV5(Item item, RedirectAttributes redirectAttributes) {

        Item savedItem = itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }
    /**
     * 테스트용 데이터 추가
     */

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, Item updateItem){

        itemRepository.update(itemId, updateItem);

        return "redirect:/basic/items/{itemId}"; // http://localhost:8080/basic/items/1
//        return "basic/item"; // http://localhost:8080/basic/items/1/edit
    }

    @GetMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId){
        itemRepository.delete(itemId);
        return "redirect:/basic/items";
    }


    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB ", 20000, 20));
    }
}

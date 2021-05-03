import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.debugger.Page;

import javax.servlet.Filter;

public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String showAllItemsPage(
            Model model,
            @RequestParam(name = "p", defaultValue = "1") int pageIndex,
            @RequestParam(name = "min", defaultValue = "0") int minPrice,
            @RequestParam(name = "max", defaultValue = "0") int maxPrice,
            @RequestParam(name = "filter", required = false) String filtersubstr
    ) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }

        Filter userFilter = itemRepository.userFilter(minPrice, maxPrice, filtersubstr);
        Page<Item> page = itemRepository.findAll(pageIndex - 1, 4, userFilter);
        model.addAttribute("page", page);
        model.addAttribute("filters", userFilter);
        return "index";

    }


    @GetMapping("/items/{id}/price/inc")
    public String incrementItemPrice(@PathVariable Long id) {
        itemRepository.incrementPriceById(id, 10);
        return "redirect:/";
    }

    @GetMapping("/items/{id}")
    public String showItemInfo(@PathVariable(name = "id") Long id, Model model) {
        itemRepository.findOneById(id).ifPresent(p -> model.addAttribute("item", p));
        return "item_info";
    }

    @GetMapping("/items/add")
    public String showCreateItemForm() {
        return "create_item_form";
    }

    @PostMapping("/items/add")
    public String createNewItem(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/";
    }

    @GetMapping("/items/delete/{id}")
    public String deleteItemById(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "redirect:/";
    }
}

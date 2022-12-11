# springmvc

# 2022-12-12
> - ```th:action="@{/basic/items/{itemId}/edit(itemId = ${item.id})}" // == th:action() because : controller @PostMapping("/{itemId}/edit")```
> - @RequestParam String itemName, @RequestParam int price // == @ModelAttribute Item item == Item item 

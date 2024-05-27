package br.lab.caiofernadomf.DynamoDbOperacoes;

import br.lab.caiofernadomf.DynamoDbOperacoes.Service.ItemService;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService=itemService;
    }

    @GetMapping
    public List<Map> getItens(){
        return itemService.findAllItems();
    }

    @GetMapping("/name/{name}")
    public Map getByName(@PathVariable String name){
        return itemService.findItemByName(name);
    }

    @GetMapping("/ip/{ip}")
    public Map getByIp(@PathVariable String ip){
        return itemService.findItemByIp(ip);
    }

    @PostMapping
    public Item createItem(@RequestBody Map<String,Object> mapItem, HttpServletRequest httpServletRequest ){
        Map<String,Object> mapItemPk = new HashMap<>();
        httpServletRequest.getParameterMap().forEach((s, strings) -> mapItemPk.put(s,strings[0]));
        return  itemService.saveItem(mapItem,mapItemPk);

    }

    @DeleteMapping("/name/{name}")
    public void deleteByName(@PathVariable  String name){
        itemService.deleteByName(name);

    }
}

package br.lab.caiofernadomf.DynamoDbOperacoes.Service;

import br.lab.caiofernadomf.DynamoDbOperacoes.repository.DynamoRepository;
import com.amazonaws.services.dynamodbv2.document.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemService {

    private final DynamoRepository dynamoRepository;

    public ItemService(DynamoRepository dynamoRepository) {
        this.dynamoRepository = dynamoRepository;
        String tableName = "hosts";
        this.dynamoRepository.setTableName(tableName);
        createTableIfNotExist();
    }

    private void createTableIfNotExist(){
        if(!dynamoRepository.isTableExists()){
            dynamoRepository.createTable();
        }
    }

    public Item saveItem(Map<String,Object> mapItens
            , Map<String,Object> itemPk){

        return dynamoRepository.saveItem(mapItens,itemPk);
    }

    public Map findItemByIp(String ip){



        return dynamoRepository.findByIp(ip);

    }

    public Map findItemByName(String name){



        return dynamoRepository.findByName(name);

    }

    public List<Map> findAllItems(){

        return dynamoRepository.findAll();
    }

    public Map deleteByName(String name){
        return dynamoRepository.deleteByName(name);
    }
}

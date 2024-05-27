package br.lab.caiofernadomf.DynamoDbOperacoes.repository;

import br.lab.caiofernadomf.DynamoDbOperacoes.repository.operations.*;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DynamoRepository {

    private final AmazonDynamoDB amazonDynamoDB;

    private String tableName;

    public DynamoRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }
    public void setTableName(String tableName){
        this.tableName=tableName;
    }

    public List<Map> findAll(){
        return ReadTable.scanTable(amazonDynamoDB,tableName);
    }

    public Map findByIp(String ip){
        return find("ip",ip);
    }

    public Map findByName(String name){
        return find("name",name);
    }

    private Map find(String keyName,String keyValue){
        return GetItem.getItem(amazonDynamoDB,tableName,keyName,keyValue);
    }

    public Map deleteByName(String name){
        return DeleteItem.deleteItem(amazonDynamoDB,tableName,"name",name);
    }

    public void createTable(){
        CreateTable.createTable(tableName,amazonDynamoDB);
    }

    public boolean isTableExists(){
        return CheckTable.checkTableExists(tableName,amazonDynamoDB);
    }

    public Item saveItem(Map<String,Object> mapItens
            , Map<String,Object> itemPk){
        return  CreateItem.createItem(amazonDynamoDB,tableName,mapItens
                , itemPk);
    }

}

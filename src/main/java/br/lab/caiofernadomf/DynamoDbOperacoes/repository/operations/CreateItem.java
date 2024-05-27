package br.lab.caiofernadomf.DynamoDbOperacoes.repository.operations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.util.Map;

public class CreateItem {

    public static Item createItem(
            AmazonDynamoDB amazonDynamoDB
            , String tableName
            , Map<String,Object> mapItens
            , Map<String,Object> itemPk) {
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        Table table = dynamoDB.getTable(tableName);

        Item item = Item.fromMap(mapItens);
        itemPk.forEach(item::withPrimaryKey);

        PutItemOutcome putItemOutcome= table.putItem(item);

        return putItemOutcome.getItem();

    }
}

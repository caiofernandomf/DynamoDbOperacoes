package br.lab.caiofernadomf.DynamoDbOperacoes.repository.operations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.internal.IteratorSupport;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;

import java.util.HashMap;
import java.util.Map;

public class GetItem {

    public static Map getItem(
            AmazonDynamoDB amazonDynamoDB,
            String tableName,
            String keyName,
            String keyValue){
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        Table table = dynamoDB.getTable(tableName);

        Map<String, Object> expressionAttributeValues =
                new HashMap<String, Object>();

        expressionAttributeValues.put(":val", (keyValue));

        Map<String, String> expressionAttributeNames =
                new HashMap<String, String>();

        expressionAttributeNames.put("#attr", (keyName));

        ItemCollection<ScanOutcome> items =  table.
                scan("#attr = :val",expressionAttributeNames,expressionAttributeValues);

        Map result = new HashMap();

        IteratorSupport<Item, ScanOutcome> iterator = items.iterator();

        while (iterator.hasNext()){
            result = iterator.next().asMap();
        }

        return result;
    }
}

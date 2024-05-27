package br.lab.caiofernadomf.DynamoDbOperacoes.repository.operations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.internal.IteratorSupport;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadTable {


    public static List<Map> scanTable(AmazonDynamoDB amazonDynamoDB, String tableName){
        /*ScanRequest scanRequest = new ScanRequest()
                .withTableName(tableName);

        ScanResult result = amazonDynamoDB.scan(scanRequest);*/

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        Table table = dynamoDB.getTable(tableName);

        ScanFilter scanFilter = new ScanFilter("ip").exists();

        IteratorSupport<Item, ScanOutcome> iterator=   table.scan(scanFilter).iterator();
        List<Map> resultList = new ArrayList<>();

        while (iterator.hasNext()){
            resultList.add(iterator.next().asMap());
        }

        return resultList;
    }

}

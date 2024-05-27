package br.lab.caiofernadomf.DynamoDbOperacoes.repository.operations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.internal.IteratorSupport;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.xspec.ScanExpressionSpec;

import java.util.*;
import java.util.stream.Collectors;

public class ReadTable {


    public static List<Map> scanTable(AmazonDynamoDB amazonDynamoDB, String tableName){
        /*ScanRequest scanRequest = new ScanRequest()
                .withTableName(tableName);

        ScanResult result = amazonDynamoDB.scan(scanRequest);*/

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        Table table = dynamoDB.getTable(tableName);

        List<KeySchemaElement> keySchemaElementList=
        table.describe().getKeySchema();

        Map<String,String> mapaKey = new HashMap<>();

        keySchemaElementList.forEach(keySchemaElement ->
            mapaKey.put("#attr".concat(keySchemaElement.getAttributeName()), keySchemaElement.getAttributeName())
                );

        ScanSpec scanSpec = new ScanSpec();

        String filterExp =mapaKey.keySet().stream().map(k->"attribute_exists(".concat(k).concat(")")).collect(Collectors.joining(" and "));

        scanSpec.withFilterExpression(filterExp);

        scanSpec.withNameMap(mapaKey);

        IteratorSupport<Item, ScanOutcome> iterator=   table.scan(scanSpec).iterator();
        List<Map> resultList = new ArrayList<>();

        while (iterator.hasNext()){
            resultList.add(iterator.next().asMap());
        }

        return resultList;
    }

}

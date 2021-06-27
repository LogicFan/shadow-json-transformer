package com.github.logicfan.gradle.shadow.transformers

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import spock.lang.Specification

class JsonTransformerTest extends Specification {
    def "mergeJson"() {
        setup:
        def text1 = "{\n" +
                "    \"object\" : {\n" +
                "        \"array\" : [1, 2, 3],\n" +
                "        \"object\" : {\n" +
                "            \"object\" : {\n" +
                "                \"key\" : 13\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"key\" : \"value\"\n" +
                "}"
        def text2 = "{\n" +
                "    \"object\" : {\n" +
                "        \"array\" : [\"hello\", \"world\"],\n" +
                "        \"object\" : {\n" +
                "            \"object\" : {\n" +
                "                \"key\" : 15\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"key\" : \"value\"\n" +
                "}"
        def text3 = "{\n" +
                "    \"object\" : {\n" +
                "        \"array\" : [\"hello\", \"world\"],\n" +
                "        \"object\" : [\"123\"],\n" +
                "        \"key1\" : 13,\n" +
                "        \"key2\" : 14\n" +
                "    },\n" +
                "    \"key\" : 123\n" +
                "}"
        def json1 = JsonParser.parseString(text1)
        def json2 = JsonParser.parseString(text2)
        def json3 = JsonParser.parseString(text3)

        when:
        JsonElement res1 = JsonTransformer.mergeJson(json1, json2)
        JsonElement res2 = JsonTransformer.mergeJson(json2, json3)

        then:
        (((res1 as JsonObject).get("object") as JsonObject).get("array") as JsonArray).size() == 5
        ((res2 as JsonObject).get("object") as JsonObject).get("key1").getAsInt() == 13
        ((res2 as JsonObject).get("object") as JsonObject).get("key2").getAsInt() == 14
    }
}

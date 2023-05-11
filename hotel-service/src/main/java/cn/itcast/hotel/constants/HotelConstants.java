package cn.itcast.hotel.constants;

/**
 * 酒店常量
 *
 * @author shenyl
 * @date 2023/05/11
 */
public class HotelConstants {
    /**
     * 映射名称
     */
    public static final String MAPPING_NAME = "{\n" +
            "  \"mappings\":{\n" +
            "    \"properties\": {\n" +
            "      \"id\":{\n" +
            "        \"type\":\"keyword\"\n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"standard\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"address\":{\n" +
            "        \"type\":\"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"price\":{\n" +
            "        \"type\":\"integer\"\n" +
            "      },\n" +
            "      \"score\":{\n" +
            "        \"type\":\"integer\"\n" +
            "      },\n" +
            "      \"brand\":{\n" +
            "        \"type\":\"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"city\":{\n" +
            "        \"type\":\"keyword\"\n" +
            "      },\n" +
            "      \"starName\":{\n" +
            "        \"type\":\"keyword\"\n" +
            "      },\n" +
            "      \"business\":{\n" +
            "        \"type\":\"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"location\":{\n" +
            "        \"type\": \"geo_point\"\n" +
            "      },\n" +
            "      \"pic\":{\n" +
            "        \"type\":\"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"standard\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}

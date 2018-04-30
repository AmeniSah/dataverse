package edu.harvard.iq.dataverse.metrics;

import edu.harvard.iq.dataverse.util.json.JsonUtil;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MetricsUtilTest {

    private static final long COUNT = 42l;

    @Test
    public void testCountToJson() {
        // This constructor is just here for code coverage. :)
        MetricsUtil metricsUtil = new MetricsUtil();
        JsonObject jsonObject = MetricsUtil.countToJson(COUNT).build();
        System.out.println(JsonUtil.prettyPrint(jsonObject));
        assertEquals(COUNT, jsonObject.getJsonNumber("count").longValue());
    }

    @Test
    public void testDataversesByCategoryToJson() throws InterruptedException {
        List<Object[]> list = new ArrayList<>();
        Object[] obj00 = {"RESEARCH_PROJECTS", 791l};
        Object[] obj01 = {"RESEARCHERS", 745l};
        Object[] obj02 = {"UNCATEGORIZED", 565l};
        Object[] obj03 = {"ORGANIZATIONS_INSTITUTIONS", 250l};
        Object[] obj04 = {"JOURNALS", 106l};
        Object[] obj05 = {"RESEARCH_GROUP", 106l};
        Object[] obj06 = {"TEACHING_COURSES", 20l};
        Object[] obj07 = {"LABORATORY", 17l};
        Object[] obj08 = {"DEPARTMENT", 7l};
        list.add(obj00);
        list.add(obj01);
        list.add(obj02);
        list.add(obj03);
        list.add(obj04);
        list.add(obj05);
        list.add(obj06);
        list.add(obj07);
        list.add(obj08);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date = new Date();
        System.out.println("Before first" + dateFormat.format(date));
        JsonArrayBuilder jab = MetricsUtil.dataversesByCategoryToJson(list);
        JsonArray jsonArray = jab.build();
        date = new Date();
        System.out.println("After first" + dateFormat.format(date));
        jab = MetricsUtil.dataversesByCategoryToJson(list);
        jsonArray = jab.build();
        date = new Date();
        System.out.println("After second" + dateFormat.format(date));
        //System.out.println(JsonUtil.prettyPrint(jsonArray));
        JsonObject jsonObject = jsonArray.getJsonObject(8);
        assertEquals("Department", jsonObject.getString("category"));
        assertEquals(7, jsonObject.getInt("count"));
    }

    @Test
    public void testdatasetsBySubjectToJson() {
        List<Object[]> list = new ArrayList<>();
        Object[] obj00 = {"Social Sciences", 24955l};
        Object[] obj01 = {"Medicine, Health and Life Sciences", 2262l};
        Object[] obj02 = {"Earth and Environmental Sciences", 1631l};
        Object[] obj03 = {"Agricultural Sciences", 1187l};
        Object[] obj04 = {"Other", 980l};
        Object[] obj05 = {"Computer and Information Science", 888l};
        Object[] obj06 = {"Arts and Humanities", 832l};
        Object[] obj07 = {"Astronomy and Astrophysics", 353l};
        Object[] obj08 = {"Business and Management", 346l};
        Object[] obj09 = {"Law", 220l};
        Object[] obj10 = {"Engineering", 203l};
        Object[] obj11 = {"Mathematical Sciences", 123l};
        Object[] obj12 = {"Chemistry", 116l};
        Object[] obj13 = {"Physics", 98l};
        list.add(obj00);
        list.add(obj01);
        list.add(obj02);
        list.add(obj03);
        list.add(obj04);
        list.add(obj05);
        list.add(obj06);
        list.add(obj07);
        list.add(obj08);
        list.add(obj09);
        list.add(obj10);
        list.add(obj11);
        list.add(obj12);
        list.add(obj13);
        JsonArrayBuilder jab = MetricsUtil.datasetsBySubjectToJson(list);
        JsonArray jsonArray = jab.build();
        System.out.println(JsonUtil.prettyPrint(jsonArray));
        JsonObject jsonObject = jsonArray.getJsonObject(13);
        assertEquals("Physics", jsonObject.getString("subject"));
        assertEquals(98, jsonObject.getInt("count"));
    }

}

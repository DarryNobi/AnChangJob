package com.anchangjob.worldchange.anchangjob.dummy;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static  int COUNT = 25;
/*
    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }*/

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }

    /*private static DummyItem createDummyItem(int position) {
        return new DummyItem("title","content");
    }*/

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public  int id;
        public  String title;
        public  String content;
        public  int age_top;
        public  int age_down;
        public  int salary_top;
        public  int salary_down;
        public  int experience;
        public  int job_type;
        public  int amount;
        public  int work_period;

        public DummyItem(int id, String content, String title,int age_top,int age_down,int salary_top,int salary_down,int experience,int job_type,int amount,int work_period) {
            this.id = id;
            this.title=title;
            this.content=content;
            this.age_top=age_top;
            this.age_down=age_down;
            this.salary_top=salary_top;
            this.salary_down=salary_down;
            this.experience=experience;
            this.amount=amount;
            this.job_type=job_type;
            this.work_period=work_period;
        }
        public DummyItem(String title,String content){
            this.id = 0;
            this.title=title;
            this.content=content;
            this.age_top=0;
            this.age_down=0;
            this.salary_top=0;
            this.salary_down=0;
            this.experience=0;
            this.amount=0;
            this.job_type=0;
            this.work_period=0;
        }
        public DummyItem(JSONObject json){
            try {
                this.id = json.getInt("id");
                this.title=json.getString("title");
                this.content=json.getString("content");
                this.age_top=json.getInt("age_top");
                this.age_down=json.getInt("age_down");
                this.salary_top=json.getInt("salary_top");
                this.salary_down=json.getInt("salary_down");
                this.experience=json.getInt("experience");
                this.amount=json.getInt("amount");
                this.job_type=json.getInt("job_type");
                this.work_period=json.getInt("work_period");
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                this.id = 0;
                this.title=title;
                this.content=content;
                this.age_top=0;
                this.age_down=0;
                this.salary_top=0;
                this.salary_down=0;
                this.experience=0;
                this.amount=0;
                this.job_type=0;
                this.work_period=0;
            }

        }
        @Override
        public String toString() {
            return content;
        }
    }
}

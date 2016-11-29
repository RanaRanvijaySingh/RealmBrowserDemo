package example.com.demoapp;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class BookModel extends RealmObject{

    @Required
    private String title;
    private int cost;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

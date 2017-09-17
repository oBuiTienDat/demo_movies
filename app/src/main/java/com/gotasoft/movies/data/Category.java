package com.gotasoft.movies.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dattien on 9/17/17.
 */
@Entity(nameInDb = "Category")
public class Category {
    @Id
    private String id;
    private String name;
    @Generated(hash = 1739025064)
    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1150634039)
    public Category() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

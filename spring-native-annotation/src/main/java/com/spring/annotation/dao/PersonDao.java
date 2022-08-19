package com.spring.annotation.dao;

import org.springframework.stereotype.Repository;

/**
 * @author cyl
 * @date 2022-08-11 10:46
 * @description
 */
@Repository
public class PersonDao {

    private String label = "1";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "PersonDao{" +
                "label='" + label + '\'' +
                '}';
    }
}

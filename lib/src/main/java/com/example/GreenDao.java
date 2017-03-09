package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

public class GreenDao {
    public static void main(String[] args) throws Exception{

        final Schema schema;
        final Entity tree;
        final Entity bench;
        final Entity bathroom;
        final Entity fountain;

        final DaoGenerator generator;

        schema = new Schema(1, "Database");

        tree = schema.addEntity("Tree");
        bench = schema.addEntity("Bench");
        bathroom = schema.addEntity("Bathroom");
        fountain = schema.addEntity("Fountain");

        tree.addIdProperty();
        bench.addIdProperty();
        bathroom.addIdProperty();
        fountain.addIdProperty();

        tree.addDoubleProperty("latitude");
        bench.addDoubleProperty("latitude");
        bathroom.addDoubleProperty("latitude");
        fountain.addDoubleProperty("latitude");

        tree.addDoubleProperty("longitude");
        bench.addDoubleProperty("longitude");
        bathroom.addDoubleProperty("longitude");
        fountain.addDoubleProperty("longitude");


        dataset.addToOne(catagory, catagoryID);

        ToMany catagoryToDataset = catagory.addToMany(dataset, catagoryID);
        catagoryToDataset.setName("Datasets");


        generator = new DaoGenerator();
        generator.generateAll(schema, "./app/src/main/java");
    }
}

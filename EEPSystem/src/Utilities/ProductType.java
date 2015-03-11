/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author songxiong
 */
public enum ProductType {

    SEEDS("inventory","seeds"), SHRUBS("inventory", "shrubs"), TREES("inventory", "trees"),
    CULTUREBOXES("leaftech", "cultureboxes"), GENOMICS("leaftech", "genomics"), 
    PROCESSING("leaftech", "processing"), REFERENCEMATIRIALS("leaftech", "referencematerials");

    private final String databaseName;
    private final String tableName;

    ProductType(String databaseName, String tableName) {
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    public String getDatabaseName() {
        return databaseName;        
    }

    public String getTableName(){
        return tableName;        
    }
}

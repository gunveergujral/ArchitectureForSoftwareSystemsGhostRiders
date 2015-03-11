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

    SEEDS("inventory"), SHRUBS("inventory"), TREES("inventory"),
    CULTUREBOXES("leaftech"), GENOMICS("leaftech"), PROCESSING("leaftech"), REFERENCEMATIRIALS("leaftech");

    private final String databaseName;

    ProductType(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

}

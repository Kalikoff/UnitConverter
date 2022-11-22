// Калмыков 303
package com.example.unitconverter.kalmykov303;

public class Unit {
    private String _name;
    private Double _coeff;

    public Unit (String name, Double coeff) {
        this._name = name;
        this._coeff = coeff;
    }

    public String toString() {
        return this._name;
    }

    public Double getCoeff() {
        return this._coeff;
    }
}
// Калмыков 303
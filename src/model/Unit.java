package model;

import java.util.ArrayList;

public class Unit {
    private String unitCode;
    private String unitName;
    private int credits;
    private String description;
    private ArrayList<String> prerequisites;

    public Unit(String unitCode, String unitName, int credits, String description) {
        if (credits <= 0) {
            throw new IllegalArgumentException("Credits must be greater than 0.");
        }

        this.unitCode = unitCode;
        this.unitName = unitName;
        this.credits = credits;
        this.description = description;
        this.prerequisites = new ArrayList<>();
    }

    public String getUnitCode() {
        return unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public int getCredits() {
        return credits;
    }

    public String getDescription() {
        return description;
    }

    public void addPrerequisite(String code) {
        for (String prerequisite : prerequisites) {
            if (prerequisite.equalsIgnoreCase(code)) {
                return;
            }
        }
        prerequisites.add(code.toUpperCase());
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    // Extra methods for compatibility with Zilan's tests
    public String getCode() {
        return unitCode;
    }

    public String getName() {
        return unitName;
    }
}
package com.edealer.inventory.feature.car;

public class Car {

    private String id;
    private Color color;
    private String makeId;
    private String modelId;
    private Integer year;

    public Car(String id, Color color, String makeId, String modelId, Integer year) {
        this.id = id;
        this.makeId = makeId;
        this.modelId = modelId;
        this.color = color;
        this.year = year;
	}

	public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public String getMakeId() {
        return makeId;
    }

    public String getModelId() {
        return modelId;
    }

	public Integer getYear() {
		return year;
	}
}

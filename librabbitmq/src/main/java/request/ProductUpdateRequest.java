package request;

import java.io.Serializable;

public class ProductUpdateRequest implements Serializable {
    private String name;
    private String color;
    private Boolean active;

    public ProductUpdateRequest() {}

    public ProductUpdateRequest(String name, String color, Boolean active) {
        this.name = name;
        this.color = color;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

package ie.greenfinch.playbook;

import java.util.Objects;

public class ModelClass {

    private String id;
    private String property;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelClass that = (ModelClass) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(property, that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, property);
    }
}

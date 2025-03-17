package citrus.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("data")
    private Data data;
    @JsonProperty("support")
    private Support support;

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "User{" +
                "data=" + data +
                ", support=" + support +
                '}';
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
package seucaioo.com.br.testeretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("pwd")
    @Expose
    private int pwd;

    public Data() {
    }

    public Data(String id, String name, int pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public Data setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Data setName(String name) {
        this.name = name;
        return this;
    }

    public int getPwd() {
        return pwd;
    }

    public Data setPwd(int pwd) {
        this.pwd = pwd;
        return this;
    }
}

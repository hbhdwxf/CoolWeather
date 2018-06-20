package android.coolweather.com.gson;

/**
 * Created by hbhdw on 2018/6/21.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comport comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    public class Comport {
        @SerializedName("txt")
        public String info;
    }

    public class CarWash {
        @SerializedName("txt")
        public String info;
    }

    public class Sport {
        @SerializedName("txt")
        public String info;
    }
}

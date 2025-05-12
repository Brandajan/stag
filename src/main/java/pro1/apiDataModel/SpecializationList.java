package pro1.apiDataModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpecializationList {
    @SerializedName("prijimaciObor")
    public List<Specialization> items; //prijimaci rizeni
}

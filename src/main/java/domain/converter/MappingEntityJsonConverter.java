package domain.converter;

import com.google.gson.Gson;
import data.entity.ListMappingEntity;

/**
 * Created by Imant on 13.05.17.
 */
public class MappingEntityJsonConverter {

    Gson gson = new Gson();

    public String convertToJson(ListMappingEntity mappingEntities) {
        return gson.toJson(mappingEntities);
    }

    public ListMappingEntity convertFromJson(String json) {
        return gson.fromJson(json, ListMappingEntity.class);
    }
}

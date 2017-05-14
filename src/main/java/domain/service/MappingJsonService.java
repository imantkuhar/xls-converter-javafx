package domain.service;

import data.entity.ListMappingEntity;
import domain.converter.MappingEntityJsonConverter;

import java.io.File;

/**
 * Created by Imant on 14.05.17.
 */
public class MappingJsonService {

    public JsonService jsonService = new JsonService();
    public MappingEntityJsonConverter mappingEntityJsonConverter = new MappingEntityJsonConverter();

    public void convertToJson(ListMappingEntity mappingEntities, String filePath) {
        String jsonString = mappingEntityJsonConverter.convertToJson(mappingEntities);
        jsonService.saveToJson(jsonString, filePath);
    }
}

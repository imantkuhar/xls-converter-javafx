package domain.service;

import data.entity.ColumnEntity;
import data.entity.MappingEntity;
import domain.mapper.ColumnMapper;
import domain.parser.XLSParser;

import java.io.File;
import java.util.List;

/**
 * Created by Imant on 13.05.17.
 */
public class XLSService {
    private ColumnMapper columnMapper = new ColumnMapper();

    public List<MappingEntity> getMappingEntities(File file) {
        XLSParser parser = new XLSParser(file);
        return columnMapper.convertToMappingEntities(parser.getColumnNames());
    }

    public List<ColumnEntity> getColumnEntities(File file) {
        XLSParser parser = new XLSParser(file);
        return columnMapper.convertToColumnEntities(parser.getColumnNames());
    }
}

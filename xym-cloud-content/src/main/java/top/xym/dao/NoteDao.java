package top.xym.dao;

import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.Note;
import top.xym.framework.mybatis.dao.BaseDao;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoteDao extends BaseDao<Note> {
    List<Note> getList(Map<String, Object> params);
}
package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.xym.convert.NoteConvert;
import top.xym.dao.NoteDao;
import top.xym.dto.NoteDTO;
import top.xym.entity.Note;
import top.xym.framework.common.constant.Constant;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.mybatis.service.impl.BaseServiceImpl;
import top.xym.query.NoteQuery;
import top.xym.service.NoteService;
import top.xym.vo.NoteVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class NoteServiceImpl extends BaseServiceImpl<NoteDao, Note> implements NoteService {
    @Override
    public PageResult<NoteVO> page(NoteQuery query) {
        // 查询参数
        Map<String, Object> params = getParams(query);
        // 分⻚查询
        IPage<Note> page = getPage(query);
        params.put(Constant.PAGE, page);
        // 数据列表
        List<Note> list = baseMapper.getList(params);
        return new PageResult<>(NoteConvert.INSTANCE.convertList(list), page.getTotal());
    }

    private Map<String, Object> getParams(NoteQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", query.getTitle());
        return params;
    }

    @Override
    public PageResult<NoteVO> getNotesByCategoryId(Long categoryId, NoteQuery query) {
        LambdaQueryWrapper<Note> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Note::getCategoryId, categoryId);
        List<Note> list = baseMapper.selectList(queryWrapper);
        Map<String, Object> params = getParams(query);
        IPage<Note> page = getPage(query);
        params.put(Constant.PAGE, page);
        return new PageResult<>(NoteConvert.INSTANCE.convertList(list), page.getTotal());
    }

    @Override
    public boolean publishNote(NoteDTO noteDTO) {
        Note note = NoteConvert.INSTANCE.convert(noteDTO);
        return baseMapper.insertOrUpdate(note);
    }
}
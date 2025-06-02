package top.xym.service;

import top.xym.dto.NoteDTO;
import top.xym.entity.Note;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.mybatis.service.BaseService;
import top.xym.query.NoteQuery;
import top.xym.vo.NoteVO;

public interface NoteService extends BaseService<Note> {
    PageResult<NoteVO> page(NoteQuery query);
    // 分类笔记列表
    PageResult<NoteVO> getNotesByCategoryId(Long categoryId, NoteQuery query);

    // 发布笔记
    boolean publishNote(NoteDTO noteDTO);
}
package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.dto.NoteDTO;
import top.xym.entity.Note;
import top.xym.vo.NoteVO;

import java.util.List;

@Mapper
public interface NoteConvert {
    NoteConvert INSTANCE = Mappers.getMapper(NoteConvert.class);
    NoteVO convert(Note entity);
    List<NoteVO> convertList(List<Note> list);

    Note convert(NoteDTO noteDTO);
}
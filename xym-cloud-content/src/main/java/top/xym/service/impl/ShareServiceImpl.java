package top.xym.service.impl;

import org.springframework.stereotype.Service;
import top.xym.dao.ShareDao;
import top.xym.entity.Share;
import top.xym.framework.mybatis.service.impl.BaseServiceImpl;
import top.xym.service.ShareService;

@Service
public class ShareServiceImpl extends BaseServiceImpl<ShareDao, Share> implements ShareService {

}
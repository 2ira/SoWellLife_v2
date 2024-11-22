package cn.mentalhealth.dao;

import cn.mentalhealth.domain.DocInfo;

import java.util.List;

public interface DocInfoDao {
    public List<DocInfo> getAllDocInfo();
    public List<DocInfo> getDocInfoByCondition(String condition);
    public void insertDocInfo(DocInfo docInfo);
    public void updateDocInfo(DocInfo docInfo, String condition);
    public void deleteDocInfo(String condition);

}

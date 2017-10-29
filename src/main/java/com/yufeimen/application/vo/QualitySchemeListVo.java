package com.yufeimen.application.vo;

import com.yufeimen.application.model.MedicQualitySchemeTitle;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class QualitySchemeListVo {

    private List<MedicQualitySchemeTitle> qualitySchemeTitle;  //质量评估方案
    private int total;    //总数

    public List<MedicQualitySchemeTitle> getQualitySchemeTitle() {
        return qualitySchemeTitle;
    }

    public void setQualitySchemeTitle(List<MedicQualitySchemeTitle> qualitySchemeTitle) {
        this.qualitySchemeTitle = qualitySchemeTitle;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

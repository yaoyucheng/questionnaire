package com.yyc.dto;

import javax.validation.constraints.NotNull;

/**
 * @author yuchengyao
 */
public class QuestionnaireQuestionItemInsertCmd {

    /**
     * 问题code
     */
    private String questionnaireQuestionCode;

    /**
     * 细项排序
     */
    @NotNull(message = "细项排序不可为空")
    private Integer questionnaireQuestionItemSort;

    /**
     * 细项类型code：单选、多选、填空等
     */
    @NotNull(message = "细项类型不可为空")
    private String questionnaireQuestionTypeCode;

    /**
     * 细项内容
     */
    private String questionnaireQuestionItemContent;
}

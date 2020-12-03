package com.yyc.dto.data;

import com.yyc.dto.QuestionnaireQuestionInsertCmd;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yuchengyao
 */
@Data
public class QuestionnaireDTO {

    /**
     * 问卷code
     */
    private String questionnaireCode;

    /**
     * 问卷标题
     */
    private String questionnaireTitle;

    /**
     * 副标题
     */
    private String questionnaireSubtitle;

    /**
     * 问卷署名:问卷结尾显示的落款
     */
    private String questionnaireSignature;

    /**
     * 问卷开始时间
     */
    private Date questionnaireStartTime;

    /**
     * 问卷结束时间
     */
    private Date questionnaireEndTime;

    /**
     * 问卷问题列表
     */
    private List<QuestionnaireQuestionDTO> questionnaireQuestionDTOS;
}

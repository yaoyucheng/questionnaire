package com.yyc.questionnaire.dto;

import com.yyc.questionnaire.dto.clientobject.UserProfileCO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * UserProfileAddCmd
 *
 * @author Frank Zhang
 * @date 2019-02-28 6:20 PM
 */
@Data
public class UserProfileAddCmd extends CommonCommand {

    @NotNull
    private UserProfileCO userProfileCO;
}

package com.utc.api13.commun.enumerations;

import com.utc.api13.commun.IErrorType;

/**
 * @author Amstrong
 *
 */
public enum ErrorTypeEnum implements IErrorType {

    /**
     * functional code for login failure
     */
    LOGIN_FAILED("data.login.failed"),

    /**
     * When the object in the import file is not a private user
     */
    NON_PRIVATE_USER(
            "data.user.notPrivate"), /**
                                      * When the imported user exist already
                                      */
    DUPLICATED_USER("data.user.duplicated"), /**
                                              * When the uid and/or the login
                                              * and/or the password are missing
                                              */
    MISSING_INFO("data.user.missingInfo");
    private final String code;

    private ErrorTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
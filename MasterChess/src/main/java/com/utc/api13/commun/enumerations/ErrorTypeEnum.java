/**
 * 
 */
package com.utc.api13.commun.enumerations;

import com.utc.api13.commun.IErrorType;

/**
 * @author Amstrong
 *
 */
public enum ErrorTypeEnum implements IErrorType{

	/**
	 * functional for code for login failure
	 */
	LOGIN_FAILED("data.login.failed");
    private final String code;
    private ErrorTypeEnum(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
}

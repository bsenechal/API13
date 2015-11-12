/**
 * 
 */
package com.utc.api13.commun.enumerations;

/**
 * @author Amstrong
 *
 */
public enum ErrorTypeEnum {

	Exemple("exp");
    private final String code;
    private ErrorTypeEnum(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
}

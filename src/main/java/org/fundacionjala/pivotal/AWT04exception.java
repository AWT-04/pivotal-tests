/**
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package org.fundacionjala.pivotal;

/**
 * The class is an paramInvalidException.
 *
 * @author Raul Laredo on 09/19/2019.
 * @version v1.0
 */
public class AWT04exception extends RuntimeException {
    private final int code;
    private final String message;


    public AWT04exception(int code, final String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Get the error messages for specific cases.
     * @return String message of the errors.
     */
    @Override
    public String getMessage() {
        String finalMessage = null;
        switch (code) {
            case 1:
                finalMessage = "The config file is not found all config is null cannot continue: " + message;
                break;
            case 2:
                finalMessage = "errors on file : " + message;
                break;
            default:
                finalMessage = "not set exception : " + message;
                break;
        }
        return finalMessage;
    }
}

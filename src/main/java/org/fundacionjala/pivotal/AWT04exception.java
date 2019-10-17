package org.fundacionjala.pivotal;

public class AWT04exception extends RuntimeException {
    private final int code;
    private final String message;


    public AWT04exception(int code, final String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Get the error message for the params.
     * @return
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

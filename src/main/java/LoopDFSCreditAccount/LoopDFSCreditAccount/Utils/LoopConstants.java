package LoopDFSCreditAccount.LoopDFSCreditAccount.Utils;

public class LoopConstants {


    public enum ApiResponseCodes {
        OK("00"),
        GENERAL_ERROR("01"),

        ACTIVE("1");
        private final String code;
        ApiResponseCodes(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}

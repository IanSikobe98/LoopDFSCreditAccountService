package LoopDFSCreditAccount.LoopDFSCreditAccount.model;

import lombok.Data;

@Data
public class AccountCreationDTO {

    private String iban;
    private String bicSwiftCode;
    private String clientId;
    private String id;
}

package LoopDFSCreditAccount.LoopDFSCreditAccount.Service;

import LoopDFSCreditAccount.LoopDFSCreditAccount.model.AccountCreationDTO;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.ApiResponse;

public interface AccountService {

    ApiResponse createAccount(AccountCreationDTO accountCreationRequest);

    ApiResponse getAllAccounts();

    ApiResponse updateAccount(AccountCreationDTO accountCreationRequest);

    ApiResponse deleteAccount(Integer id);



}

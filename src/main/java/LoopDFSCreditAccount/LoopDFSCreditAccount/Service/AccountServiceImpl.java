package LoopDFSCreditAccount.LoopDFSCreditAccount.Service;

import LoopDFSCreditAccount.LoopDFSCreditAccount.Utils.LoopConstants;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Account;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Client;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.AccountCreationDTO;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.ApiResponse;
import LoopDFSCreditAccount.LoopDFSCreditAccount.repositories.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

    @Autowired
    CrudTransactionsService crudTransactionsService;

    @Autowired
    private CrudService crudService;

    @Override
    public ApiResponse createAccount(AccountCreationDTO accountCreationRequest){
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            log.info("Fetching Client  using id {} ........",accountCreationRequest.getClientId());
            Client client = crudTransactionsService.getClientById(Integer.valueOf(accountCreationRequest.getClientId()));
            if (client == null) {
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
                apiResponse.setMessage("Client for Id provided does not exist");
            } else {
                log.info("Client Found.Creating Account using iban {} ........",accountCreationRequest.getIban());
                Account account = new Account();
                account.setIban(accountCreationRequest.getIban());
                account.setBicswift(accountCreationRequest.getBicSwiftCode());
                account.setClientid(Integer.valueOf(accountCreationRequest.getClientId()));
                account.setStatus(Integer.valueOf(LoopConstants.ApiResponseCodes.ACTIVE.getCode()));
                crudService.save(account);
                log.info("Account successfully created");


                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.OK.getCode());
                apiResponse.setMessage("Account successfully created");
                apiResponse.setEntity(account);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Error occured creating account {}",e.getMessage());
            apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            apiResponse.setMessage("Error occured creating account");
            apiResponse.setResponseDescription(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public  ApiResponse getAllAccounts(){
        ApiResponse apiResponse = new ApiResponse();
        try{
            List<Account> accounts =crudTransactionsService.getAllccounts();
            if(accounts.isEmpty()){
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
                apiResponse.setMessage("There are no accounts present at the moment");
            }
            else{
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.OK.getCode());
                apiResponse.setMessage("Account successfully fetched");
                apiResponse.setEntity(accounts);
                log.info("Account successfully fetched");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Error occured fetching accounts {}",e.getMessage());
            apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            apiResponse.setMessage("Error occured fetching accounts");
            apiResponse.setResponseDescription(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse updateAccount(AccountCreationDTO accountCreationRequest){
        ApiResponse apiResponse  = new ApiResponse();

        try {
            Account account = crudTransactionsService.fetchAccountById(Integer.valueOf(accountCreationRequest.getId()));
            if(account==null){
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
                apiResponse.setMessage("There are no accounts present at the moment for id "+accountCreationRequest.getId());
                log.info("There are no accounts present at the moment for id "+accountCreationRequest.getId());
            }
            else{
                account.setIban(accountCreationRequest.getIban());
                account.setBicswift(accountCreationRequest.getBicSwiftCode());
                crudService.saveOrUpdate(account);

                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.OK.getCode());
                apiResponse.setMessage("Account successfully updated");
                apiResponse.setEntity(account);
                log.info("Account successfully updated");

            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Error occured updating accounts {}",e.getMessage());
            apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            apiResponse.setMessage("Error occured updating accounts");
            apiResponse.setResponseDescription(e.getMessage());
        }
        return apiResponse;
    }

    public ApiResponse deleteAccount(Integer id){
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            log.info("Fetching account of id {}",id);
            Account account = crudTransactionsService.fetchAccountById(Integer.valueOf(id));
            if(account==null){
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
                apiResponse.setMessage("There are no accounts present at the moment for id "+id);
                log.info("There are no accounts present at the moment for id "+id);
            }
            else{
                log.info("Account found deleting account of id {}",id);
                crudTransactionsService.deleteAccount(id);
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.OK.getCode());
                apiResponse.setMessage("Account successfully deleted");
                apiResponse.setEntity(account);
                log.info("Account successfully deleted");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Error occured deleting accounts {}",e.getMessage());
            apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            apiResponse.setMessage("Error occured deleting accounts");
            apiResponse.setResponseDescription(e.getMessage());
        }
        return apiResponse;
    }
}

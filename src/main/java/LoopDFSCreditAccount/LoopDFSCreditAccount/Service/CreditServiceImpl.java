package LoopDFSCreditAccount.LoopDFSCreditAccount.Service;

import LoopDFSCreditAccount.LoopDFSCreditAccount.Utils.LoopConstants;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Account;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Card;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.ApiResponse;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.CardDTO;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.CardUpdateDTO;
import LoopDFSCreditAccount.LoopDFSCreditAccount.repositories.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CreditServiceImpl implements CreditService{

    @Autowired
    CrudTransactionsService crudTransactionsService;

    @Autowired
    private CrudService crudService;

    @Override
    public ApiResponse createCard(CardDTO cardDTO){
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            log.info("Fetching Account using id {} ........",cardDTO.getAccountId());
            Account account = crudTransactionsService.fetchAccountById(Integer.valueOf(cardDTO.getAccountId()));
            if (account == null) {
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
                apiResponse.setMessage("Account for Id provided does not exist");
            } else {
                log.info("Account Found.Creating Card using card alias {} ........",cardDTO.getCardAlias());
                Card card = new Card();
                card.setCardName(cardDTO.getCardAlias());
                card.setCardType(cardDTO.getCardType());
                card.setAccountId(Integer.valueOf(cardDTO.getAccountId()));
                card.setStatus(Integer.valueOf(LoopConstants.ApiResponseCodes.ACTIVE.getCode()));
                crudService.save(card);
                log.info("Card successfully created");

                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.OK.getCode());
                apiResponse.setMessage("Card successfully created");
                apiResponse.setEntity(card);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Error occured creating card {}",e.getMessage());
            apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            apiResponse.setMessage("Error occured creating card");
            apiResponse.setResponseDescription(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public  ApiResponse getAllCards(){
        ApiResponse apiResponse = new ApiResponse();
        try{
            List<Card> cards =crudTransactionsService.getAllCards();
            if(cards.isEmpty()){
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
                apiResponse.setMessage("There are no cards present at the moment");
            }
            else{
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.OK.getCode());
                apiResponse.setMessage("Cards successfully fetched");
                apiResponse.setEntity(cards);
                log.info("Cards successfully fetched");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Error occured fetching cards {}",e.getMessage());
            apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            apiResponse.setMessage("Error occured fetching cards");
            apiResponse.setResponseDescription(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getCardsByAccountId(Integer id){
        ApiResponse apiResponse = new ApiResponse();
        try{
            List<Card> cards =crudTransactionsService.getCardsByAccountId(id);
            if(cards.isEmpty()){
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
                apiResponse.setMessage("There are no cards present at the moment");
            }
            else{
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.OK.getCode());
                apiResponse.setMessage("Cards successfully fetched");
                apiResponse.setEntity(cards);
                log.info("Cards successfully fetched");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Error occured fetching cards {}",e.getMessage());
            apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            apiResponse.setMessage("Error occured fetching cards");
            apiResponse.setResponseDescription(e.getMessage());
        }
        return apiResponse;
    }

    public ApiResponse updateCard(CardUpdateDTO cardDTO){
        ApiResponse apiResponse  = new ApiResponse();

        try {
            Card card = crudTransactionsService.fetchCardsById(Integer.valueOf(cardDTO.getId()));
            if(card==null){
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
                apiResponse.setMessage("There are no cards present at the moment for id "+cardDTO.getId());
                log.info("There are no cards present at the moment for id "+cardDTO.getId());
            }
            else{
                card.setCardName(cardDTO.getCardAlias());
                crudService.saveOrUpdate(card);

                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.OK.getCode());
                apiResponse.setMessage("Card successfully updated");
                apiResponse.setEntity(card);
                log.info("Card successfully updated");

            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Error occured updating cards {}",e.getMessage());
            apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            apiResponse.setMessage("Error occured updating cards");
            apiResponse.setResponseDescription(e.getMessage());
        }
        return apiResponse;
    }

    public ApiResponse deleteCard(Integer id){
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            log.info("Fetching card of id {}",id);
            Card card = crudTransactionsService.fetchCardsById(Integer.valueOf(id));
            if(card==null){
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
                apiResponse.setMessage("There are no cards present at the moment for id "+id);
                log.info("There are no cards present at the moment for id "+id);
            }
            else{
                log.info("Card found deleting card of id {}",id);
                crudTransactionsService.deleteCard(id);
                apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.OK.getCode());
                apiResponse.setMessage("Card successfully deleted");
                log.info("Card successfully deleted");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Error occured deleting cards {}",e.getMessage());
            apiResponse.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            apiResponse.setMessage("Error occured deleting cards");
            apiResponse.setResponseDescription(e.getMessage());
        }
        return apiResponse;
    }
}

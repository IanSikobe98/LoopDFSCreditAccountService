package LoopDFSCreditAccount.LoopDFSCreditAccount.Service;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.ApiResponse;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.CardDTO;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.CardUpdateDTO;

public interface CreditService {

    ApiResponse createCard(CardDTO cardDTO);

    ApiResponse getAllCards();

    ApiResponse getCardsByAccountId(Integer id);

    ApiResponse updateCard(CardUpdateDTO cardDTO);

    ApiResponse deleteCard(Integer id);
}

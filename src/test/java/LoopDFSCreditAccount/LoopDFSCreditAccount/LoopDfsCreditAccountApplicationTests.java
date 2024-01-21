package LoopDFSCreditAccount.LoopDFSCreditAccount;

import LoopDFSCreditAccount.LoopDFSCreditAccount.Service.AccountService;
import LoopDFSCreditAccount.LoopDFSCreditAccount.Service.CreditService;
import LoopDFSCreditAccount.LoopDFSCreditAccount.Utils.LoopConstants;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Account;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Card;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.AccountCreationDTO;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.ApiResponse;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.CardDTO;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.CardUpdateDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class LoopDfsCreditAccountApplicationTests {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CreditService creditService;
	@Test
	void testCreateAccount() {
		AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
		accountCreationDTO.setIban("IB34");
		accountCreationDTO.setBicSwiftCode("BIC23");
		accountCreationDTO.setClientId("1");

		ApiResponse<Account> apiResponse = accountService.createAccount(accountCreationDTO);

		// Assertions
		assertEquals(LoopConstants.ApiResponseCodes.OK.getCode(), apiResponse.getResponseCode());
		assertEquals("Account successfully created", apiResponse.getMessage());
		assertNotNull(apiResponse.getEntity());
	}


	@Test
	void testGetAllAccounts() {
		ApiResponse<List<Account>> apiResponse = accountService.getAllAccounts();

		// Assertions
		assertEquals(LoopConstants.ApiResponseCodes.OK.getCode(), apiResponse.getResponseCode());
		assertEquals("Account successfully fetched", apiResponse.getMessage());
		assertNotNull(apiResponse.getEntity());
	}

	@Test
	void testUpdateAccount() {
		AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
		//Provide existing id for update
		accountCreationDTO.setId("1");
		accountCreationDTO.setBicSwiftCode("BT-189");
		accountCreationDTO.setIban("IB-222");



		ApiResponse<Account> apiResponse = accountService.updateAccount(accountCreationDTO);

		// Assertions
		assertEquals(LoopConstants.ApiResponseCodes.OK.getCode(), apiResponse.getResponseCode());
		assertEquals("Account successfully updated", apiResponse.getMessage());
		assertNotNull(apiResponse.getEntity());
	}

	@Test
	void testDeleteAccount() {
		// Your test logic here
		Integer accountId = 3; // Provide an existing account ID for testing

		ApiResponse<Account> apiResponse = accountService.deleteAccount(accountId);

		// Assertions
		assertEquals(LoopConstants.ApiResponseCodes.OK.getCode(), apiResponse.getResponseCode());
		assertEquals("Account successfully deleted", apiResponse.getMessage());
		assertNotNull(apiResponse.getEntity());
	}



	@Test
	void testCreateCard() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setAccountId("1");
		cardDTO.setCardAlias("VISA3");
		cardDTO.setCardType("VISA");


		ApiResponse<Card> apiResponse = creditService.createCard(cardDTO);


		assertEquals(LoopConstants.ApiResponseCodes.OK.getCode(), apiResponse.getResponseCode());
		assertEquals("Card successfully created", apiResponse.getMessage());
		assertNotNull(apiResponse.getEntity());

	}

	@Test
	void testGetAllCardsWhenCardsPresent() {
		ApiResponse<List<Card>> apiResponse = creditService.getAllCards();

		// Assertions
		assertEquals(LoopConstants.ApiResponseCodes.OK.getCode(), apiResponse.getResponseCode());
		assertEquals("Cards successfully fetched", apiResponse.getMessage());
		assertNotNull(apiResponse.getEntity());

	}


	@Test
	void testGetCardsByAccountId() {
		// Mocking
		Integer accountId = 1;

		// Test
		ApiResponse<List<Card>> apiResponse = creditService.getCardsByAccountId(accountId);

		// Assertions
		assertEquals(LoopConstants.ApiResponseCodes.OK.getCode(), apiResponse.getResponseCode());
		assertEquals("Cards successfully fetched", apiResponse.getMessage());
		assertNotNull(apiResponse.getEntity());

	}

	@Test
	void testUpdateCard() {
		// Mocking
		CardUpdateDTO cardDTO = new CardUpdateDTO();
		cardDTO.setId("1");
		cardDTO.setCardAlias("MOCKTEST");

		// Test
		ApiResponse<Card> apiResponse = creditService.updateCard(cardDTO);

		// Assertions
		assertEquals(LoopConstants.ApiResponseCodes.OK.getCode(), apiResponse.getResponseCode());
		assertEquals("Card successfully updated", apiResponse.getMessage());
		assertNotNull(apiResponse.getEntity());

	}

	@Test
	void testDeleteCard() {
		// Mocking
		Integer cardId = 2;

		// Test
		ApiResponse<Card> apiResponse = creditService.deleteCard(cardId);

		// Assertions
		assertEquals(LoopConstants.ApiResponseCodes.OK.getCode(), apiResponse.getResponseCode());
		assertEquals("Card successfully deleted", apiResponse.getMessage());

		// Verify interactions with mocked dependencies

	}
}



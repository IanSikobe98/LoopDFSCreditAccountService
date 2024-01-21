package LoopDFSCreditAccount.LoopDFSCreditAccount.controllers;

import LoopDFSCreditAccount.LoopDFSCreditAccount.Service.CreditService;
import LoopDFSCreditAccount.LoopDFSCreditAccount.Utils.LoopConstants;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.ApiResponse;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.CardDTO;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.CardUpdateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/card")
@Slf4j
public class CardController {

    @Autowired
    CreditService creditService;


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCards(@RequestBody CardDTO cardDTO, HttpServletRequest request) {
        ApiResponse response = new ApiResponse();
        log.info("POST /create | body :: {}",  cardDTO);
        try{
            response = creditService.createCard(cardDTO);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            response.setMessage("Error Ocurred Processing Request.Please try again later");
            log.info("Error occurred creating cards for payload {}",request);


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCards(HttpServletRequest request) {
        ApiResponse response = new ApiResponse();
        log.info("POST /read | body :: {}",  request);
        try{
            response = creditService.getAllCards();
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            response.setMessage("Error Ocurred Processing Request.Please try again later");
            log.info("Error occurred fetching cards  for payload {}",request);


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCards(@RequestBody CardUpdateDTO cardUpdateDTO, HttpServletRequest request) {
        ApiResponse response = new ApiResponse();
        log.info("POST /update | body :: {}",  cardUpdateDTO);
        try{
            response = creditService.updateCard(cardUpdateDTO);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            response.setMessage("Error Ocurred Processing Request.Please try again later");
            log.info("Error occurred updating cards  for payload {}",request);


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCards(@PathVariable Integer id, HttpServletRequest request) {
        ApiResponse response = new ApiResponse();
        log.info("POST /delete | body :: {}",  id);
        try{
            response = creditService.deleteCard(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            response.setMessage("Error Ocurred Processing Request.Please try again later");
            log.info("Error occurred deleting card  for payload {}",request);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/fetchCardsByAccountId/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchCardsByAccountId(@PathVariable Integer id, HttpServletRequest request) {
        ApiResponse response = new ApiResponse();
        log.info("POST /fetchCardsByAccountId | body :: {}",  id);
        try{
            response = creditService.getCardsByAccountId(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            response.setMessage("Error Ocurred Processing Request.Please try again later");
            log.info("Error occurred fetching cards  for payload {}",request);


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

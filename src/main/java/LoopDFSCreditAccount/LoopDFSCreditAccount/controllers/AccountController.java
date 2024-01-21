package LoopDFSCreditAccount.LoopDFSCreditAccount.controllers;

import LoopDFSCreditAccount.LoopDFSCreditAccount.Service.AccountService;
import LoopDFSCreditAccount.LoopDFSCreditAccount.Utils.LoopConstants;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Account;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.AccountCreationDTO;
import LoopDFSCreditAccount.LoopDFSCreditAccount.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    AccountService accountsservice;

    @Autowired
    Environment environment;


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccounts(@RequestBody AccountCreationDTO accountCreationDTO, HttpServletRequest request) {
        ApiResponse response = new ApiResponse();
        log.info("POST /create | body :: {}",  accountCreationDTO);
        try{
            response = accountsservice.createAccount(accountCreationDTO);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            response.setMessage("Error Ocurred Processing Request.Please try again later");
            log.info("Error occurred creating accounts token  for payload{}",request);


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccounts(HttpServletRequest request) {
        ApiResponse response = new ApiResponse();
        log.info("POST /read | body :: {}",  request);
        try{
            response = accountsservice.getAllAccounts();
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            response.setMessage("Error Ocurred Processing Request.Please try again later");
            log.info("Error occurred fetching accounts  for payload{}",request);


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAccounts(@RequestBody AccountCreationDTO accountCreationDTO, HttpServletRequest request) {
        ApiResponse response = new ApiResponse();
        log.info("POST /update | body :: {}",  accountCreationDTO);
        try{
            response = accountsservice.updateAccount(accountCreationDTO);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            response.setMessage("Error Ocurred Processing Request.Please try again later");
            log.info("Error occurred updating account  for payload{}",request);


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAccounts(@PathVariable Integer id, HttpServletRequest request) {
        ApiResponse response = new ApiResponse();
        log.info("POST /delete | body :: {}",  id);
        try{
            response = accountsservice.deleteAccount(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setResponseCode(LoopConstants.ApiResponseCodes.GENERAL_ERROR.getCode());
            response.setMessage("Error Ocurred Processing Request.Please try again later");
            log.info("Error occurred deleting account  for payload{}",request);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

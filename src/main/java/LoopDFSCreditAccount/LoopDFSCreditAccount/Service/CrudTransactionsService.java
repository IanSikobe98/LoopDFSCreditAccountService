package LoopDFSCreditAccount.LoopDFSCreditAccount.Service;


import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Account;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Card;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Client;
import LoopDFSCreditAccount.LoopDFSCreditAccount.repositories.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CrudTransactionsService {
    @Autowired
    Environment environment;

    @Autowired
    CrudService crudService;

    public Client getClientById(Integer id) {
        String query ="select s from Client s where status = 1 and id = "+id;
        Client client = new LoopDaoImpl(crudService,environment).getClientById(query);
        return client;
    }

    public List<Account> getAllccounts(){
        String query ="select s from Account s where status = 1";
        List <Account>  accounts= new LoopDaoImpl(crudService,environment).getAccounts(query);
        return accounts;
    }

    public Account fetchAccountById(Integer id){
        String query ="select s from Account s where status = 1 and id="+id;
        List <Account>  accounts= new LoopDaoImpl(crudService,environment).getAccounts(query);
        Account account = accounts.isEmpty()?null: accounts.get(0);
        return account;
    }

    public void deleteAccount(Integer id){
        String query = "Delete  from Account where id ="+id;
        new LoopDaoImpl(crudService,environment).executeQuery(query);
    }

    public void deleteCard(Integer id){
        String query = "Delete  from Card where id ="+id;
        new LoopDaoImpl(crudService,environment).executeQuery(query);
    }

    public List<Card> getAllCards(){
        String query ="select s from Card s where status = 1";
        List <Card>  cards= new LoopDaoImpl(crudService,environment).getCards(query);
        return cards;
    }

    public List<Card> getCardsByAccountId(Integer id){
        String query ="select s from Card s where status = 1 and accountId="+id;
        List <Card>  cards= new LoopDaoImpl(crudService,environment).getCards(query);
        return cards;
    }

    public Card fetchCardsById(Integer id){
        String query ="select s from Card s where status = 1 and id="+id;
        List <Card>  cards= new LoopDaoImpl(crudService,environment).getCards(query);
        Card card = cards.isEmpty()?null: cards.get(0);
        return card;
    }
}

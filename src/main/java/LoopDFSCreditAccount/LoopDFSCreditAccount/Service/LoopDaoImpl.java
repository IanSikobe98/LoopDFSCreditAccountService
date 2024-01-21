package LoopDFSCreditAccount.LoopDFSCreditAccount.Service;

import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Account;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Card;
import LoopDFSCreditAccount.LoopDFSCreditAccount.entity.Client;
import LoopDFSCreditAccount.LoopDFSCreditAccount.repositories.CrudService;
import org.springframework.core.env.Environment;

import java.util.Collections;
import java.util.List;

public class LoopDaoImpl {

    private CrudService databaseCrudService;

    private Environment environment;

    public LoopDaoImpl(CrudService databaseCrudService,Environment environment){
        this.environment = environment;
        this.databaseCrudService = databaseCrudService;
    }

    public Client getClientById(String query) {
        List<Client> clients = databaseCrudService.fetchWithHibernateQuery(query, Collections.EMPTY_MAP);
        Client client = clients.isEmpty()?null: clients.get(0);
        return client;
    }

    public List<Account> getAccounts(String query){
        List<Account> accounts = databaseCrudService.fetchWithHibernateQuery(query, Collections.EMPTY_MAP);
        return accounts;
    }

    public List<Card> getCards(String query){
        List<Card> cards = databaseCrudService.fetchWithHibernateQuery(query, Collections.EMPTY_MAP);
        return cards;
    }

    public void executeQuery(String query){
        databaseCrudService.executeHibernateQuery(query,Collections.EMPTY_MAP);
    }

}

package packagetracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import packagetracking.model.CreditCard;
import packagetracking.repository.CreditCardRepository;
import packagetracking.repository.DeliveryRequestRepository;
import packagetracking.service.CreditCardService;

import java.util.List;

@Component
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Override
    public List<CreditCard> getAllCreditCards(int userid) {
        return creditCardRepository.findAllByRequestUser(userid);
    }

    @Override
    public CreditCard saveCreditCard(CreditCard CreditCard) {
        return creditCardRepository.save(CreditCard);
    }

    @Override
    public CreditCard getCreditCardById(Integer CreditCardId) {
        return creditCardRepository.getById(CreditCardId);
    }

    @Override
    public void deleteCreditCardById(Integer CreditCardId) {
        creditCardRepository.deleteById(CreditCardId);
    }

    @Override
    public List<CreditCard> searchCreditCards(String searchString) {
        return creditCardRepository.findAllByIssueNameContaining(searchString);
    }

    @Override
    public List<CreditCard> getAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public CreditCard getPreferred(int userid) {
        return creditCardRepository.findPreferredCard(userid);
    }
}

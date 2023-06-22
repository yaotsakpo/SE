package packagetracking.service;

import org.springframework.stereotype.Service;
import packagetracking.model.CreditCard;

import java.util.List;

@Service
public interface CreditCardService {
    public abstract List<CreditCard> getAllCreditCards(int userid);
    public abstract CreditCard saveCreditCard(CreditCard CreditCard);
    public abstract CreditCard getCreditCardById(Integer CreditCardId);
    public abstract void deleteCreditCardById(Integer CreditCardId);
    public abstract List<CreditCard> searchCreditCards(String searchString);

    public abstract List<CreditCard> getAll();
    public abstract CreditCard getPreferred(int userid);
}

package packagetracking.service;

import org.springframework.stereotype.Service;
import packagetracking.model.Invoice;

import java.util.List;

@Service
public interface InvoiceService {

    public abstract List<Invoice> getAllInvoices();
    public abstract Invoice saveInvoice(Invoice Invoice);
    public abstract Invoice getInvoiceById(Integer InvoiceId);
    public abstract void deleteInvoiceById(Integer InvoiceId);
    public abstract List<Invoice> searchInvoices(String searchString);
}

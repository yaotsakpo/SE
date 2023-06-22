package packagetracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import packagetracking.model.Invoice;
import packagetracking.repository.InvoiceRepository;
import packagetracking.service.InvoiceService;

import java.util.List;

@Component
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAllInvoices() {
        return null;
    }

    @Override
    public Invoice saveInvoice(Invoice Invoice) {
        return invoiceRepository.save(Invoice);
    }

    @Override
    public Invoice getInvoiceById(Integer InvoiceId) {
        return null;
    }

    @Override
    public void deleteInvoiceById(Integer InvoiceId) {

    }

    @Override
    public List<Invoice> searchInvoices(String searchString) {
        return null;
    }
}

package todo1.sysiems.store.invoice.service;

import todo1.sysiems.store.invoice.entity.Invoice;
import todo1.sysiems.store.invoice.entity.InvoiceItem;
import todo1.sysiems.store.invoice.repository.InvoiceItemsRepository;
import todo1.sysiems.store.invoice.repository.InvoiceRepository;
import todo1.sysiems.store.product.entity.Product;
import todo1.sysiems.store.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemsRepository invoiceItemsRepository;

    @Autowired
    ProductService productService;

    @Override
    public List<Invoice> findInvoiceAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if (invoiceDB != null) {
            return invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach(invoiceItem -> {
            productService.updateStock(invoiceItem.getProductId(), (int) (invoiceItem.getQuantity() * -1));
        });

        return invoiceDB;
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if ( invoiceDB == null || !invoiceDB.getState().equals("CREATED") ) {
            return null;
        }
        
        invoiceDB.setState("DELETED");

        invoiceDB.getItems().forEach(invoiceItem -> {
            productService.updateStock(invoiceItem.getProductId(), (int) (invoiceItem.getQuantity()));
        });

        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {

        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (null != invoice) {

            List<InvoiceItem> listItem = invoice.getItems().stream().map(invoiceItem -> {
                Product product = productService.getProduct(invoiceItem.getProductId());
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(listItem);
        }
        return invoice;
    }
}

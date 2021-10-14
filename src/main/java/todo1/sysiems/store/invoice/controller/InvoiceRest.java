package todo1.sysiems.store.invoice.controller;

import todo1.sysiems.store.helper.FormatMessage;
import todo1.sysiems.store.invoice.entity.Invoice;
import todo1.sysiems.store.invoice.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceRest {

    @Autowired
    InvoiceService invoiceService;

    private FormatMessage message = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<Invoice>> listAllInvoices() {
        List<Invoice> invoices = invoiceService.findInvoiceAll();
        if (invoices.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(invoices);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") long id) {

        Invoice invoice = invoiceService.getInvoice(id);
        if (null == invoice) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message.formatMessage(result));
        }
        Invoice invoiceDB = invoiceService.createInvoice(invoice);

        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {

        Invoice invoice = invoiceService.getInvoice(id);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        invoice = invoiceService.deleteInvoice(invoice);
        return ResponseEntity.ok(invoice);
    }

   
}

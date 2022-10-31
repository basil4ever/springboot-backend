package net.javaguides.springbootbackend.controller;


import net.javaguides.springbootbackend.dto.InvoiceReportDTO;
import net.javaguides.springbootbackend.exception.ResourceNotFoundException;
import net.javaguides.springbootbackend.model.Invoice;
import net.javaguides.springbootbackend.repository.InvoiceRepository;
import net.javaguides.springbootbackend.services.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImpl invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping()
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/pie-chart-data")
    public ResponseEntity<List<InvoiceReportDTO>> getDataForPieChart() {
        List<InvoiceReportDTO> reports = invoiceService.sumInvoiceByEmployee();
        return ResponseEntity.ok(reports);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exist with id :" + id));
        return ResponseEntity.ok(invoice);
    }

    @PostMapping()
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoiceDetails) {
        return ResponseEntity.ok(invoiceRepository.save(invoiceDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteInvoice(@PathVariable(value = "id") Long id)
        throws ResourceNotFoundException {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exist with id : " + id));
        invoiceRepository.delete(invoice);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}

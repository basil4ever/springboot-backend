package net.javaguides.springbootbackend.dto;

import javax.persistence.Column;
import javax.persistence.Id;

import java.math.BigDecimal;

public class InvoiceReportDTO {

    @Id
    @Column(name = "employee_id")
    private long employee_id;

    @Column(name = "employee_name")
    private String employee_name;

    @Column(name = "invoice_sum")
    private BigDecimal invoice_sum;

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public BigDecimal getInvoice_sum() {
        return invoice_sum;
    }

    public void setInvoice_sum(BigDecimal invoice_sum) {
        this.invoice_sum = invoice_sum;
    }
}

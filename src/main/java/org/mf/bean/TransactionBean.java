package org.mf.bean;

public class TransactionBean {

    private Long id;
    private String companyName;
    private String description;
    private Double amount;

    public TransactionBean() {
    }

    public TransactionBean(Long id, String companyName, String description, Double amount) {
        this.id = id;
        this.companyName = companyName;
        this.description = description;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

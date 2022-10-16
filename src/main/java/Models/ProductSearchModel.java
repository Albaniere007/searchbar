package Models;

public class ProductSearchModel {

    Integer ID;
    String productName, branD, modelNumber,desCription;
    Integer modelYear;

    public ProductSearchModel(Integer ID, String productName, String branD, String modelNumber, Integer modelYear,String desCription) {
        this.ID = ID;
        this.productName = productName;
        this.branD = branD;
        this.modelNumber = modelNumber;
        this.desCription = desCription;
        this.modelYear = modelYear;
    }


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBranD() {
        return branD;
    }

    public void setBranD(String branD) {
        this.branD = branD;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getDesCription() {
        return desCription;
    }

    public void setDesCription(String desCription) {
        this.desCription = desCription;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }
}

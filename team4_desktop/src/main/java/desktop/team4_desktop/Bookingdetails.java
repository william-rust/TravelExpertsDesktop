package desktop.team4_desktop;


public class Bookingdetails {

  private long bookingDetailId;
  private double itineraryNo;
  private java.sql.Timestamp tripStart;
  private java.sql.Timestamp tripEnd;
  private String description;
  private String destination;
  private double basePrice;
  private double agencyCommission;
  private long bookingId;
  private String regionId;
  private String classId;
  private String feeId;
  private long productSupplierId;


  public long getBookingDetailId() {
    return bookingDetailId;
  }

  public void setBookingDetailId(long bookingDetailId) {
    this.bookingDetailId = bookingDetailId;
  }


  public double getItineraryNo() {
    return itineraryNo;
  }

  public void setItineraryNo(double itineraryNo) {
    this.itineraryNo = itineraryNo;
  }


  public java.sql.Timestamp getTripStart() {
    return tripStart;
  }

  public void setTripStart(java.sql.Timestamp tripStart) {
    this.tripStart = tripStart;
  }


  public java.sql.Timestamp getTripEnd() {
    return tripEnd;
  }

  public void setTripEnd(java.sql.Timestamp tripEnd) {
    this.tripEnd = tripEnd;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }


  public double getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(double basePrice) {
    this.basePrice = basePrice;
  }


  public double getAgencyCommission() {
    return agencyCommission;
  }

  public void setAgencyCommission(double agencyCommission) {
    this.agencyCommission = agencyCommission;
  }


  public long getBookingId() {
    return bookingId;
  }

  public void setBookingId(long bookingId) {
    this.bookingId = bookingId;
  }


  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }


  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }


  public String getFeeId() {
    return feeId;
  }

  public void setFeeId(String feeId) {
    this.feeId = feeId;
  }


  public long getProductSupplierId() {
    return productSupplierId;
  }

  public void setProductSupplierId(long productSupplierId) {
    this.productSupplierId = productSupplierId;
  }

}

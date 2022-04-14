package desktop.team4_desktop;


public class Bookings {

  private long bookingId;
  private java.sql.Timestamp bookingDate;
  private String bookingNo;
  private double travelerCount;
  private long customerId;
  private String tripTypeId;
  private long packageId;


  public long getBookingId() {
    return bookingId;
  }

  public void setBookingId(long bookingId) {
    this.bookingId = bookingId;
  }


  public java.sql.Timestamp getBookingDate() {
    return bookingDate;
  }

  public void setBookingDate(java.sql.Timestamp bookingDate) {
    this.bookingDate = bookingDate;
  }


  public String getBookingNo() {
    return bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }


  public double getTravelerCount() {
    return travelerCount;
  }

  public void setTravelerCount(double travelerCount) {
    this.travelerCount = travelerCount;
  }


  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }


  public String getTripTypeId() {
    return tripTypeId;
  }

  public void setTripTypeId(String tripTypeId) {
    this.tripTypeId = tripTypeId;
  }


  public long getPackageId() {
    return packageId;
  }

  public void setPackageId(long packageId) {
    this.packageId = packageId;
  }

}

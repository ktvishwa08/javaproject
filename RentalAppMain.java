package javaassignment;

class Property {
		 String propertyId;
		 String address;
		 String type;
		 double rent;
		 String status;
	 Property(String propertyId, String address, String type, double rent) {
		 this.propertyId = propertyId;
		 this.address = address;
		 this.type = type;
		 this.rent = rent;
		 this.status = "Available";
		 }
	 void display() {
		 System.out.println("PropertyId: " + propertyId + " | Address: " + address + " | Type:"
		 + type + " | Rent: " + rent + " | Status: " + status);
		 }
	 double maintenanceCharge() {
		 return 0;
		 }
		}
	 class Apartment extends Property {
		 Apartment(String propertyId, String address, double rent) {
		 super(propertyId, address, "Apartment", rent);
		 }
	 double maintenanceCharge() {
		 return 500;
		 }
	 void display() {
		 System.out.println("ApartmentId: " + propertyId + " | Address: " + address + 
				 " |Rent: " + rent + " | Status: " + status);
		 }
		}
	 class House extends Property {
		 House(String propertyId, String address, double rent) {
		 super(propertyId, address, "House", rent);
		 }
     double maintenanceCharge() {
		 return 1000;
		 }
     void display() {
		 System.out.println("HouseId: " + propertyId + " | Address: " + address + " | Rent: " +
		rent + " | Status: " + status);
		 }
		}
class Tenant {
		 String tenantId;
		 String name;
		 String contact;
		 double deposit;
		 Lease[] activeLeases = new Lease[5];
		 int leaseCount = 0;
	 Tenant(String tenantId, String name, String contact, double deposit) {
		 this.tenantId = tenantId;
		 this.name = name;
		 this.contact = contact;
		 this.deposit = deposit;
		 }
	 void addLease(Lease lease) {
		 if (leaseCount < activeLeases.length) {
		 activeLeases[leaseCount++] = lease;
		 }
		 }
	 void display() {
		 System.out.println("TenantId: " + tenantId + " | Name: " + name + " | Contact: " +
		contact + " | Deposit: " + deposit);
		 }
		}
class Lease {
		 String leaseId;
		 Property property;
		 Tenant tenant;
		 String startDate;
		 String endDate;
		 String rentCycle;
		 String status;
	 Lease(String leaseId, Property property, Tenant tenant, String startDate, String
		endDate, String rentCycle) {
		 this.leaseId = leaseId;
		 this.property = property;
		 this.tenant = tenant;
		 this.startDate = startDate;
		 this.endDate = endDate;
		 this.rentCycle = rentCycle;
		 this.status = "Active";
		 property.status = "Occupied";
		 tenant.addLease(this);
		 }
	 void terminate() {
		 status = "Terminated";
		 property.status = "Available";
		 }
	 void display() {
		 System.out.println("LeaseId: " + leaseId + " | Property: " + property.propertyId + 
				 " |Tenant: " + tenant.tenantId + " | Start: " + startDate + " | End: " + endDate + " | Cycle: " +
		rentCycle + " | Status: " + status);
		 }
		}
class RentalService {
		 Property[] properties = new Property[10];
		 Tenant[] tenants = new Tenant[10];
		 Lease[] leases = new Lease[10];
		 int propertyCount = 0;
		 int tenantCount = 0;
		 int leaseCount = 0;
	void addProperty(Property property) {
		 if (propertyCount < properties.length) {
		 properties[propertyCount++] = property;
		 }
		 }
	void addTenant(Tenant tenant) {
		 if (tenantCount < tenants.length) {
		 tenants[tenantCount++] = tenant;
		 }
		 }
	 Lease createLease(String leaseId, String propertyId, String tenantId, String startDate,
		String endDate, String rentCycle) {
		 Property p = null;
		 Tenant t = null;
		 for (int i = 0; i < propertyCount; i++) {
     		 if (properties[i].propertyId.equals(propertyId) &&
	       	 properties[i].status.equals("Available")) {
		     p = properties[i];
		     break;
		   }
		 }
		 for (int j = 0; j < tenantCount; j++) {
		     if (tenants[j].tenantId.equals(tenantId)) {
		     t = tenants[j];
		     break;
		 }
		 }
		  if (p != null && t != null && leaseCount < leases.length) {
		  Lease lease = new Lease(leaseId, p, t, startDate, endDate, rentCycle);
		  leases[leaseCount++] = lease;
		  return lease;
		 }
		 return null;
		 }
	void collectRent(Lease lease, double amount) {
		 System.out.println("Rent collected from Tenant " + lease.tenant.name + ": " +
		amount);
		 }
	void collectRent(Lease lease, double amount, boolean late) {
		 if (late) {
		 double fine = 100;
		 System.out.println("Late rent collected from Tenant " + lease.tenant.name + ": "
		+ amount + " + Fine: " + fine);
		 } else {
		 System.out.println("Partial rent collected from Tenant " + lease.tenant.name +
				 ":" + amount);
		 }
		 }
	void showAllProperties() {
		 for (int i = 0; i < propertyCount; i++) {
		 properties[i].display();
		 }
		 }
	void showAllLeases() {
		 for (int i = 0; i < leaseCount; i++) {
		 leases[i].display();
		 }
		 }
	void monthlyIncomeReport() {
		 double total = 0;
		 for (int i = 0; i < leaseCount; i++) {
		 if (leases[i].status.equals("Active")) {
		 total += leases[i].property.rent;
		 }
		 }
		 System.out.println("Monthly Income: " + total);
		 }
		 void occupancyReport() {
		 int occupied = 0;
		 for (int i = 0; i < propertyCount; i++) {
		 if (properties[i].status.equals("Occupied")) {
		 occupied++;
		 }
		 }
		 System.out.println("Total Properties: " + propertyCount + " | Occupied: " + occupied
		+ " | Vacant: " + (propertyCount - occupied));
		 }
		}
public class RentalAppMain {
	public static void main(String[] args) {
		 RentalService service = new RentalService();
		 Property p1 = new Apartment("P1", "Street 1", 8000);
		 Property p2 = new House("P2", "Street 2", 12000);
		 Tenant t1 = new Tenant("T1", "Vishwa", "9876543210", 10000);
		 Tenant t2 = new Tenant("T2", "Harish", "9876501234", 15000);
		 service.addProperty(p1);
		 service.addProperty(p2);
		 service.addTenant(t1);
		 service.addTenant(t2);
		 Lease l1 = service.createLease("L1", "P1", "T1", "01-09-2025", "31-08-2026",
		"Monthly");
		 service.showAllProperties();
		 service.showAllLeases();
		 service.collectRent(l1, 8000);
		 service.collectRent(l1, 4000, false);
		 service.collectRent(l1, 8000, true);
		 service.monthlyIncomeReport();
		 service.occupancyReport();
		 l1.terminate();
		 service.showAllLeases();
		 service.occupancyReport();
		 }
		}
